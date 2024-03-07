import numpy as np
import keras
from keras import layers
from keras.models import Model
from sklearn.utils import shuffle
from sklearn.model_selection import train_test_split
from imgaug import augmenters as iaa
import random
import socket
import threading
import os
from PIL import Image

import cv2
import glob
import struct

def preprocess(image_path):
    img_list = sorted(glob.glob(image_path + '/*.bmp'))

    imgs = np.empty((len(img_list), 90, 90,1), dtype=np.uint8)

    for i, img_path in enumerate(img_list):
        img = cv2.imread(img_path, cv2.IMREAD_GRAYSCALE)
        img = cv2.resize(img, (90,90))
        img = np.resize(img, (90, 90, 1))
        
        imgs[i] = img
        
    np.savez(image_path + '/x.npz', data=imgs)

    print(f'bmp list: len->{len(img_list)}')
    
    for f in img_list:
        if os.path.isfile(f):
            os.remove(f)

def myPredict(new_image_path, target_image_path, augment_count, threshold):
    # Get a new image
    print('new: ' + new_image_path)
    new_image = np.load(new_image_path)['data'][0]


    if not os.path.isfile(target_image_path):
        return (-2, 0.)

    # # Get target images
    print('target: ' + target_image_path)
    target_image = np.load(target_image_path)['data']

    flag = 0
    max_pred = 0.
    for _ in range(augment_count):
        # Augmentation
        seq = iaa.Sequential([
            iaa.GaussianBlur(sigma=(0, 0.5)),
            iaa.Affine(
                scale={"x": (0.9, 1.1), "y": (0.9, 1.1)},
                translate_percent={"x": (-0.1, 0.1), "y": (-0.1, 0.1)},
                rotate=(-30, 30),
                order=[0, 1],
                cval=255
            )
        ], random_order=True)

        seq_img = seq.augment_image(new_image).reshape((1, 90, 90, 1)).astype(np.float32) / 255.
        idx = 1

        for cur_target_image in target_image:
            cur_target_image = cur_target_image.reshape((1, 90, 90, 1)).astype(np.float32) / 255.
            pred = model.predict([seq_img, cur_target_image])
            # print(f"%.03f %% accuracy with index %d" % (pred, idx))
            idx += 1
            cur_pred = pred[0][0]
            if cur_pred >= threshold:
                flag = 1
            if cur_pred > max_pred:
                max_pred = cur_pred
    
    return (flag, max_pred.item())

def save_image(col, img_path, img_name, data):
    resultImage = Image.new('L',(256,288), 'white')
    pixels = resultImage.load()
    (resultImageWidth, resultImageHeight) = resultImage.size
    current_row, current_column = 0, 0
    for y in range(resultImageHeight):
        for x in range(resultImageWidth):
            if x % 2 == 0:
                pixels[x, y] = (data[current_row][current_column] >> 4) * 17
            else:
                pixels[x, y] = (data[current_row][current_column] & 0x0F) * 17
                current_column += 1
                if current_column == col:
                    current_row += 1
                    current_column = 0

    try:
        if not os.path.exists(img_path):
            os.makedirs(img_path)
    except OSError:
        print("Failed to make data directory...")
    except Exception:
        print("Unknown error for makeing directory")

    try:
        resultImage.save(img_path + '/' + img_name)
    except Exception:
        print("Failed to save data")

    return True

def register(client_socket, seq, num, row, col):
    # origin_path = '/home/j-i10a203/recognition/targetNpzData'
    origin_path = './targetNpzData'
    for n in range(1,num + 1):
        print('image #', end="")
        print(n)
        image_data = []
        print(row, col)
        for cnt in range(row):
            recv_len = col
            buf = []
            while True:
                data = client_socket.recv(recv_len)
                buf += data
                if len(buf) == col:
                    break
                elif len(buf) < col:
                    recv_len = col - len(buf)
            #print(buf)
            image_data.append(buf)

        print("Data received, length: ", end="")
        print(len(image_data))

        image_path = origin_path + '/' + str(seq)
        image_name = str(seq) + '__M_Right_thumb_finger_' + str(n) + '.bmp'

        if save_image(col, image_path, image_name, image_data):
            print(f'Image #{n} saved')
        else:
            print('Failed to save images...')
            return 0
    
    preprocess(image_path)

    print(f'Registration for user#{seq} confirmed')
    return 1
        
def match(client_socket, seq, row, col):
    # origin_new_path = '/home/j-i10a203/recognition/newNpzData/'
    # origin_target_path = '/home/j-i10a203/recognition/targetNpzData/'
    origin_new_path = './newNpzData'
    target_path = './targetNpzData'
    image_data = []

    print(f"{row}, {col}")

    for _ in range(row):
        recv_len = col
        buf = []
        while True:
            data = client_socket.recv(recv_len)
            buf += data
            if len(buf) == col:
                break
            elif len(buf) < col:
                recv_len = col - len(buf)
        image_data.append(data)

    print("Tramsmission comfirmed...")
    print(f"Data received, length: {len(image_data)}")

    image_path = origin_new_path + '/' + str(seq)
    image_name = str(seq) + '__M_Right_thumb_finger_' + '0' + '.bmp'

    if save_image(col, image_path, image_name, image_data):
        print(f'Image 0 saved')
    else:
        print('Failed to save images...')
        return (False, 0.)
    
    # TBD: Make npz file
    preprocess(image_path)

    return myPredict(image_path + '/x.npz', target_path + '/' + str(seq) + '/x.npz', 3, 0.985)

def bind(client_socket, addr):
    print('Connected with ', addr)

    try:

        # Length of command
        cmd_len = client_socket.recv(4)
        cmd_len = int.from_bytes(cmd_len, 'big')
        print("cmd_len: ", cmd_len)

        # Command
        cmd = client_socket.recv(cmd_len)
        cmd = cmd.decode()
        print("cmd: ", cmd)

        # Sequantial number of the user
        seq = client_socket.recv(4)
        seq = int.from_bytes(seq, 'big')
        print("seq: ", seq)

        num_images = 1

        # Number of images
        if cmd == "REGISTER":
            num_images = client_socket.recv(4)
            num_images = int.from_bytes(num_images, 'big')
            print("num_images: ", num_images)

        # Row size of image
        row = client_socket.recv(4)
        row = int.from_bytes(row, 'big')
        print('row: ', row)

        # Column size of image
        col = client_socket.recv(4)
        col = int.from_bytes(col, 'big')
        print('col: ', col)

        result = 0

        if cmd == "REGISTER":
            result = register(client_socket, seq, num_images, row, col)
            print(result)
            client_socket.sendall(result.to_bytes(4, byteorder='big'))
            
        elif cmd == "MATCH":
            (flag, max_pred) = match(client_socket, seq, row, col)
            print(flag, max_pred)
            print(type(flag), type(max_pred))
            print(flag.to_bytes(4, byteorder='big'))
            print(struct.pack('>d', max_pred))
            client_socket.sendall(flag.to_bytes(4, byteorder='big'))
            client_socket.sendall(struct.pack('>d', max_pred))

        # data = 1
        # client_socket.sendall(data.to_bytes(4, byteorder='big'))
    except:
        print('Connection failed with ',addr)
    finally:
        client_socket.close()


if __name__ == "__main__":
    port = 41234


    # Load my model
    print("Loading model...")
    model = keras.models.load_model("fr_recog_model")
    model.summary()

    print('Starting Server')
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind(('',port))

    server_socket.listen()
    print('Server listening...')

    try :
        while True:
            client_socket, addr = server_socket.accept()
            th = threading.Thread(target=bind, args = (client_socket, addr))
            th.start()
    except :
        print('Client binding error')
    finally:
        server_socket.close()

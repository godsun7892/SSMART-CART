from serial import Serial

class MySerial:

    def __init__(self):
        print("TESTEST")

    def py_print(self, str):
        print(f"PY: {str}")

    def begin(self, deivce_name, baudrate):
        self.ser = Serial(deivce_name,baudrate=baudrate,timeout=1)
        self.py_print(f"device: {deivce_name}, baudrate: {baudrate}")

    def get_char(self):
        # self.py_print('get char')
        if self.ser.readable():
            # self.py_print('reading...')
            try:
                res = self.ser.read(1)
                # self.py_print(int.from_bytes(res, byteorder='big'))
                return int.from_bytes(res, byteorder='big')
            except Exception as e:
                print('예외가 발생했습니다.', e)
        
    # def gets(self):
    #     self.py_print('gets')
    #     if self.ser.readable():
    #         self.py_print('reading...')
    #         res = self.ser.readline()
    #         self.py_print('read data!')
    #         self.py_print(res[:len(res)-1].decode())
    #         return res[:len(res)-1].decode()
    #     else:
    #         print("PY: ERROR")
    #         return "ERROR"
    
    # def put_char(self, data):
    #     self.py_print(f'data: {data}')
    #     self.ser.write(data)

    def puts(self, data):
        # self.py_print(f'data: {data.to_bytes()}')
        self.ser.write(data.to_bytes())


    def isReadable(self):
        return self.ser.readable()



package com.a203.smartcart.service;

import com.a203.smartcart.model.entity.Member;
import com.a203.smartcart.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Service
@Transactional
@RequiredArgsConstructor
public class FingerprintService {

    private final MemberRepository memberRepository;

    @Value("${gpu.ip}")
    private String gpuIp;

    @Value("${gpu.port}")
    private int gpuPort;


    public int register(byte [][][] images, String phoneNum){

        Member targetMember = memberRepository.findByUserId(phoneNum);

        // No such user
        if(targetMember == null){
            return -1;
        }

        try{
            Socket client = new Socket();
            InetSocketAddress ipep = new InetSocketAddress(gpuIp, gpuPort);
            client.connect(ipep);

            try{
                OutputStream sender = client.getOutputStream();
                InputStream receiver = client.getInputStream();

                // Requesting recognition
                // Command Length
                ByteBuffer cmdLen = ByteBuffer.allocate(4);
                cmdLen.order(ByteOrder.BIG_ENDIAN);
                cmdLen.putInt("REGISTER".length());
                sender.write(cmdLen.array(), 0, 4);

                // Register command
                byte[] cmd = "REGISTER".getBytes();
                sender.write(cmd);

                // User sequential number
                ByteBuffer seq = ByteBuffer.allocate(4);
                seq.putInt(targetMember.getSeq());
                sender.write(seq.array(), 0, 4);

                // Number of images
                ByteBuffer numImages = ByteBuffer.allocate(4);
                numImages.putInt(images.length);
                sender.write(numImages.array(), 0, 4);

                // Row
                ByteBuffer row = ByteBuffer.allocate(4);
                row.putInt(images[0].length);
                sender.write(row.array(), 0, 4);
                // Col
                ByteBuffer col = ByteBuffer.allocate(4);
                col.putInt(images[0][0].length);
                sender.write(col.array(), 0, 4);

                for(byte[][] image : images){
                    for (byte[] data : image){
                        sender.write(data);
                        //System.out.println("Send... length: " + data.length);
                    }
                }

                // Getting a Response
                byte[] recv_data = new byte[4];
                receiver.read(recv_data, 0, 4);
                ByteBuffer resultBuf = ByteBuffer.wrap(recv_data);
                resultBuf.order(ByteOrder.BIG_ENDIAN);
                if (resultBuf.getInt() == 0){
                    return 0;
                }
                else return 1;
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
        catch (Throwable e){
            e.printStackTrace();
        }

        return 0;
    }

    public int match(byte[][] image, String phoneNum){

        Member targetMember = memberRepository.findByUserId(phoneNum);

        // No such user
        if(targetMember == null){
            return -1;
        }

        try{
            Socket client = new Socket();
            InetSocketAddress ipep = new InetSocketAddress(gpuIp, gpuPort);
            client.connect(ipep);

            try{
                OutputStream sender = client.getOutputStream();
                InputStream receiver = client.getInputStream();

                // Requesting recognition
                // Command Length
                ByteBuffer cmdLen = ByteBuffer.allocate(4);
                cmdLen.order(ByteOrder.BIG_ENDIAN);
                cmdLen.putInt("MATCH".length());
                sender.write(cmdLen.array(), 0, 4);

                // Register command
                byte[] cmd = "MATCH".getBytes();
                sender.write(cmd);

                // User sequential number
                ByteBuffer seq = ByteBuffer.allocate(4);
                seq.putInt(targetMember.getSeq());
                sender.write(seq.array(), 0, 4);

                // Row
                ByteBuffer row = ByteBuffer.allocate(4);
                row.putInt(image.length);
                sender.write(row.array(), 0, 4);
                // Col
                ByteBuffer col = ByteBuffer.allocate(4);
                col.putInt(image[0].length);
                sender.write(col.array(), 0, 4);

                for (byte[] data : image) {
                    sender.write(data);
                }

                // Getting a Response
                byte[] recv_data = new byte[4];
                receiver.read(recv_data, 0, 4);
                ByteBuffer resultBuf = ByteBuffer.wrap(recv_data);
                resultBuf.order(ByteOrder.BIG_ENDIAN);
                int flag = resultBuf.getInt();

                byte[] recv_pred = new byte[8];
                receiver.read(recv_pred, 0, 8);
                ByteBuffer predBuf = ByteBuffer.wrap(recv_pred);
                predBuf.order(ByteOrder.BIG_ENDIAN);
                double pred = predBuf.getDouble();

                System.out.printf("%d %f\n",flag, pred);

                return flag;
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
        catch (Throwable e){
            e.printStackTrace();
        }

        return 0;
    }
}
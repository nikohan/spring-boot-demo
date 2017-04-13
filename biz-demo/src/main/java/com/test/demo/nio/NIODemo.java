package com.test.demo.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * http://ifeve.com/java-nio-all/
 * Created on 2017/4/13.
 */
public class NIODemo {

    private void displayBuf(ByteBuffer buf) throws IOException {
        buf.flip();  //make buffer ready for read
        while(buf.hasRemaining()){
            System.out.print((char) buf.get()); // read 1 byte at a time
        }
        System.out.println();
    }

    @Test
    public void fileChannelTest() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("D:/Data/testFrom.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }

        aFile.close();
    }

    @Test
    public void scatterTest() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("D:/Data/testFrom.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(1);
        ByteBuffer body   = ByteBuffer.allocate(2);

        ByteBuffer[] bufferArray = { header, body };

        //分散到多个buf中
        inChannel.read(bufferArray);

        displayBuf(header);
        displayBuf(body);

        aFile.close();
    }

    @Test
    public void gatherTest() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("D:/Data/testFrom.txt", "rw");
        FileChannel channel1 = aFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(1);
        ByteBuffer body   = ByteBuffer.allocate(2);

        header.put((byte) 49);//1
        body.put((byte) 50);//2
        body.put((byte) 51);//3

        header.flip();
        body.flip();

        ByteBuffer[] bufferArray = { header, body };
        channel1.write(bufferArray);

        aFile.close();
    }

    @Test
    public void transferFromTest() throws Exception {
        long startTime = System.currentTimeMillis();

        RandomAccessFile fromFile = new RandomAccessFile("D:/Data/testFrom.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("D:/Data/testTo.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

        fromFile.close();
        toFile.close();

        System.out.println("----耗时(ms):" + (System.currentTimeMillis() - startTime));
    }


}

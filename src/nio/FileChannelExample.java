package nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {
    public static void main(String[] arg) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("resources/test.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = fileChannel.read(buffer);
        while (bytesRead!= -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            bytesRead = fileChannel.read(buffer);
        }
    }
}

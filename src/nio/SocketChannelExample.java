package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SocketChannelExample {
    public static void main(String[] args) {
        Runnable server = () -> {
            try {
                System.out.println("Start server");

                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("Incoming connection accepted");

                ByteBuffer buffer = ByteBuffer.wrap("Hello, World!".getBytes());
                socketChannel.write(buffer);
//                buffer.flip();
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };


        Runnable client = () -> {
            try {
                System.out.println("Start client");
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.connect(new InetSocketAddress("localhost", 8080));
                boolean isConnected = socketChannel.isConnected();
                System.out.println("Connected: " + isConnected);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.print("Server output: " + new String(bytes, StandardCharsets.UTF_8) + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }

        };

        System.out.println("First thread");
        new Thread(server).start();
        System.out.println("Second thread");
        new Thread(client).start();
    }
}

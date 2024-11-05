package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

// Not final
public class SelectorExample {
    public static void main(String[] arg) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = runServer();
        serverChannel.configureBlocking(false);
        serverChannel.register(selector,
                SelectionKey.OP_ACCEPT);

        runClient();

        selector.select();
        Set<SelectionKey> selectedKeys = selector.selectedKeys();

        Iterator<SelectionKey> iterator = selectedKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isAcceptable()) {
                // Accept an incoming connection
            } else if (key.isReadable()) {
                // Read data from a channel
            }
            iterator.remove();
        }
    }

    private static void runClient() {
        Runnable client = () -> {
            try {
                System.out.println("Start client");
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.connect(new InetSocketAddress("localhost", 8080));
                boolean isConnected = socketChannel.isConnected();
                System.out.println("Connected: " + isConnected);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                socketChannel.read(buffer);
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.print("Server output: " + new String(bytes, StandardCharsets.UTF_8) + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }

        };
        new Thread(client).start();
    }

    private static ServerSocketChannel runServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Runnable server = () -> {
            try {
                System.out.println("Start server");

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
        new Thread(server).start();
        return serverSocketChannel;
    }
}

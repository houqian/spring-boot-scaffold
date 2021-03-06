package org.houqian.springbootdemo.nio;// $Id$


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiPortEcho {
  private int        ports[];
  private ByteBuffer echoBuffer = ByteBuffer.allocate(1024);

  public MultiPortEcho(int ports[]) throws IOException {
    this.ports = ports;

    go();
  }

  private void go() throws IOException {
    // Create a new selector
    Selector selector = Selector.open();

    // Open a listener on each port, and register each one
    // with the selector
    for (int i = 0; i < ports.length; ++i) {
      ServerSocketChannel ssc = ServerSocketChannel.open();
      ssc.configureBlocking(false);
      ServerSocket      ss      = ssc.socket();
      InetSocketAddress address = new InetSocketAddress(ports[i]);
      ss.bind(address);

      SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);

      System.out.println("Going to listen on " + ports[i]);
    }

    while (true) {
      int num = selector.select();

      Set<SelectionKey>      selectedKeys = selector.selectedKeys();
      Iterator<SelectionKey> it           = selectedKeys.iterator();

      while (it.hasNext()) {
        SelectionKey key = it.next();

        if ((key.readyOps() & SelectionKey.OP_ACCEPT)
                == SelectionKey.OP_ACCEPT) {
          // Accept the new connection
          ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
          SocketChannel       sc  = ssc.accept();
          sc.configureBlocking(false);

          // Add the new connection to the selector
          SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
          it.remove();

          System.out.println("Got connection from " + sc);
        } else if ((key.readyOps() & SelectionKey.OP_READ)
                == SelectionKey.OP_READ) {
          // Read the data
          SocketChannel sc = (SocketChannel) key.channel();

          // Echo data
          int bytesEchoed = 0;
          while (true) {
            echoBuffer.clear();

            int r = sc.read(echoBuffer);

            if (r <= 0) {
              break;
            }

            echoBuffer.flip();

            String s = bytebufferToString(echoBuffer);
            System.out.println(s);

            sc.write(echoBuffer);
            bytesEchoed += r;
          }

          System.out.println("Echoed " + bytesEchoed + " from " + sc);

          it.remove();
        }

      }

//System.out.println( "going to clear" );
//      selectedKeys.clear();
//System.out.println( "cleared" );
    }
  }

  private String bytebufferToString(ByteBuffer echoBuffer) {
    // 得到缓冲区的已写入长度，并创建等长的缓冲数组
    final byte[] bytes = new byte[echoBuffer.remaining()];
    // 读出该长度的数据到缓冲数组
    echoBuffer.get(bytes);
    // 转成字符串
    return new String(bytes);
  }

  static public void main(String args[]) throws Exception {
    args = new String[]{"9000", "9001", "9002", "9003"};
    if (args.length <= 0) {
      System.err.println("Usage: java MultiPortEcho port [port port ...]");
      System.exit(1);
    }

    int ports[] = new int[args.length];

    for (int i = 0; i < args.length; ++i) {
      ports[i] = Integer.parseInt(args[i]);
    }

    new MultiPortEcho(ports);
  }
}

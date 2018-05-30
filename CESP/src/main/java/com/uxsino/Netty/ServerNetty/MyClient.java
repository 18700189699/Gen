package com.uxsino.Netty.ServerNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class MyClient {

    private static class SingletonHolder {
//        static final MyClient instance = new MyClient();

//        private static MyClientHandlerAdapter myClientHandlerAdapter;
//
//        public static void setMyClientHandlerAdapter(MyClientHandlerAdapter myClientHandlerAdapter) {
//            SingletonHolder.myClientHandlerAdapter = myClientHandlerAdapter;
//        }

        public  static MyClient instance(MyClientHandlerAdapter myClientHandlerAdapter){
            return new MyClient(myClientHandlerAdapter);
        }
    }

    public static MyClient getInstance(MyClientHandlerAdapter myClientHandlerAdapter) {
        return SingletonHolder.instance(myClientHandlerAdapter);
    }

    private EventLoopGroup group;
    private Bootstrap b;
    private ChannelFuture cf;

    private MyClient(MyClientHandlerAdapter myClientHandlerAdapter) {
        group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(group)
                .option(ChannelOption.SO_KEEPALIVE,true)    //保持连接（可以不写，默认为true）
                .option(ChannelOption.TCP_NODELAY, true)   //禁用nagle算法
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //sc.pipeline().addLast(new ReadTimeoutHandler(1));//5秒不传送数据断开连接
                        sc.pipeline().addLast(myClientHandlerAdapter);
                    }
                });
    }


    public void connect() {
        try {
            this.cf = b.connect("127.0.0.1", 8765).sync();
            System.out.println("远程服务器已连接，可以进行数据提交");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChannelFuture getChannelFuture() {
        if (this.cf == null) {
            this.connect();
        }
        if (!this.cf.channel().isActive()) {
            this.connect();
        }
        return this.cf;
    }
}
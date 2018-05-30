package com.uxsino.Netty.ServerNetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;


public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup pGroup = new NioEventLoopGroup();    //一个是用于处理服务器端接受客户端的
        EventLoopGroup cGroup = new NioEventLoopGroup();    //一个是进行网络通信的（网络读写）
        ServerBootstrap b = new ServerBootstrap();          //创建辅助工具类，用于服务器通道的一系列配置
        b.group(pGroup,cGroup)                              //绑定两个线程组
                .channel(NioServerSocketChannel.class)      //指定NIO的模式
                .option(ChannelOption.SO_BACKLOG,1024)      //设置tcp缓冲区
                .option(ChannelOption.SO_SNDBUF,32*1024)    //设置发送缓冲大小
                .option(ChannelOption.SO_RCVBUF,32*1024)    //设置接受缓冲大小
                .option(ChannelOption.SO_KEEPALIVE,true)    //保持连接（可以不写，默认为true）
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                        sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));//以特殊字符分割解决TCP的粘包问题
                        //sc.pipeline().addLast(new FixedLengthFrameDecoder(5));      //以长度分割解决TCP的粘包问题（如果不够用空格补位）
                        sc.pipeline().addLast(new StringDecoder());     //把接收到的数据转换成String类型
						sc.pipeline().addLast(new HeartBeatServerHandler());  //心跳监测
						sc.pipeline().addLast("handler",new IdleStateHandler(3, 0, 0, TimeUnit.SECONDS));

                        //sc.pipeline().addLast(new ReadTimeoutHandler(1));
                        sc.pipeline().addLast(new MyServerHandler());                    //在这里配置具体数据接收方法的处理
                    }
                });

        ChannelFuture cfl = b.bind(8765).sync();    //进行绑定

        cfl.channel().closeFuture().sync();                 //等待

        pGroup.shutdownGracefully();                        //关闭释放
        cGroup.shutdownGracefully();
    }
}

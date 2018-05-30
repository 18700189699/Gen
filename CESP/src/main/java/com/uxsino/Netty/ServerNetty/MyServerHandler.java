package com.uxsino.Netty.ServerNetty;

import io.netty.buffer.ByteBuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyServerHandler extends ChannelInboundHandlerAdapter {     //ChannelHandlerAdapter 没有channelRead方法 QAQ
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {

/*        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req,"utf-8");*/

        String body = (String)msg;
        System.out.println("Server:" +body);

        String response = "Hi Client!!    我是服务器"+"这个是客户端你发给我的消息："+body+"$_";

        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));      //服务器端给客户端的响应

        /*ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()))
        .addListener(ChannelFutureListener.CLOSE);  //发送完毕后关闭管道*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}

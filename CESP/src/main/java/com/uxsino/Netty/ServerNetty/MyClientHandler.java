package com.uxsino.Netty.ServerNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class MyClientHandler extends ChannelInboundHandlerAdapter {

    private MyClientHandlerAdapter myClientHandlerAdapter;

    MyClientHandler(MyClientHandlerAdapter myClientHandlerAdapter){
        this.myClientHandlerAdapter = myClientHandlerAdapter;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("启动时会调用这个方法");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {

            String body =(String )msg;
            GetFile.writeDiskInfo(body);
            System.out.println("Client:"+body);
            // 设置返回值
            myClientHandlerAdapter.setResponse(body);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

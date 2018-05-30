package com.uxsino.Netty.ServerNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyClientHandlerAdapter extends ChannelInitializer<SocketChannel> {


	private Object response;

	@Override
	protected void initChannel(SocketChannel sc) {
		ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
		sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));//以特殊字符分割解决TCP的粘包问题
		sc.pipeline().addLast(new StringDecoder());     //把接收到的数据转换成String类型
		sc.pipeline().addLast(new MyClientHandler(this));
		sc.pipeline().addLast(new HeartBeatClientHandler());  //心跳监测
		sc.pipeline().addLast("handler", new IdleStateHandler(0, 3, 0, TimeUnit.SECONDS));
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Object getResponse() {
		return response;
	}
}

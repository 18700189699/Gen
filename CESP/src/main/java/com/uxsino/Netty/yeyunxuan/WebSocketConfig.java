package com.uxsino.Netty.yeyunxuan;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class WebSocketConfig {

	//@Value("${netty.port}")
	private int port;
	//@Value("${netty.host}")
	private String host;
	//@Value("${netty.websocket.url}")
	private String url;
	//@Value("${netty.websocket.user_name}")
	private String userName;
}

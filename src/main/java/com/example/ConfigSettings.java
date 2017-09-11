package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
@PropertySource("classpath:config.properties")
public class ConfigSettings {

	
	private Integer serverport;
	private String  protocol;
	private String scheme;
	private Boolean secure;
	private Integer directport;
	
	
	 
	public Integer getServerport() {
		return serverport;
	}
	public void setServerport(Integer serverport) {
		this.serverport = serverport;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public Boolean getSecure() {
		return secure;
	}
	public void setSecure(Boolean secure) {
		this.secure = secure;
	}
	public Integer getDirectport() {
		return directport;
	}
	public void setDirectport(Integer directport) {
		this.directport = directport;
	}
	
}

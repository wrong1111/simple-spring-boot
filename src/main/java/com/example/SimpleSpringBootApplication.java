package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.dao")
@EntityScan(basePackages = "com.example.model")
public class SimpleSpringBootApplication {

	 
	/***
	 * 此方法主要是加载一些特定额外的properties
	 * 具体哪个文件，看此类的头部configure配置
	 * */
	@Autowired
	ConfigSettings configSettings;
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringBootApplication.class, args);
	}
	
	/***
	 * 下面的二个方法，为默认http请求，全部转成https请求。
	 * 同时，需要创建授权证书(此方法为tomcat使用，使用nginx请使用baidu.com)
	 * 步骤如下：
	 * 1，keytool -genkey -alias tomcat
	 *    在当前目录下生成的.keystore文件，复制到项目根目录下。
	 * 2，在 application.properies文件中，配置如下
	 * server.port = 8443
	 * server.ssl.key-store = .keystore
	 * server.ssl.key-store-password = 123456
	 * server.ssl.keyStoreType = JKS
	 * server.ssl.keyAlias = tomcat
	 * 
	 * 
	 * 如果访问http://localhost:8080 出现 ERR_SSL_VERSION_OR_CIPHER_MISMATCH 错误 。
	 * 在chore浏览器中，按如下操作
	 * For Chrome v40:

		Open chrome://flags
		Look for "Minimum SSL/TLS version supported."
		Choose SSLv3
		Click on "Relaunch now" button
		Open your https page again
		You will be redirected to a "Your connection is not private" page. If you do not worry about this security issue click on the "Advanced" link.
		Finally click on "Proceed to (unsafe)".
	 * **/
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint  = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
				
			}
		};
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}
	
	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector(configSettings.getProtocol());
		connector.setScheme(configSettings.getScheme());
		connector.setPort(configSettings.getServerport());
		connector.setSecure(configSettings.getSecure());
		connector.setRedirectPort(configSettings.getDirectport());
		return connector;
	}
	
	//--以上为使用https配合使用。
}

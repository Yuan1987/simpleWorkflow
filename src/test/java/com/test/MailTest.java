package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dynastech.common.service.IMailService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/spring/spring-*.xml")
public class MailTest {

	@Autowired
	IMailService mailService;

	@Test
	public void send() {
		
		String content="测试邮件发送%s";
		
		content=String.format(content, "张三");
		
		mailService.send("263710292@qq.com", "Test",content);
	}

}

package com.dynastech.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.dynastech.common.service.IMailService;

@Service
public class MailServiceImpl implements IMailService{

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	/** 
     * 单发 
     * 
     * @param recipient 收件人 
     * @param subject 主题 
     * @param content 内容 
     */
	@Override
    public void send(String recipient,String subject,String content){  
        simpleMailMessage.setTo(recipient);  
        simpleMailMessage.setSubject(subject);  
        simpleMailMessage.setText(content);  
        mailSender.send(simpleMailMessage);  
    }  
      
    /** 
     * 群发 
     * 
     * @param recipients 收件人 
     * @param subject 主题 
     * @param content 内容 
     */
	@Override
    public void send(List<String> recipients,String subject,String content){  
        simpleMailMessage.setTo(recipients.toArray(new String[recipients.size()]));  
        simpleMailMessage.setSubject(subject);  
        simpleMailMessage.setText(content);  
        mailSender.send(simpleMailMessage);  
    } 
}

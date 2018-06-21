package com.dynastech.common.service;

import java.util.List;

public interface IMailService {
	
	 /** 
     * 单发 
     * 
     * @param recipient 收件人 
     * @param subject 主题 
     * @param content 内容 
     */  
    public void send(String recipient,String subject,String content);
    
    
    /** 
     * 群发 
     * @param recipients 收件人 
     * @param subject 主题 
     * @param content 内容 
     */  
    public void send(List<String> recipients,String subject,String content);

}

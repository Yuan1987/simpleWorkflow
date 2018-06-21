package com.dynastech.base.util;

import java.net.URI;

import com.dynastech.model.entity.Apply;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.entity.PersonEvaluationform;
import com.dynastech.system.entity.User;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class ExchangUtil {
	
	public static final String topic ="员工任职管理系统信息";
	
	/**
     * 发送邮件
     * @param mail
     * @return
	 * @throws Exception 
     */
    public static boolean sendEmail(String to,String subject,String body) throws Exception {

        Boolean flag = false;
        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP1); //新建server版本
        ExchangeCredentials credentials = new WebCredentials("hbyuan@dynastech.com", "Dg147852369", "dynastech"); //用户名，密码，域名
        service.setCredentials(credentials);
        service.setUrl(new URI("https://outlook.live.com/EWS/Exchange.asmx")); //outlook.spacex.com 改为自己的邮箱服务器地址
        EmailMessage msg = new EmailMessage(service);
        msg.setSubject(subject); //主题
        msg.setBody(MessageBody.getMessageBodyFromText(body)); //内容
        msg.getToRecipients().add(to); //收件人
//        	msg.getCcRecipients().add("test2@test.com"); //抄送人
//        	msg.getAttachments().addFileAttachment("D:\\Downloads\\EWSJavaAPI_1.2\\EWSJavaAPI_1.2\\Getting started with EWS Java API.RTF"); //附件
        msg.send(); //发送
        flag = true;

        return flag;
    }
    
    public static String templeteForApply(Apply apply,CheckDetail cd){
    	
    	String temp="申请人：【%s】 申请的序列为【%s】层级为【%s】的申请，在【%s】环节  【%s】审核意见为 %s";
    	
    	String result=ModelEnum.STATUS_3.getValue();
    	
    	if(cd.getResult()){
    		result=ModelEnum.STATUS_2.getValue();
    	}else if(!cd.getResult()&&cd.getIsback()){
    		result=ModelEnum.EVALSTATUS_4.getValue();
    	}
    	return String.format(temp,apply.getUsername(),apply.getSeqV(),apply.getGradeV(),cd.getNodename(),result,cd.getOpinion());
    }
    
    public static String templete(User user,PersonEvaluationform pef,CheckDetail cd){
    	
    	String temp="申请人【%s】 申请的序列为【%s】层级为【%s】的 测评表，在【%s】环节  【%s】审核意见为 %s";
    	
    	String result=ModelEnum.STATUS_3.getValue();
    	
    	if(cd.getResult()){
    		result=ModelEnum.STATUS_2.getValue();
    	}else if(!cd.getResult()&&cd.getIsback()){
    		result=ModelEnum.EVALSTATUS_4.getValue();
    	}
    	return String.format(temp,user.getDisplayname(),pef.getCnSerial(),pef.getCnLevel(),cd.getNodename(),result,cd.getOpinion());
    }

    public static void main(String[] args) {
        try {
			sendEmail("263710292@qq.com","TEST","测试");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

package com.data.model.timer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.data.model.entity.VUser;
import com.data.model.entity.VUserExample;
import com.data.model.mapper.VUserMapper;
import com.data.model.service.DataImport;

@Component
public class DataImportTimer {
	
	private static Logger logger = Logger.getLogger(DataImportTimer.class);
	
	@Autowired
	private DataImport dataImport;
	
	@Autowired
	private VUserMapper vUserMapper;
	
	
	//@Scheduled(cron="0 0/1 * * * ?")//每分钟
	//@Scheduled(cron = "0 0 2 * * ?")//每天凌晨两点执行
	private void dataImport(){
		
		dataImport.deptImport();
		
		dataImport.serialImport();
		
		List<VUser> vlist= vUserMapper.selectByExample(new VUserExample());
		for(VUser vu: vlist){
			try{
				dataImport.userImport(vu);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("userImport error userid:"+vu.getId(),e);
			}
		}
		
		dataImport.userPostInfo();
	}
}

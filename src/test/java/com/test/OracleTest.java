package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.data.model.entity.VUser;
import com.data.model.entity.VUserExample;
import com.data.model.mapper.VUserMapper;
import com.data.model.service.DataImport;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/spring/spring-*.xml")
public class OracleTest {

	@Autowired
	DataImport dataImport;
	
	@Autowired
	private VUserMapper vUserMapper;
	
	@Test
	public void start() {

		dataImport.deptImport();
		dataImport.serialImport();
		List<VUser> vlist= vUserMapper.selectByExample(new VUserExample());
		for(VUser vu: vlist){
			dataImport.userImport(vu);
		}
		dataImport.userPostInfo();

	}
}

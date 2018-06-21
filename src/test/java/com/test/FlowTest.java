package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dynastech.flow.entity.Activity;
import com.dynastech.flow.service.IFlowService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/spring/spring-*.xml")
public class FlowTest {

	@Autowired
	IFlowService flowService;

	@Test
	public void start() {

		/*try {

			List<String> list = new ArrayList<>();

			list.add("9F384FA6-4727-4BC6-B611-DAE615D90051");

			flowService.startProcess("12322", "apply", "123", list);

		} catch (Exception e) {
			e.printStackTrace();
		}*/

	}

	@Test
	public void query() {

	}
	
	@Test
	public void complete() {

		try {

			List<String> list = new ArrayList<>();

			list.add("9F384FA6-4727-4BC6-B611-DAE615D90051");

			flowService.complete("","f07c1fd8-6734-462d-9df7-8c04a289aaaf", true, list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void addPreNode() {
		
		List<String> list = new ArrayList<>();
		list.add("AFE4280D-43C3-4B7D-BE8A-6233F698EB2F");
		list.add("12EEE6EA-7415-4FED-BB0A-A9C07F811976");
		
		Activity a=new Activity();
		
		a.setKey("jiaqian");
		a.setName("本部门领导加签");
		a.setMultiInstance(true);
		a.setCandidateType(Activity.ACTIVITY_CANDIDATE_TYPE_USER);
		
		flowService.addPreNode("775cd7dc-3caa-4b79-9fd8-95e3cc4586b7", a, list);

	}
}

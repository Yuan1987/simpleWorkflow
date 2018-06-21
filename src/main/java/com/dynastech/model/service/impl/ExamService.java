package com.dynastech.model.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynastech.model.entity.Exam;
import com.dynastech.model.entity.ExamExample;
import com.dynastech.model.mapper.ExamMapper;
import com.dynastech.model.service.IExamService;


@Service
public class ExamService implements IExamService{
	
	@Autowired
	private ExamMapper examMapper;

	@Override
	public List<Exam> getExamList(ExamExample ex) {
		
		return examMapper.selectByExample(ex);
	}

	@Override
	public int add(Exam ex) {
		
		return examMapper.insertSelective(ex);
	}

	@Override
	public int update(Exam ex) {
		return examMapper.updateByPrimaryKeySelective(ex);
	}

	@Override
	public Exam findById(String id) {
		return examMapper.selectByPrimaryKey(id);
	}

	@Override
	public int batchDelete(String ...ids) {
		
		Exam ex = new Exam();
		ex.setIsdeleted(true);
	
		ExamExample example = new ExamExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		
		return examMapper.updateByExampleSelective(ex, example);
	}
	

}

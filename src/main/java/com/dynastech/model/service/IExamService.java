package com.dynastech.model.service;

import java.util.List;

import com.dynastech.model.entity.Exam;
import com.dynastech.model.entity.ExamExample;


public interface IExamService {

	public List<Exam> getExamList(ExamExample ex);
	
	public int add(Exam ex);
	
	public int update(Exam ex);
	
	public Exam findById(String id);
	
	public int batchDelete(String ...ids);

}

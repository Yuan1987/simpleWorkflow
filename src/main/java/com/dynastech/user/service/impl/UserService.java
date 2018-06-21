package com.dynastech.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynastech.user.entity.UserCertificate;
import com.dynastech.user.entity.UserCertificateExample;
import com.dynastech.user.entity.UserProfile;
import com.dynastech.user.entity.UserResume;
import com.dynastech.user.mapper.UserCertificateMapper;
import com.dynastech.user.mapper.UserProfileMapper;
import com.dynastech.user.mapper.UserResumeMapper;
import com.dynastech.user.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserProfileMapper userProfileMapper;
	
	@Autowired
	private UserResumeMapper userResumeMapper;
	
	@Autowired
	private UserCertificateMapper certificateMapper;

	
	@Override
	public int addUserProfile(UserProfile profile) {
		return userProfileMapper.insertSelective(profile);
	}
	
	@Override
	public int updateUserProfile(UserProfile profile) {
		return userProfileMapper.updateByPrimaryKeySelective(profile);
	}
	
	@Override
	public UserProfile selectUserProfileByUserId(String userId) {
		return userProfileMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public int addUserResume(UserResume resume) {
		return userResumeMapper.insertSelective(resume);
	}
	
	@Override
	public UserResume selectUserResumeByUserId(String userId) {
		return userResumeMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public int updateUserResume(UserResume resume) {
		return userResumeMapper.updateByPrimaryKeySelective(resume);
	}
	
	@Override
	public List<UserCertificate> selectCertificateByUserId(String userId){
		
		UserCertificateExample uce=new UserCertificateExample();
		
		uce.createCriteria().andIsdeletedEqualTo(false).andUseridEqualTo(userId);
		
		return certificateMapper.selectByExample(uce);
	}
	
	@Override
	public int addCertificate(UserCertificate uc) {
		return certificateMapper.insertSelective(uc);
	}
	
	@Override
	public int updateCertificate(UserCertificate uc) {
		return certificateMapper.updateByPrimaryKeySelective(uc);
	}
	
	@Override
	public int deleteCertificate(String ...id) {
		
		List<String> ids=Arrays.asList(id);
		
		UserCertificate uc=new UserCertificate();
		uc.setIsdeleted(true);
		
		UserCertificateExample uce=new UserCertificateExample();
		
		uce.createCriteria().andIdIn(ids);
		
		return certificateMapper.updateByExampleSelective(uc, uce);
	}
	
	@Override
	public UserCertificate selectCertificateById(String id) {
		return certificateMapper.selectByPrimaryKey(id);
	}
}

package com.dynastech.user.service;

import java.util.List;

import com.dynastech.user.entity.UserCertificate;
import com.dynastech.user.entity.UserProfile;
import com.dynastech.user.entity.UserResume;


/**
 * @author  yuan
 */
public interface IUserService {
	
	/**
	 * 个人详细信息新增
	 * @param profile
	 * @return
	 */
	public int addUserProfile(UserProfile profile);
	
	/**
	 * 通过userId 查找个人详细信息
	 * @param userId
	 * @return
	 */
	public UserProfile selectUserProfileByUserId(String userId);
	
	/**
	 * 个人详细信息 修改
	 * @param profile
	 * @return
	 */
	public int updateUserProfile(UserProfile profile);
	
	/**
	 * 新增个人履历信息
	 * @param resume
	 * @return
	 */
	public int addUserResume(UserResume resume);
	
	/**
	 * 通过userId 查找个人履历信息
	 * @param userId
	 * @return
	 */
	public UserResume selectUserResumeByUserId(String userId);
	
	/**
	 * 修改 个人履历
	 * @param resume
	 * @return
	 */
	public int updateUserResume(UserResume resume);
	
	
	/**
	 * 通过userId 查询人员证书信息
	 * @param userId
	 * @return
	 */
	public List<UserCertificate> selectCertificateByUserId(String userId);
	
	/**
	 * 新增人员证书信息
	 * @param uc
	 * @return
	 */
	public int addCertificate(UserCertificate uc);
	
	
	/**
	 * 获取证书信息
	 * @param id
	 * @return
	 */
	public UserCertificate selectCertificateById(String id);
	
	/**
	 * 修改人员证书信息
	 * @param uc
	 * @return
	 */
	public int updateCertificate(UserCertificate uc);
	
	/**
	 * 通过主键删除（逻辑删除） 人员证书信息
	 * @param id
	 * @return
	 */
	public int deleteCertificate(String... id);
}

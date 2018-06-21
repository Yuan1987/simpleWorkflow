package com.data.model.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.data.model.entity.VDEPT;
import com.data.model.entity.VDEPTExample;
import com.data.model.entity.VSerial;
import com.data.model.entity.VSerialExample;
import com.data.model.entity.VUser;
import com.data.model.entity.VUserPostInfo;
import com.data.model.entity.VUserPostInfoExample;
import com.data.model.mapper.VDEPTMapper;
import com.data.model.mapper.VSerialMapper;
import com.data.model.mapper.VUserPostInfoMapper;
import com.data.model.service.DataImport;
import com.dynastech.base.global.Constants;
import com.dynastech.model.entity.UserPostInfo;
import com.dynastech.model.entity.UserPostInfoExample;
import com.dynastech.model.mapper.UserPostInfoMapper;
import com.dynastech.system.entity.Organization;
import com.dynastech.system.entity.OrganizationDepth;
import com.dynastech.system.entity.OrganizationDepthExample;
import com.dynastech.system.entity.OrganizationDepthKey;
import com.dynastech.system.entity.OrganizationExample;
import com.dynastech.system.entity.OrganizationUser;
import com.dynastech.system.entity.OrganizationUserKey;
import com.dynastech.system.entity.RoleUser;
import com.dynastech.system.entity.RoleUserKey;
import com.dynastech.system.entity.Serial;
import com.dynastech.system.entity.User;
import com.dynastech.system.mapper.OrganizationDepthMapper;
import com.dynastech.system.mapper.OrganizationMapper;
import com.dynastech.system.mapper.OrganizationUserMapper;
import com.dynastech.system.mapper.RoleUserMapper;
import com.dynastech.system.mapper.SerialMapper;
import com.dynastech.system.mapper.UserMapper;

@Service
public class DataImportImpl implements DataImport{
	
	private static Logger logger = Logger.getLogger(DataImportImpl.class);
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private OrganizationDepthMapper organizationDepthMapper;
	
	@Autowired
	private VDEPTMapper vdeptMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleUserMapper roleUserMapper;
	
	@Autowired
	private OrganizationUserMapper organizationUserMapper;
	
	@Autowired
	private VSerialMapper vSerialMapper;
	
	@Autowired
	private SerialMapper serialMapper;
	
	@Autowired
	private VUserPostInfoMapper vUserPostInfoMapper;
	
	@Autowired
	private UserPostInfoMapper userPostInfoMapper;
	

	@Override
	@Transactional
	public void deptImport() {
		
		logger.info("detpImport begin-->");
		
		List<VDEPT> deptList=vdeptMapper.selectByExample(new VDEPTExample());
		
		logger.info(String.format("detpImport dept count --> %s 条",deptList.size()));
		
		if(deptList==null||deptList.isEmpty()){
			return;
		}
		//删除全部 层级关系
		int deleteCount=organizationDepthMapper.deleteByExample(new OrganizationDepthExample());
		
		logger.info(String.format("delete organizationDepth --> %s 条",deleteCount));
		
		//插入 单条
		int i=0;
		for(VDEPT dept: deptList){
			deptImport(dept);
			i++;
		}
	
		logger.info(String.format("insert organization' -->%s 条",i));
		OrganizationExample oe=new OrganizationExample();
		oe.createCriteria().andIsdeletedEqualTo(false);
		List <Organization> orgs=organizationMapper.selectByExample(oe);
		
		OrganizationDepth od=new OrganizationDepth();
		for(Organization org:orgs){
			
			od.setChildid(org.getId());
			od.setParentid(org.getId());
			//先插入自己本身这条
			deptDepth(od);
		}
		
	}
	
	/**
	 * 单条部门插入
	 * @param dept
	 */
	private void deptImport(VDEPT dept){
		Date now =new Date();
		
		//organizationMapper.deleteByPrimaryKey(dept.getId());
		
		Organization org= organizationMapper.selectByPrimaryKey(dept.getId());
		
		if(org==null){
			org=new Organization();
			org.setId(dept.getId());
			org.setDatetimecreated(now);
			org.setDatetimemodified(now);
			org.setDisplayname(dept.getDisplayname());
			boolean isDeleted=StringUtils.equals(dept.getIsdeleted(),"1");
			org.setIsdeleted(isDeleted);
			org.setName(dept.getDisplayname());
			org.setParentid(dept.getParentid());
			organizationMapper.insert(org);
		}else{
			org.setDatetimemodified(now);
			org.setDisplayname(dept.getDisplayname());
			boolean isDeleted=StringUtils.equals(dept.getIsdeleted(),"1");
			org.setIsdeleted(isDeleted);
			org.setName(dept.getDisplayname());
			org.setParentid(dept.getParentid());
			organizationMapper.updateByPrimaryKeySelective(org);
		}
		
	};
	
	private void deptDepth(OrganizationDepth od){
		OrganizationDepthKey odk=new OrganizationDepthKey();
		odk.setChildid(od.getChildid());
		odk.setParentid(od.getParentid());
		organizationDepthMapper.deleteByPrimaryKey(odk);
		organizationDepthMapper.insertSelective(od);
		
		Organization porg= organizationMapper.selectByPrimaryKey(od.getParentid());
		if(porg!=null&&!StringUtils.isBlank(porg.getParentid())){
			OrganizationDepth od2=new OrganizationDepth();
			od2.setChildid(porg.getId());
			od2.setParentid(porg.getParentid());
			deptDepth(od2);
		}
		
	}

	@Override
	@Transactional
	public void serialImport(){
		List<VSerial> list= vSerialMapper.selectByExample(new VSerialExample());
		
		logger.info(String.format("serialImport begin:获取到 序列  %s 条",list.size()));
		
		for(VSerial vs:list){
			serialMapper.deleteByPrimaryKey(vs.getId());
			Serial s=new Serial();
			s.setId(vs.getId());
			s.setName(vs.getName());
			s.setCode(vs.getId());
			s.setParentid("0");
			boolean isDeleted=StringUtils.equals(vs.getIsdeleted(),"1");
			s.setIsdeleted(isDeleted);
			serialMapper.insertSelective(s);
		}
		
	}

	/**
	 * 单条人员
	 * @param vu
	 */
	@Override
	@Transactional
	public void userImport(VUser vu){
		
		String userId=vu.getId();
		
		User user=userMapper.selectByPrimaryKey(userId);
		
		if(user==null){
			user=new User();
			user.setId(userId);
			user.setAccountname(vu.getAccountname());
			user.setPassword("123");
			user.setDisplayname(vu.getDisplayname());
			user.setEmail(vu.getEmail());
			user.setWorknumber(vu.getWorknumber());
			boolean isDeleted=StringUtils.equals(vu.getIsdeleted(),"1");
			user.setIsenabled(isDeleted);
			userMapper.insertSelective(user);
		}else{
			user.setAccountname(vu.getAccountname());
			user.setPassword("123");
			user.setDisplayname(vu.getDisplayname());
			user.setEmail(vu.getEmail());
			user.setWorknumber(vu.getWorknumber());
			boolean isDeleted=StringUtils.equals(vu.getIsdeleted(),"1");
			user.setIsenabled(isDeleted);
			userMapper.updateByPrimaryKeySelective(user);
		}
		
		//角色
		RoleUserKey ruk=new RoleUserKey();
		ruk.setUserid(userId);
		ruk.setOrganizationid(vu.getDeptid());
		ruk.setRoleid(Constants.ROLE_NORMAL);
		//删除普通角色
		roleUserMapper.deleteByPrimaryKey(ruk);
		//删除部门领导角色
		ruk.setRoleid(Constants.ROLE_BMLD);
		roleUserMapper.deleteByPrimaryKey(ruk);
		
		RoleUser ru=new RoleUser();
		ru.setRoleid(Constants.ROLE_NORMAL);
		ru.setUserid(userId);
		ru.setOrganizationid(vu.getDeptid());
		roleUserMapper.insertSelective(ru);
		
		if(StringUtils.equals(vu.getIsleader(), "1")){
			ru.setRoleid(Constants.ROLE_BMLD);
			ru.setUserid(userId);
			ru.setOrganizationid(vu.getDeptid());
			roleUserMapper.insertSelective(ru);
		}
		
		//组织 （由于遗留问题，保留这个数据结构）
		OrganizationUserKey ouk=new OrganizationUserKey();
		ouk.setOrganizationid(vu.getDeptid());
		ouk.setUserid(userId);
		organizationUserMapper.deleteByPrimaryKey(ouk);
		
		OrganizationUser ou=new OrganizationUser();
		ou.setUserid(userId);
		ou.setOrganizationid(vu.getDeptid());
		organizationUserMapper.insertSelective(ou);
	}
	
	@Override
	public void userPostInfo(){
		
		List<VUserPostInfo> list=vUserPostInfoMapper.selectByExample(new VUserPostInfoExample());
		
		logger.info(String.format("userPostInfo begin:获取到 现就职信息共  %s 条",list.size()));
		
		for(VUserPostInfo vupi:list){
			try{
				
				UserPostInfoExample upie=new UserPostInfoExample();
				upie.createCriteria().andUseridEqualTo(vupi.getUserid());
				userPostInfoMapper.deleteByExample(upie);
				
				UserPostInfo upi=new UserPostInfo();
				upi.setUserid(vupi.getUserid());
				
				upi.setDegree(vupi.getDegree());
				upi.setEducational(vupi.getEducational());
				upi.setGraduateschool(vupi.getGraduateschool());
				upi.setHavequalificationgrade(vupi.getHavequalificationgrade());
				upi.setHavequalificationgradetime(vupi.getHavequalificationgradetime());
				upi.setHavequalificationseq(vupi.getHavequalificationseq());
				upi.setHavequalificationseqid(vupi.getHavequalificationseqid());
				upi.setImprovementplanclosedloop(StringUtils.equals(vupi.getImprovementplanclosedloop(),"1"));
				upi.setJobsgrade(vupi.getJobsgrade());
				upi.setJobstype(vupi.getJobstype());
				upi.setJobstypeid(vupi.getJobstypeid());
				upi.setMajors(vupi.getMajors());
				upi.setNuclearresultsfront(StringUtils.equals(vupi.getNuclearresultsfront(),"1"));
				upi.setNuclearresultsfrontannual(vupi.getNuclearresultsfrontannual());
				upi.setWorkconditionsgrade(vupi.getWorkconditionsgrade());
				upi.setWorkconditionsgradeannual(vupi.getWorkconditionsgradeannual());
				upi.setWorkconditionsseq(vupi.getWorkconditionsseq());
				userPostInfoMapper.insertSelective(upi);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}

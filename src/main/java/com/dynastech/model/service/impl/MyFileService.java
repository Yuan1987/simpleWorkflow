package com.dynastech.model.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dynastech.base.file.IFileSystemService;
import com.dynastech.model.entity.AttachmentFile;
import com.dynastech.model.entity.AttachmentFileBean;
import com.dynastech.model.entity.AttachmentFileRelation;
import com.dynastech.model.entity.AttachmentFileRelationExample;
import com.dynastech.model.entity.PersonAbility;
import com.dynastech.model.entity.PersonAbilityExample;
import com.dynastech.model.entity.SysAbility;
import com.dynastech.model.mapper.AttachmentFileMapper;
import com.dynastech.model.mapper.AttachmentFileRelationMapper;
import com.dynastech.model.mapper.PersonAbilityMapper;
import com.dynastech.model.service.IMyFileService;

@Service
public class MyFileService implements IMyFileService{
	
	@Autowired
	private PersonAbilityMapper personAbilityMapper;
	
	@Autowired
	private AttachmentFileRelationMapper afrMapper;
	
	@Autowired
    private AttachmentFileMapper fileMapper;
	
	@Autowired
	private IFileSystemService fileSystemService;
	
	@Autowired
	private SysAbilityService saService;

	@Override
	public List<AttachmentFileBean> getAttachmentFileBeanList(String user,String searchText){
		return fileMapper.getAttachmentFileBean(user, searchText);
	}

	@Override
	public int uploadFile(String saId, String userId, MultipartFile file, String description) throws Exception {


			String fileId = UUID.randomUUID().toString();
			
			Date date = new Date();
			//获取原始文件名称
			String OriginalName= file.getOriginalFilename();
			//获取后缀名
			String cfileName = OriginalName.substring(OriginalName.lastIndexOf("."), OriginalName.length());
			
			String path = userId + "/" + UUID.randomUUID().toString() + cfileName;

			AttachmentFile af = new AttachmentFile();
			af.setGuid(fileId);
			af.setUserguid(userId);
			af.setIsfolder(0);
			af.setPid("");
			af.setFilename(cfileName);
			af.setFriendlyfilename(OriginalName);
			af.setFilecontenttype(file.getContentType());
			af.setFilesize(file.getSize());
			af.setFilephysicalpath(path);
			af.setIsdeleted(0);
			af.setCreatetime(date);
			af.setCreateuserid(userId);
			af.setDescription(description);
			fileMapper.insert(af);
			
			fileSystemService.save(file, path);
			
			String[] saids = saId.split(",");
			for(int i=0;i<saids.length;i++){
				String sid = saids[i]; 
				if(StringUtils.isBlank(sid)){
					continue;
				}
				String paid = UUID.randomUUID().toString();
				
				PersonAbilityExample pae=new PersonAbilityExample();
				pae.createCriteria().andSysAbilityidEqualTo(sid).andUseridEqualTo(userId);
				
				List<PersonAbility> paList= personAbilityMapper.selectByExample(pae);
				
				if(!paList.isEmpty()){//判定该人已有此能力项（从测评表添加的情况）
					paid=paList.get(0).getId();
				}else{//没有就添加个人能力项
					SysAbility sa = saService.findAbilityById(sid);
					PersonAbility pa = new PersonAbility();
					pa.setId(paid);
					pa.setDatetimecreated(date);
					pa.setDatetimemodified(date);
					pa.setName(sa.getName());
					pa.setSysAbilityid(sid);
					pa.setSerial(sa.getSerial());
					pa.setThelevel(sa.getThelevel());
					pa.setTypeid(sa.getTypeid());
					pa.setScore(sa.getScore());
					pa.setPassmark(sa.getPassmark());
					pa.setEvidence(sa.getEvidence());
					pa.setFormula(sa.getFormula());
					pa.setDescription(description);
					pa.setUserid(userId);
					personAbilityMapper.insert(pa);
				}
				AttachmentFileRelation afr = new AttachmentFileRelation();
				afr.setId(UUID.randomUUID().toString());
				afr.setYwid(paid);
				afr.setFileid(fileId);
				afrMapper.insert(afr);
			}
			
		return 1;
	}
	
	@Override
	public List<PersonAbility> findPersonAbilityByFileId(String fileId) {
		
		return personAbilityMapper.selectPersonAbilityByFileId(fileId);
	}

	@Override
	@Transactional
	public int update(String fileId,String paIds,String userId,String description){
		
		AttachmentFile file = new AttachmentFile();
		file.setGuid(fileId);
		file.setDescription(description);
		fileMapper.updateByPrimaryKeySelective(file);
		
		//现在对应的能力项
		List<String> paIdList=new Vector<>();
		personAbilityMapper.selectPersonAbilityByFileId(fileId).forEach(pa->{
			paIdList.add(pa.getId());
		});
		
		if(!paIdList.isEmpty()){
			//先删除现有的对应关系
			AttachmentFileRelationExample afre=new AttachmentFileRelationExample();
			afre.createCriteria().andFileidEqualTo(fileId).andYwidIn(paIdList);
			afrMapper.deleteByExample(afre);
		}
		//再建立新的对应关系
		String[] saids = paIds.split(",");
		for(int i=0;i<saids.length;i++){
			String sid = saids[i]; 
			String paid = UUID.randomUUID().toString();
			PersonAbilityExample pae=new PersonAbilityExample();
			pae.createCriteria().andSysAbilityidEqualTo(sid).andUseridEqualTo(userId);
			
			List<PersonAbility> paList= personAbilityMapper.selectByExample(pae);
			
			if(!paList.isEmpty()){//判定该人已有此能力项（从测评表添加的情况）
				paid=paList.get(0).getId();
			}else{//没有就添加个人能力项
				SysAbility sa = saService.findAbilityById(sid);
				PersonAbility pa = new PersonAbility();
				pa.setId(paid);
				Date date=new Date();
				pa.setDatetimecreated(date);
				pa.setDatetimemodified(date);
				pa.setName(sa.getName());
				pa.setSysAbilityid(sid);
				pa.setSerial(sa.getSerial());
				pa.setThelevel(sa.getThelevel());
				pa.setTypeid(sa.getTypeid());
				pa.setScore(sa.getScore());
				pa.setPassmark(sa.getPassmark());
				pa.setEvidence(sa.getEvidence());
				pa.setFormula(sa.getFormula());
				pa.setDescription(description);
				pa.setUserid(userId);
				personAbilityMapper.insert(pa);
			}
			AttachmentFileRelation afr = new AttachmentFileRelation();
			afr.setId(UUID.randomUUID().toString());
			afr.setYwid(paid);
			afr.setFileid(fileId);
			afrMapper.insert(afr);
		}
		
		return 1;
	}
	
	@Override
	@Transactional
	public int delete(String fileId){
		
		AttachmentFileRelationExample afre=new AttachmentFileRelationExample();
		afre.createCriteria().andFileidEqualTo(fileId);
		afrMapper.deleteByExample(afre);
		
		AttachmentFile af=new AttachmentFile();
		af.setIsdeleted(1);
		af.setGuid(fileId);
		int a=fileMapper.updateByPrimaryKeySelective(af);
		return a;
	}
}

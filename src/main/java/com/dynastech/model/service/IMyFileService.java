package com.dynastech.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dynastech.model.entity.AttachmentFileBean;
import com.dynastech.model.entity.PersonAbility;

public interface IMyFileService {
	
	public List<AttachmentFileBean> getAttachmentFileBeanList(String user,String searchText);
	
	public List<PersonAbility> findPersonAbilityByFileId(String guid);
	
	public int uploadFile(String saId,String userId,MultipartFile file,String description)throws Exception;
	
	public int update(String fileId,String saId,String userId,String description);
	
	public int delete(String fileId);

}

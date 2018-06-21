package com.dynastech.system.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynastech.base.global.Constants;
import com.dynastech.system.entity.Resource;
import com.dynastech.system.entity.ResourceExample;
import com.dynastech.system.entity.ResourceExample.Criteria;
import com.dynastech.system.mapper.ResourceMapper;
import com.dynastech.system.service.IResourceManagerService;

@Service
public class ResourceManagerServiceImpl implements IResourceManagerService {
	
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public List<Resource> selectByExample(ResourceExample example){
		return resourceMapper.selectByExample(example);
	}
	
	@Override
	public int add(Resource resource) {
		return resourceMapper.insertSelective(resource);
	}
	
	@Override
	public int update(Resource resource) {
		return resourceMapper.updateByPrimaryKeySelective(resource);
	}
	
	@Override
	public int delete(String id) {
		
		Resource re=new Resource();
		
		re.setId(id);
		re.setIsdeleted(true);
		
		return resourceMapper.updateByPrimaryKeySelective(re);
	}
	
	@Override
	public Resource findById(String id) {
		return resourceMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Resource> findResourceByUserId(String userId, String type) {
		
		if(Constants.SUPER_USERID.equals(userId)){
			//所有菜单
			ResourceExample pe =new ResourceExample();
			Criteria c=pe.createCriteria().andIdNotEqualTo("0").andIsdeletedEqualTo(false);
			
			if(!StringUtils.isBlank(type)){
				c.andTypeEqualTo(type);
			}
			return this.selectByExample(pe);
			
		}else{
			return resourceMapper.findResourceByUserId(userId, type);
		}
	}
	
	@Override
	public List<Resource> findMenus(String userId) {
		
		List<Resource> list=this.findResourceByUserId(userId, "menu");
		
		List<Resource> parentList= list.stream().distinct().filter(res->res.getParentId().equals("0")).collect(Collectors.toList());
		
		for(Resource pre:parentList){
			pre.setChildResource(getChild(pre.getId(),list));
		}
		
		Collections.sort(parentList, new Comparator<Resource>() {
		    @Override
		    public int compare(Resource a, Resource b) {
		        return a.getOrderno().compareTo(b.getOrderno());
		    }
		});
		
		return parentList;
	}
	
	private List<Resource> getChild(String id, List<Resource> allList) {
		List<Resource> childList= allList.stream().filter(re->re.getParentId().equals(id)).collect(Collectors.toList());
	    for (Resource resource : childList){
	    	resource.setChildResource(getChild(resource.getId(), allList));
	    }
	    if (childList.size() == 0) {
	        return null;
	    }
	    
	    Collections.sort(childList, new Comparator<Resource>() {
		    @Override
		    public int compare(Resource a, Resource b) {
		        return a.getOrderno().compareTo(b.getOrderno());
		    }
		});
	    return childList;
	}
	
}

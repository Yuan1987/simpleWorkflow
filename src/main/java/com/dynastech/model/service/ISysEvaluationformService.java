package com.dynastech.model.service;

import com.dynastech.model.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ganlu on 2017/9/18.
 */
public interface ISysEvaluationformService {



    /**
     * 功能：查询评测列表
     * @param sysEvaluationFormExample
     * @return
     */
    public List<SysEvaluationForm> getSysEvaluationFormList(SysEvaluationFormExample sysEvaluationFormExample);

    /**
     * 获取测评表
     * @param id
     * @return
     */
    public  SysEvaluationForm getSysEvaluationForm(String id);

    /**
     * 获取能力项
     * @param id
     * @return
     */
    public  List<SysAbility> getSysAbilities(String id);


    /**
     * 获取能力项
     * @param id
     * @return
     */
    public  AttachmentFile getAttachmentFile(String id);

    /**
     * 新增
     * @param sysEvaluationForm
     * @return
     */
    public int addSysEvaluationForm(SysEvaluationForm sysEvaluationForm);

    /**
     * 保存评测表和能力项
     * @param sysEvaluationForm
     * @param sysAbilities
     * @throws Exception
     */
    public void addSysEvaluationFormAndAbility(MultipartFile file,SysEvaluationForm sysEvaluationForm, List<SysAbility> sysAbilities)
            throws RuntimeException;

    /**
     * 修改能力项和测评表
     * @param sysEvaluationForm
     * @param sysAbilities
     * @throws Exception
     */
    public void editSysEvaluationFormAndAbility(MultipartFile file,SysEvaluationForm sysEvaluationForm, List<SysAbility> sysAbilities)
            throws RuntimeException;

    /**
     * 修改
     * @param sysEvaluationForm
     * @return
     */
    public int editSysEvaluationForm(SysEvaluationForm sysEvaluationForm);

    /**
     * 逻辑删除
     * @param guid
     * @return
     */
    public void deleteSysEvaluationFormAndAbility(String guid) throws RuntimeException;
    
    public int updateStatus(SysEvaluationForm sysEvaluationForm);

}

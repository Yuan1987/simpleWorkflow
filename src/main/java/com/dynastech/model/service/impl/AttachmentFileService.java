package com.dynastech.model.service.impl;

import com.dynastech.model.entity.AttachmentFile;
import com.dynastech.model.mapper.AttachmentFileMapper;
import com.dynastech.model.service.IAttachmentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ganlu on 2017/9/22.
 */
@Service
public class AttachmentFileService implements IAttachmentFileService {
 @Autowired
 private AttachmentFileMapper fileMapper;

    @Override
    public int addAttachmentFile(AttachmentFile attachmentFile) {
        return fileMapper.insert(attachmentFile);
    }

    @Override
    public int updateAttachmentFile(AttachmentFile attachmentFile) {
        return fileMapper.updateByPrimaryKey(attachmentFile);
    }

    @Override
    public int deleteAttachmentFile(String guid) {
        return fileMapper.deleteByPrimaryKey(guid);
    }

    @Override
    public AttachmentFile getAttachmentFile(String guid) {
        return fileMapper.selectByPrimaryKey(guid);
    }
}

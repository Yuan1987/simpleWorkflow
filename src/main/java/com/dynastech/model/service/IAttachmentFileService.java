package com.dynastech.model.service;

import com.dynastech.model.entity.AttachmentFile;

/**
 * Created by ganlu on 2017/9/22.
 */
public interface IAttachmentFileService {

    int addAttachmentFile(AttachmentFile attachmentFile);

    int updateAttachmentFile(AttachmentFile attachmentFile);

    int deleteAttachmentFile(String guid);

    AttachmentFile getAttachmentFile(String guid);

}

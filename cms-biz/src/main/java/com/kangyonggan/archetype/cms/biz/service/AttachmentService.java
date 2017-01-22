package com.kangyonggan.archetype.cms.biz.service;

import com.kangyonggan.archetype.cms.model.vo.Attachment;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/1/21
 */
public interface AttachmentService {

    /**
     * 批量保存附件
     *
     * @param sourceId
     * @param attachments
     */
    void saveAttachments(Long sourceId, List<Attachment> attachments);

    /**
     * 查找附件
     *
     * @param sourceId
     * @param type
     * @return
     */
    List<Attachment> findAttachmentsBySourceIdAndType(Long sourceId, String type);

    /**
     * 删除附件
     *
     * @param id
     * @param sourceId
     * @param type
     */
    void deleteAttachment(Long id, Long sourceId, String type);
}

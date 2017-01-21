package com.kangyonggan.archetype.cms.mapper;

import com.kangyonggan.archetype.cms.model.vo.Attachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentMapper extends MyMapper<Attachment> {

    /**
     * 批量保存附件
     *
     * @param sourceId
     * @param attachments
     */
    void insertAttachments(@Param("sourceId") Long sourceId, @Param("attachments") List<Attachment> attachments);

}
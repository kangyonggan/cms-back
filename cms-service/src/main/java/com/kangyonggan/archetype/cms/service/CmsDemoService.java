package com.kangyonggan.archetype.cms.service;

import com.kangyonggan.archetype.cms.model.annotation.Param;
import com.kangyonggan.archetype.cms.model.dto.request.DemoRequest;
import com.kangyonggan.archetype.cms.model.dto.response.CommonResponse;

/**
 * @author kangyonggan
 * @since 2017/1/18
 */
public interface CmsDemoService {

    /**
     * 请求参数是引用类型
     *
     * @param request
     * @return
     */
    CommonResponse hello(@Param(name = "request") DemoRequest request);

    /**
     * 请求参数是基本类型
     *
     * @param name
     * @param value
     * @return
     */
    CommonResponse world(String name, @Param(name = "value", min = 0, max = 99) int value);

}

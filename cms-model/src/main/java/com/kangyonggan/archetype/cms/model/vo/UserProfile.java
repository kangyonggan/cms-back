package com.kangyonggan.archetype.cms.model.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "user_profile")
@Data
public class UserProfile implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 中头像
     */
    @Column(name = "medium_avatar")
    private String mediumAvatar;

    /**
     * 大头像
     */
    @Column(name = "large_avatar")
    private String largeAvatar;

    /**
     * 性别:{0:男, 1:女}
     */
    private Byte sex;

    /**
     * 座机号
     */
    private String phone;

    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 个人网站
     */
    @Column(name = "web_site")
    private String webSite;

    /**
     * 国家代码
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 国家名称
     */
    @Column(name = "country_name")
    private String countryName;

    /**
     * 城市代码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 地区代码
     */
    @Column(name = "district_code")
    private String districtCode;

    /**
     * 地区名称
     */
    @Column(name = "district_name")
    private String districtName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 逻辑删除:{0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}
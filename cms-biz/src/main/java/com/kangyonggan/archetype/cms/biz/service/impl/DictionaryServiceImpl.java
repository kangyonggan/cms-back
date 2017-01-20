package com.kangyonggan.archetype.cms.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.archetype.cms.biz.service.DictionaryService;
import com.kangyonggan.archetype.cms.biz.util.StringUtil;
import com.kangyonggan.archetype.cms.mapper.DictionaryMapper;
import com.kangyonggan.archetype.cms.model.annotation.CacheDelete;
import com.kangyonggan.archetype.cms.model.annotation.CacheGetOrSave;
import com.kangyonggan.archetype.cms.model.annotation.LogTime;
import com.kangyonggan.archetype.cms.model.constants.AppConstants;
import com.kangyonggan.archetype.cms.model.vo.Dictionary;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/1/20
 */
@Service
public class DictionaryServiceImpl extends BaseService<Dictionary> implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    @LogTime
    public List<Dictionary> searchDictionsries(int pageNum, String type, String value) {
        Example example = new Example(Dictionary.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(type)) {
            criteria.andEqualTo("type", type);
        }

        if (StringUtils.isNotEmpty(value)) {
            criteria.andLike("value", StringUtil.toLikeString(value));
        }

        example.setOrderByClause("sort desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return super.selectByExample(example);
    }

    @Override
    @LogTime
    @CacheGetOrSave("dictionary:id:{0}")
    public Dictionary findDictionaryById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    @LogTime
    @CacheDelete("dictionary:id:{0:id}")
    public void updateDictionary(Dictionary dictionary) {
        super.updateByPrimaryKeySelective(dictionary);
    }

    @Override
    @LogTime
    public void saveDictionary(Dictionary dictionary) {
        super.insertSelective(dictionary);
    }

    @Override
    @LogTime
    public boolean existsDictionaryCode(String code) {
        Dictionary dictionary = new Dictionary();
        dictionary.setCode(code);

        return dictionaryMapper.selectCount(dictionary) == 1;
    }
}

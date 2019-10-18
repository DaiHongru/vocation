package com.freework.vocation.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freework.common.loadon.cache.JedisUtil;
import com.freework.common.loadon.result.entity.ResultVo;
import com.freework.common.loadon.result.enums.ResultStatusEnum;
import com.freework.common.loadon.result.util.ResultUtil;
import com.freework.vocation.dao.VocationCategoryDao;
import com.freework.vocation.entity.VocationCategory;
import com.freework.vocation.exceptions.VocationCategoryOperationException;
import com.freework.vocation.service.VocationCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daihongru
 */
@Service
public class VocationCategoryServiceImpl implements VocationCategoryService {
    @Autowired(required = false)
    private VocationCategoryDao vocationCategoryDao;
    @Autowired(required = false)
    private JedisUtil.Keys jedisKeys;
    @Autowired(required = false)
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(VocationCategoryServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVo<VocationCategory> queryVocationCategoryTop() {
        String key = VOCATION_CATEGORY_TOP_KEY;
        List<VocationCategory> vocationCategoryList = null;
        ObjectMapper mapper = new ObjectMapper();
        if (!jedisKeys.exists(key)) {
            vocationCategoryList = vocationCategoryDao.queryVocationCategoryTop();
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(vocationCategoryList);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, VocationCategory.class);
            try {
                vocationCategoryList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            }
        }
        if (vocationCategoryList.size() == 0 || vocationCategoryList == null) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        } else {
            return ResultUtil.success(vocationCategoryList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVo<VocationCategory> queryVocationCategorySecond(Integer parentId) {
        if (parentId == null || parentId <= 0) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        List<VocationCategory> vocationCategoryList = null;
        ObjectMapper mapper = new ObjectMapper();
        String key = VOCATION_CATEGORY_SECOND_KEY + "_ParentId" + parentId;
        if (!jedisKeys.exists(key)) {
            vocationCategoryList = vocationCategoryDao.queryVocationCategorySecond(parentId);
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(vocationCategoryList);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, VocationCategory.class);
            try {
                vocationCategoryList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new VocationCategoryOperationException(e.getMessage());
            }
        }
        if (vocationCategoryList.size() == 0 || vocationCategoryList == null) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        } else {
            return ResultUtil.success(vocationCategoryList);
        }
    }
}

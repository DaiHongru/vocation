package com.freework.vocation.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freework.common.loadon.cache.JedisUtil;
import com.freework.vocation.client.vo.VocationCategoryVo;
import com.freework.vocation.dao.VocationCategoryDao;
import com.freework.vocation.entity.VocationCategory;
import com.freework.vocation.exceptions.VocationCategoryOperationException;
import com.freework.vocation.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daihongru
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired(required = false)
    private VocationCategoryDao vocationCategoryDao;
    @Autowired(required = false)
    private JedisUtil.Keys jedisKeys;
    @Autowired(required = false)
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<VocationCategoryVo> queryVocationCategoryTop() {
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
            return null;
        } else {
            List<VocationCategoryVo> VocationCategoryVoList = vocationCategoryList.stream().map(e -> {
                VocationCategoryVo outPut = new VocationCategoryVo();
                BeanUtils.copyProperties(e, outPut);
                return outPut;
            }).collect(Collectors.toList());
            return VocationCategoryVoList;
        }
    }
}

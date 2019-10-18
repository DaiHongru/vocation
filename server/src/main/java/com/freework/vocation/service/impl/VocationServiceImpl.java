package com.freework.vocation.service.impl;

import com.freework.common.loadon.cache.JedisUtil;
import com.freework.common.loadon.result.entity.ResultVo;
import com.freework.common.loadon.result.enums.ResultStatusEnum;
import com.freework.common.loadon.result.util.ResultUtil;
import com.freework.common.loadon.util.JsonUtil;
import com.freework.enterprise.client.feign.EnterpriseClient;
import com.freework.enterprise.client.key.EnterpriseRedisKey;
import com.freework.enterprise.client.vo.EnterpriseVo;
import com.freework.vocation.client.vo.VocationVo;
import com.freework.vocation.dao.VocationCategoryDao;
import com.freework.vocation.dao.VocationDao;
import com.freework.vocation.entity.Vocation;
import com.freework.vocation.service.VocationService;
import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daihongru
 */
@Service
public class VocationServiceImpl implements VocationService {
    private static Logger logger = LoggerFactory.getLogger(VocationServiceImpl.class);
    @Autowired(required = false)
    private VocationDao vocationDao;
    @Autowired(required = false)
    private VocationCategoryDao vocationCategoryDao;
    @Autowired(required = false)
    private JedisUtil.Keys jedisKeys;
    @Autowired(required = false)
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private EnterpriseClient enterpriseClient;

    @Override
    public ResultVo queryEnterpriseVocationList(Integer enterpriseId) {
        if (enterpriseId == null || enterpriseId <= 0) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        Vocation vocation = new Vocation();
        vocation.setEnterpriseId(enterpriseId);
        List<Vocation> vocationList = vocationDao.queryVocationByRequirement(vocation);
        if (vocationList == null || vocationList.size() == 0) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        } else {
            return ResultUtil.success(vocationList);
        }
    }

    @Override
    public ResultVo queryVocationById(Integer vocationId) {
        if (vocationId == null || vocationId <= 0) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        Vocation v = new Vocation();
        v.setVocationId(vocationId);
        List<Vocation> vocationList = vocationDao.queryVocationByRequirement(v);
        if (vocationList == null || vocationList.size() == 0) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        } else {
            VocationVo vocationVo = new VocationVo();
            BeanUtils.copyProperties(vocationList.get(0), vocationVo);
            vocationVo.setVocationCategoryName(
                    vocationCategoryDao.queryVocationCategoryById(vocationVo.getVocationCategoryId())
                            .getVocationCategoryName());
            return ResultUtil.success(vocationVo);
        }
    }

    @Override
    public ResultVo searchVocation(Vocation vocation,Integer pageNum, Integer pageSize) {
        if(vocation==null || pageNum == null || pageSize == null){
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Vocation> vocationList=vocationDao.queryVocationByRequirement(vocation);
        if (vocationList == null || vocationList.size() == 0) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        }
        List<VocationVo> VocationVoList = vocationList.stream().map(e -> {
            VocationVo outPut = new VocationVo();
            BeanUtils.copyProperties(e, outPut);
            EnterpriseVo enterpriseVo = enterpriseClient.getEnterpriseById(e.getEnterpriseId());
            outPut.setLogo(enterpriseVo.getLogo());
            outPut.setEnterpriseName(enterpriseVo.getEnterpriseName());
            return outPut;
        }).collect(Collectors.toList());
        return ResultUtil.success(VocationVoList);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public ResultVo queryVocationByPagination(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        List<Vocation> vocationList = null;
        PageHelper.startPage(pageNum, pageSize);
        vocationList = vocationDao.queryVocationAll();
        if (vocationList == null || vocationList.size() == 0) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        }
        List<VocationVo> VocationVoList = vocationList.stream().map(e -> {
            VocationVo outPut = new VocationVo();
            BeanUtils.copyProperties(e, outPut);
            EnterpriseVo enterpriseVo = enterpriseClient.getEnterpriseById(e.getEnterpriseId());
            outPut.setLogo(enterpriseVo.getLogo());
            outPut.setEnterpriseName(enterpriseVo.getEnterpriseName());
            return outPut;
        }).collect(Collectors.toList());
        return ResultUtil.success(VocationVoList);
    }

    @Override
    public ResultVo updateVocation(Vocation vocation, String token) {
        if (vocation == null || token == null) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        Integer enterpriseId = getCurrentEnterpriseId(token);
        if (enterpriseId == null) {
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        vocation.setEnterpriseId(enterpriseId);
        vocation.setLastEditTime(new Date());
        try {
            int judgeNum = vocationDao.updateVocation(vocation);
            if (judgeNum <= 0) {
                return ResultUtil.error(ResultStatusEnum.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("修改招聘信息时出现异常" + e.getMessage());
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public ResultVo deleteVocation(Integer vocationId, String token) {
        if (vocationId == null || token == null) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        Integer enterpriseId = getCurrentEnterpriseId(token);
        if (enterpriseId == null) {
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        Vocation vocation = new Vocation();
        vocation.setVocationId(vocationId);
        vocation.setEnterpriseId(enterpriseId);
        try {
            int judgeNum = vocationDao.deleteVocation(vocation);
            if (judgeNum <= 0) {
                return ResultUtil.error(ResultStatusEnum.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("删除招聘信息时出现异常：" + e.getMessage());
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public ResultVo updateVocationStatus(Integer vocationId, String token) {
        if (vocationId == null || token == null) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        Integer enterpriseId = getCurrentEnterpriseId(token);
        if (enterpriseId == null) {
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        Vocation vocation = new Vocation();
        vocation.setVocationId(vocationId);
        vocation.setEnterpriseId(enterpriseId);
        vocation.setLastEditTime(new Date());
        try {
            int judgeNum = vocationDao.updateVocationStatus(vocation);
            if (judgeNum <= 0) {
                return ResultUtil.error(ResultStatusEnum.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("修改招聘信息状态时出现异常：" + e.getMessage());
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultUtil.success();
    }


    @Override
    public ResultVo insertVocation(Vocation vocation, String token) {
        if (vocation == null || token == null) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        Integer enterpriseId = getCurrentEnterpriseId(token);
        if (enterpriseId == null) {
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        vocation.setEnterpriseId(enterpriseId);
        vocation.setStatus(1);
        vocation.setCreateTime(new Date());
        vocation.setLastEditTime(new Date());
        System.out.println(vocation.getPlace());
        try {
            int judgeNum = vocationDao.addVocation(vocation);
            if (judgeNum <= 0) {
                logger.error("创建招聘信息时操作数据库失败");
                return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("创建招聘信息时出现异常：" + e.getMessage());
            return ResultUtil.error(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 获取的当前登录企业ID
     *
     * @param token
     * @return
     */
    private Integer getCurrentEnterpriseId(String token) {
        String enterpriseStr = jedisStrings.get(EnterpriseRedisKey.LOGIN_KEY + token);
        EnterpriseVo enterpriseVo = null;
        try {
            enterpriseVo = JsonUtil.jsonToObject(enterpriseStr, EnterpriseVo.class);
        } catch (Exception e) {
            logger.error("从redis中获取的当前登录的企业信息转换object异常：" + e.getMessage());
            return null;
        }
        return enterpriseVo.getEnterpriseId();
    }
}

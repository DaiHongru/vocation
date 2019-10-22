package com.freework.vocation.service.impl;

import com.freework.vocation.client.vo.VocationVo;
import com.freework.vocation.dao.VocationDao;
import com.freework.vocation.entity.Vocation;
import com.freework.vocation.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daihongru
 */
@Service
public class ClientServiceImpl implements ClientService {
    private static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired(required = false)
    private VocationDao vocationDao;


    @Override
    public VocationVo getVocationInfo(Integer vocationId) {
        Vocation vocation = new Vocation();
        vocation.setVocationId(vocationId);
        List<Vocation> vocationList = vocationDao.queryVocationByRequirement(vocation);
        VocationVo vocationVo = new VocationVo();
        BeanUtils.copyProperties(vocationList.get(0), vocationVo);
        return vocationVo;
    }
}

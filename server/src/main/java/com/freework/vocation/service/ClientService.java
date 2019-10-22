package com.freework.vocation.service;

import com.freework.vocation.client.vo.VocationVo;
import org.springframework.stereotype.Service;

/**
 * @author daihongru
 */
@Service
public interface ClientService {
    /**
     * 查询招聘信息
     *
     * @param vocationId
     * @return
     */
    VocationVo getVocationInfo(Integer vocationId);
}

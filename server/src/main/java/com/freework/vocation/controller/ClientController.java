package com.freework.vocation.controller;

import com.freework.vocation.client.vo.VocationVo;
import com.freework.vocation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daihongru
 */
@RestController
@RequestMapping(value = "client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    /**
     * 查询招聘信息
     *
     * @param vocationId
     * @return
     */
    @PostMapping("getVocationInfo")
    public VocationVo getVocationInfo(@RequestBody Integer vocationId) {
        return clientService.getVocationInfo(vocationId);
    }
}

package com.freework.vocation.controller;

import com.freework.common.loadon.result.entity.ResultVo;
import com.freework.vocation.entity.Vocation;
import com.freework.vocation.service.VocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author daihongru
 */
@RestController
public class VocationController {
    @Autowired
    private VocationService vocationService;

    /**
     * 根据岗位ID查询招聘详情
     *
     * @param vocationId
     * @return
     */
    @GetMapping(value = "detail/{vocationId}")
    public ResultVo getVocationDetailById(@PathVariable Integer vocationId) {
        return vocationService.queryVocationById(vocationId);
    }

    /**
     * 分页查询招聘信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "page/{pageNum}/{pageSize}")
    public ResultVo getVocationByPagination(@PathVariable Integer pageNum,
                                            @PathVariable Integer pageSize) {
        return vocationService.queryVocationByPagination(pageNum, pageSize);
    }

    @PostMapping(value = "searchs")
    public ResultVo searchVocation(@RequestBody Vocation vocation, HttpServletRequest request) {
        Integer pageNum = request.getIntHeader("pageNum");
        Integer pageSize = request.getIntHeader("pageSize");
        return vocationService.searchVocation(vocation, pageNum, pageSize);
    }

    /**
     * 根据企业ID查询的企业发布的岗位招聘信息
     */
    @GetMapping(value = "list/{enterpriseId}")
    public ResultVo getEnterpriseVocationList(@PathVariable Integer enterpriseId) {
        return vocationService.queryEnterpriseVocationList(enterpriseId);
    }

    /**
     * 修改招聘信息
     *
     * @param vocation
     * @return
     */
    @PutMapping(value = "edit")
    public ResultVo updateVocation(@RequestBody Vocation vocation, HttpServletRequest request) {
        String token = request.getHeader("etoken");
        return vocationService.updateVocation(vocation, token);
    }

    /**
     * 删除招聘信息
     *
     * @param vocationId
     * @param request
     * @return
     */
    @DeleteMapping(value = "edit")
    public ResultVo deleteVocation(Integer vocationId, HttpServletRequest request) {
        String token = request.getHeader("etoken");
        return vocationService.deleteVocation(vocationId, token);
    }

    /**
     * 添加新的岗位招聘信息
     *
     * @param vocation
     * @param request
     * @return
     */
    @PostMapping(value = "edit")
    public ResultVo insertVocation(@RequestBody Vocation vocation, HttpServletRequest request) {
        String token = request.getHeader("etoken");
        return vocationService.insertVocation(vocation, token);
    }

    /**
     * 修改招聘状态
     *
     * @param vocationId
     * @param request
     * @return
     */
    @PutMapping(value = "edit/status")
    public ResultVo updateVocationStatus(Integer vocationId, HttpServletRequest request) {
        String token = request.getHeader("etoken");
        return vocationService.updateVocationStatus(vocationId, token);
    }
}

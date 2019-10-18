package com.freework.vocation.service;

import com.freework.common.loadon.result.entity.ResultVo;
import com.freework.vocation.entity.Vocation;
import org.springframework.stereotype.Service;

/**
 * @author daihongru
 */
@Service
public interface VocationService {
    /**
     * 根据企业ID查询岗位列表
     *
     * @param enterpriseId
     * @return
     */
    ResultVo queryEnterpriseVocationList(Integer enterpriseId);

    /**
     * 根据岗位ID查询岗位详情
     *
     * @param vocationId
     * @return
     */
    ResultVo queryVocationById(Integer vocationId);

    /**
     * 搜索符合条件的招聘信息
     *
     * @param vocation
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultVo searchVocation(Vocation vocation, Integer pageNum, Integer pageSize);

    /**
     * 分页查询招聘信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultVo queryVocationByPagination(Integer pageNum, Integer pageSize);

    /**
     * 更改招聘信息
     *
     * @param vocation
     * @param token
     * @return
     */
    ResultVo updateVocation(Vocation vocation, String token);

    /**
     * 删除招聘信息
     *
     * @param vocationId
     * @param token
     * @return
     */
    ResultVo deleteVocation(Integer vocationId, String token);

    /**
     * 修改招聘信息状态
     *
     * @param vocationId
     * @param token
     * @return
     */
    ResultVo updateVocationStatus(Integer vocationId, String token);

    /**
     * 新增招聘岗位
     *
     * @param vocation
     * @param token
     * @return
     */
    ResultVo insertVocation(Vocation vocation, String token);
}

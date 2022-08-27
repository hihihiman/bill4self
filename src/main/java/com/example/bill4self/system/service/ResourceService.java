package com.example.bill4self.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bill4self.system.dto.ResourceVo;
import com.example.bill4self.system.entity.Resource;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据角色id查询拥有的资源
     *
     * @param roleId
     * @return
     */
    List<ResourceVo> listResourceByRoleId(Long roleId);
}

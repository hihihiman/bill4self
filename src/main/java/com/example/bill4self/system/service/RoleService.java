package com.example.bill4self.system.service;

import com.example.bill4self.base.service.MyService;
import com.example.bill4self.system.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
public interface RoleService extends MyService<Role> {

    /**
     * 获得权限列表
     *
     * @return
     */
    List<Role> getRoleList();

    /**
     * 新增角色和角色所具有的资源
     *
     * @param role
     * @return
     */
    boolean saveRole(Role role);

    /**
     * 修改角色和角色所具有的资源
     *
     * @param role
     * @return
     */
    boolean updateRole(Role role);
}

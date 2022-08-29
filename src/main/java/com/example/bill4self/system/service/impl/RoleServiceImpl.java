package com.example.bill4self.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bill4self.system.entity.Role;
import com.example.bill4self.system.entity.RoleResource;
import com.example.bill4self.system.mapper.RoleMapper;
import com.example.bill4self.system.mapper.RoleResourceMapper;
import com.example.bill4self.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    RoleResourceMapper roleResourceMapper;

    @Override
    public List<Role> getRoleList() {
        return list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRole(Role role) {
        save(role);
        final Long roleId = role.getRoleId();
        final List<Long> resourceIds = role.getResourceIds();
        if (CollectionUtil.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                final RoleResource roleResource = new RoleResource()
                        .setRoleId(roleId)
                        .setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }
        }
        return true;
    }

    @Override
    public boolean updateRole(Role role) {
        updateById(role);
        final Long roleId = role.getRoleId();

        //先删除后新增
        roleResourceMapper.delete(Wrappers.<RoleResource>lambdaQuery().eq(RoleResource::getRoleId, roleId));

        final List<Long> resourceIds = role.getResourceIds();
        if (CollectionUtil.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                final RoleResource roleResource = new RoleResource()
                        .setRoleId(roleId)
                        .setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }
        }
        return true;
    }
}

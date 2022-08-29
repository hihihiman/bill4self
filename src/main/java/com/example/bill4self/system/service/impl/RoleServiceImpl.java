package com.example.bill4self.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bill4self.system.entity.Role;
import com.example.bill4self.system.mapper.RoleMapper;
import com.example.bill4self.system.service.RoleService;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Role> getRoleList() {
        return list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
    }
}

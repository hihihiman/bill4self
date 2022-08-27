package com.example.bill4self.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bill4self.system.dto.ResourceVo;
import com.example.bill4self.system.entity.Resource;
import com.example.bill4self.system.mapper.ResourceMapper;
import com.example.bill4self.system.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<ResourceVo> listResourceByRoleId(Long roleId) {
        // 一级目录
        QueryWrapper<Resource> query = Wrappers.query();
        query.eq("rr.role_id", roleId).isNull("re.parent_id");
        List<ResourceVo> resourceVos = baseMapper.listResource(query);
        resourceVos.forEach(resourceVo -> {
            // 二级目录
            Long resourceId = resourceVo.getResourceId();
            QueryWrapper<Resource> subQuery = Wrappers.query();
            subQuery.eq("rr.role_id", roleId)
                    .eq("re.parent_id", resourceId);
            List<ResourceVo> subResourceVos = baseMapper.listResource(subQuery);
            if (CollectionUtil.isNotEmpty(subResourceVos)) {
                resourceVo.setSubs(subResourceVos);
            }
        });
        return resourceVos;
    }
}

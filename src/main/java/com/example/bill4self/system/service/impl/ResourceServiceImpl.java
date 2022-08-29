package com.example.bill4self.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bill4self.system.dto.ResourceVo;
import com.example.bill4self.system.dto.TreeVo;
import com.example.bill4self.system.entity.Resource;
import com.example.bill4self.system.mapper.ResourceMapper;
import com.example.bill4self.system.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        query.eq("rr.role_id", roleId).isNull("re.parent_id").orderByAsc("re.sort");
        List<ResourceVo> resourceVos = baseMapper.listResource(query);
        resourceVos.forEach(resourceVo -> {
            // 二级目录
            Long resourceId = resourceVo.getResourceId();
            QueryWrapper<Resource> subQuery = Wrappers.query();
            subQuery.eq("rr.role_id", roleId)
                    .eq("re.parent_id", resourceId)
                    .orderByAsc("re.sort");
            List<ResourceVo> subResourceVos = baseMapper.listResource(subQuery);
            if (CollectionUtil.isNotEmpty(subResourceVos)) {
                resourceVo.setSubs(subResourceVos);
            }
        });
        return resourceVos;
    }

    @Override
    public List<TreeVo> listResource(Long roleId, Integer flag) {
        if (roleId == null) {
            // 新增
            final LambdaQueryWrapper<Resource> wrapper = Wrappers.<Resource>lambdaQuery()
                    .isNull(Resource::getParentId)
                    .orderByAsc(Resource::getSort);
            final List<Resource> resources = list(wrapper);
            final List<TreeVo> treeVos = resources.stream().map(r -> {
                TreeVo treeVo = new TreeVo()
                        .setId(r.getResourceId())
                        .setTitle(r.getResourceName());
                final LambdaQueryWrapper<Resource> subWrapper = Wrappers.<Resource>lambdaQuery()
                        .eq(Resource::getParentId, r.getResourceId())
                        .orderByAsc(Resource::getSort);
                final List<Resource> subResources = list(subWrapper);
                if (CollectionUtil.isNotEmpty(subResources)) {
                    final List<TreeVo> children = subResources.stream().map(sub -> {
                        TreeVo subTreeVo = new TreeVo()
                                .setId(sub.getResourceId())
                                .setTitle(sub.getResourceName());
                        return subTreeVo;
                    }).collect(Collectors.toList());
                    treeVo.setChildren(children);
                }
                return treeVo;
            }).collect(Collectors.toList());
            return treeVos;
        } else {
            // 修改
            QueryWrapper<Resource> query = Wrappers.query();
            query.eq(flag==1,"rr.role_id",roleId)
                    .isNull("re.parent_id").orderByAsc("re.sort");
            final List<TreeVo> treeVos = baseMapper.listResourceByRoleId(query, roleId);
            treeVos.forEach(t -> {
                t.setChecked(false);
                QueryWrapper<Resource> subQuery = Wrappers.query();
                subQuery.eq(flag==1,"rr.role_id",roleId)
                        .eq("re.parent_id", t.getId())
                        .orderByAsc("re.sort");
                final List<TreeVo> children = baseMapper.listResourceByRoleId(subQuery, roleId);
                if (CollectionUtil.isNotEmpty(children)) {
                    t.setChildren(children);
                }
            });
            return treeVos;
        }
    }

    @Override
    public Set<String> convert(List<ResourceVo> resourceVos) {
        Set<String > module = new HashSet<>();
        resourceVos.forEach(r->{
            final String url = r.getUrl();
            if (StrUtil.isNotBlank(url)){
                module.add(url.substring(0,url.indexOf("/")));
            }
            final List<ResourceVo> subs = r.getSubs();
            if (CollectionUtil.isNotEmpty(subs)){
                subs.forEach(sub->{
                    final String subUrl = sub.getUrl();
                    if (StrUtil.isNotBlank(subUrl)){
                        module.add(subUrl.substring(0,subUrl.indexOf("/")));
                    }
                });
            }
        });
        return module;
    }
}

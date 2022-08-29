package com.example.bill4self.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.bill4self.base.mapper.MyMapper;
import com.example.bill4self.system.dto.ResourceVo;
import com.example.bill4self.system.dto.TreeVo;
import com.example.bill4self.system.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
public interface ResourceMapper extends MyMapper<Resource> {

    /**
     * 查询当前登录人的资源
     *
     * @param wrapper
     * @return
     */
    List<ResourceVo> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);

    List<TreeVo> listResourceByRoleId(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper, @Param("roleId") Long roleId);
}

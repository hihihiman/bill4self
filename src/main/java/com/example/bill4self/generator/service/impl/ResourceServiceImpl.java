package com.example.bill4self.generator.service.impl;

import com.example.bill4self.generator.entity.Resource;
import com.example.bill4self.generator.mapper.ResourceMapper;
import com.example.bill4self.generator.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

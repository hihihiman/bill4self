package com.example.bill4self.generator.service.impl;

import com.example.bill4self.generator.entity.Customer;
import com.example.bill4self.generator.mapper.CustomerMapper;
import com.example.bill4self.generator.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2022-08-26
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}

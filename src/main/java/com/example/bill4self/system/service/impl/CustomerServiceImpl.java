package com.example.bill4self.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bill4self.system.entity.Customer;
import com.example.bill4self.system.mapper.CustomerMapper;
import com.example.bill4self.system.service.CustomerService;
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

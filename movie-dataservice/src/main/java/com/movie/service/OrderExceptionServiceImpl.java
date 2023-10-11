package com.movie.service;


import com.movie.api.model.entity.OrderException;

import com.movie.mapper.OrderExceptionMapper;
import com.movie.utils.DataTimeUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@DubboService(interfaceClass = OrderExceptionService.class,version = "1.0")
public class OrderExceptionServiceImpl implements OrderExceptionService {

    @Resource
    private OrderExceptionMapper orderExceptionMapper;

    @Override
    public OrderException create(OrderException orderException) {
        orderException.setStatus(false);
        orderException.setId(UUID.randomUUID().toString());
        orderException.setCreateAt(DataTimeUtil.getNowTimeString());
        orderExceptionMapper.insert(orderException);
        return orderException;
    }

    @Override
    public List<OrderException> findAll() {
        return orderExceptionMapper.selectList(null);
    }

    @Override
    public void handleException(OrderException orderException) {
        orderException.setEndAt(DataTimeUtil.getNowTimeString());
        orderExceptionMapper.updateById(orderException);
    }

}

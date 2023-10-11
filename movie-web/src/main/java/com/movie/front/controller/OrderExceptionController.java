package com.movie.front.controller;

import com.movie.api.model.entity.OrderException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单异常上报接口
 */
@RestController
@Api(tags = "订单异常工单")
@RequestMapping("/api/oe")
public class OrderExceptionController extends BaseController{

    @PostMapping("")
    //添加异常订单
    @ApiOperation(value = "添加异常订单")
    public OrderException create(@RequestBody OrderException orderException) {
        return orderExceptionService.create(orderException);
    }

    @GetMapping("")
    //查询所有异常订单
    @ApiOperation(value = "查询所有异常订单")
    public List<OrderException> findAll() {
        return orderExceptionService.findAll();
    }

    @PutMapping("")
    //工作人员处理异常订单
    @ApiOperation(value = "工作人员处理异常订单")
    public void handle(@RequestBody OrderException orderException) {
        orderExceptionService.handleException(orderException);
    }

}

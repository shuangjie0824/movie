package com.movie.front.controller;

import com.movie.api.model.entity.Cart;
import com.movie.api.model.entity.Order;
import com.movie.api.model.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单接口
 */
@RestController
@Api(tags = "订单接口")
@RequestMapping("/api/order")
public class OrderController extends BaseController{

    @PostMapping("")
    //创建订单
    @ApiOperation(value = "创建订单")
    public void save(@RequestBody Cart cart) throws Exception {
        orderService.create(cart);
    }

    @GetMapping("")
    //查询所有订单
    @ApiOperation(value = "查询所有订单")
    public List<OrderVO> findAll() {
        return orderService.findAll();
    }

    @PutMapping("")
    // 更新订单
    @ApiOperation(value = "更新订单")
    public void update(@RequestBody Order order) {
        orderService.update(order);
    }

    @GetMapping("/user/{id}")
    //查询用户订单
    @ApiOperation(value = "查询用户订单")
    public List<OrderVO> findByUser(@PathVariable String id) {
        return orderService.findByUser(id);
    }

    @GetMapping("/pay")
    //支付订单
    @ApiOperation(value = "支付订单")
    public Order save(String id) throws Exception {
        return orderService.pay(id);
    }

}

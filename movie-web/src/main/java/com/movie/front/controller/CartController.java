package com.movie.front.controller;

import com.movie.api.model.entity.Cart;
import com.movie.api.model.vo.CartVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户购物车接口
 */
@RestController
@Api(tags = "用户购物车")
@RequestMapping("/api/cart")
public class CartController extends BaseController{

    @PostMapping
    //添加购物车
    @ApiOperation(value = "添加购物车")
    public void save(@RequestBody Cart cart) throws Exception {
        cartService.save(cart);
    }

    @GetMapping("")
    //根据用户id查询购物车
    @ApiOperation(value = "根据用户id查询购物车")
    public List<CartVO> list(@RequestParam String uid) {
        return cartService.findAllByUserId(uid);
    }

    @DeleteMapping("")
    //删除购物车
    @ApiOperation(value = "删除购物车")
    public void delete(String id) {
        cartService.deleteById(id);
    }

}

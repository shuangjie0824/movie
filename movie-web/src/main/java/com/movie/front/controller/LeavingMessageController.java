package com.movie.front.controller;

import com.movie.api.model.entity.LeavingMessage;
import com.movie.api.model.vo.ActiveUserVO;
import com.movie.api.model.vo.LeavingMessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 留言接口
 */
@RestController
@Api(tags = "影院留言")
@RequestMapping("/api/lm")
public class LeavingMessageController extends BaseController{

    @PostMapping("")
    //新增留言接口
    @ApiOperation(value = "新增留言接口")
    public void save(@RequestBody LeavingMessage leavingMessage) {
        leavingMessageService.save(leavingMessage);
    }

    @PutMapping("")
    //回复留言
    @ApiOperation(value = "回复留言")
    public void reply(@RequestBody LeavingMessage leavingMessage) {
        leavingMessageService.reply(leavingMessage);
    }

    @GetMapping("")
    //获取所有影院留言
    @ApiOperation(value = "获取所有影院留言")
    public List<LeavingMessageVO> list() {
        return leavingMessageService.findAll();
    }

    @GetMapping("/active")
    //获取活跃留言的用户
    @ApiOperation(value = "获取活跃留言的用户")
    public List<ActiveUserVO> findActiveUsers() {
        return leavingMessageService.findActiveUsers();
    }

}

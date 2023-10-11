package com.movie.front.controller;

import com.movie.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class BaseController {

    @Resource
    protected StringRedisTemplate stringRedisTemplate;

    /*
    * 活动服务
    */
    @DubboReference(interfaceClass = ActivityService.class,version = "1.0")
    protected ActivityService activityService;

    /*
     * 管理员登录服务
     */
    @DubboReference(interfaceClass = AdminService.class,version = "1.0")
    protected AdminService adminService;

    /*
     * 影片排片服务
     */
    @DubboReference(interfaceClass = ArrangementService.class,version = "1.0")
    protected ArrangementService arrangementService;

    /*
     * 影片信息服务
     */
    @DubboReference(interfaceClass = FilmService.class,version = "1.0")
    protected FilmService filmService;

    /*
     * 购物车服务
     */
    @DubboReference(interfaceClass = CartService.class,version="1.0")
    protected CartService cartService;

    /*
     * 日常工作服务
     */
    @DubboReference(interfaceClass = DailyWorkService.class,version="1.0")
    protected DailyWorkService dailyWorkService;

    /*
     * 电影评论服务
     * */
    @DubboReference(interfaceClass = FilmEvaluateService.class,version="1.0")
    protected FilmEvaluateService filmEvaluateService;

    /*
    * 影院留言服务
    * */
    @DubboReference(interfaceClass = LeavingMessageService.class,version="1.0")
    protected LeavingMessageService leavingMessageService;

    /*
    * 订单服务
    * */
    @DubboReference(interfaceClass = OrderService.class,version="1.0")
    protected OrderService orderService;

    /*
    * 异常订单处理服务
    * */
    @DubboReference(interfaceClass = OrderExceptionService.class,version="1.0")
    protected OrderExceptionService orderExceptionService;

    /*
    * 首页海报服务
    * */
    @DubboReference(interfaceClass = PosterService.class,version="1.0")
    protected PosterService posterService;

    /*
    * 活动报名服务
    * */
    @DubboReference(interfaceClass = RegistrationService.class,version="1.0")
    protected RegistrationService registrationService;

    /*
    * 权限控制服务
    * */
    @DubboReference(interfaceClass = RoleService.class,version="1.0")
    protected RoleService roleService;

    /*
    * 图片上传服务
    * */
    @DubboReference(interfaceClass = UploadService.class,version="1.0")
    protected UploadService uploadService;

    /*
    * 用户服务
    * */
    @DubboReference(interfaceClass = UserService.class,version="1.0")
    protected UserService userService;

    /*
    * 员工服务
    * */
    @DubboReference(interfaceClass = WorkerService.class,version="1.0")
    protected WorkerService workerService;

}

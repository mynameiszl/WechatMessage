package demo.wx.mp.controller;

import demo.wx.mp.pojo.MsgVO;
import demo.wx.mp.pojo.Result;
import demo.wx.mp.service.MsgVOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private MsgVOService msgVOService;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/public/queryUserAll")
    public Result queryUserAll(){
        List<MsgVO> msgVOS = msgVOService.queryAll();
        return Result.buildResult(200,"success",msgVOS);
    }

    /**
     * 获取登录用户信息?
     * 1)用户登录成功以后信息存储到了哪里
     * 2)如何获取用户登录信息
     * @return
     */
    @PostMapping("/public/getCurrentUser")
    public Result doGetUser(){
        //从Session中获取用户认证信息
        //1)Authentication 认证对象(封装了登录用户信息的对象)
        //2)SecurityContextHolder 持有登录状态的信息的对象(底层可通过session获取用户信息)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //基于认证对象获取用户身份信息
        User principal = (User)authentication.getPrincipal();
        return Result.buildResult(200,"success",principal);
    }
}

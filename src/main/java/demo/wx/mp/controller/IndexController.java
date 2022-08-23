package demo.wx.mp.controller;

import demo.wx.mp.pojo.MsgVO;
import demo.wx.mp.pojo.Result;
import demo.wx.mp.service.MsgVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {
    @Autowired
    private MsgVOService msgVOService;

    @GetMapping("/")
    public String index(){
        return "index";
    }


    /**
     * 修改用户信息
     * @param msgVO
     * @return
     */
    @PostMapping("/admin/updataUser")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")//有ADMIN权限的才能访问
    public Result updataUser(MsgVO msgVO){
        int i = msgVOService.updateById(msgVO);
        if (i>0){
            return Result.success("修改成功");
        }
        return Result.fail("修改失败");
    }

    /**
     * 删除用户信息
     * @param openId
     * @return
     */
    @PostMapping("/admin/deleteById")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")//有ADMIN权限的才能访问
    public Result deleteById(String openId){
        int i = msgVOService.deleteById(openId);
        if (i>0){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    /**
     * 添加
     * @param msgVO
     * @return
     */
    @PostMapping("/admin/insert")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")//有ADMIN权限的才能访问
    public Result insert(MsgVO msgVO){
        int i = msgVOService.insert(msgVO);
        if (i>0){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败");
    }

}

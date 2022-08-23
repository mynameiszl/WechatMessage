package demo.wx.mp.controller;
import demo.wx.mp.pojo.MsgVO;
import demo.wx.mp.pojo.Result;
import demo.wx.mp.service.MsgVOService;
import demo.wx.mp.service.VxPushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SendMessageConeroller {

    @Autowired
    private VxPushMsgService vxPushMsgService;

    @Autowired
    private MsgVOService msgVOService;

    /**
     * 通过接口发送消息
     * @return
     */
    @PostMapping("/public/sendMessage")
    public Result sendMessage(String openId){
        MsgVO msgVO = msgVOService.selectById(openId);
        if (msgVO!=null){
            vxPushMsgService.pushRenewalNotice(msgVO);
            return Result.success("发送成功");
        }
        return Result.fail("用户不存在");
    }
}

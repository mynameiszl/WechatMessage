package demo.wx.mp.service;

import demo.wx.mp.pojo.MsgVO;

public interface VxPushMsgService {

    void pushRenewalNotice(MsgVO msgVO);

    String getToken(String appid,String secret);
}

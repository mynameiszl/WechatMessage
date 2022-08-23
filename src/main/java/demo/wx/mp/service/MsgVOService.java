package demo.wx.mp.service;

import demo.wx.mp.pojo.MsgVO;

import java.util.List;

public interface MsgVOService {
    List<MsgVO> queryAll();

    List<MsgVO> queryMsgVoByName(String name);

    int updateById(MsgVO msgVO);

    int deleteById(String openId);

    int insert(MsgVO msgVO);

    MsgVO selectById(String openId);
}

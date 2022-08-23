package demo.wx.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import demo.wx.mp.mapper.MsgVOMapper;
import demo.wx.mp.pojo.MsgVO;
import demo.wx.mp.service.MsgVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgVOServiceImpl implements MsgVOService {
    @Autowired
    private MsgVOMapper msgVOMapper;

    @Override
    public List<MsgVO> queryAll() {
        return msgVOMapper.selectList(null);
    }

    @Override
    public  List<MsgVO> queryMsgVoByName(String name){
        return msgVOMapper.selectList(new QueryWrapper<MsgVO>().like("name", name));
    }

    @Override
    public  int updateById(MsgVO msgVO){
        return  msgVOMapper.updateById(msgVO);
    }

    @Override
    public int deleteById(String openId){
        return  msgVOMapper.deleteById(openId);
    }

    @Override
    public int insert(MsgVO msgVO){
        return  msgVOMapper.insert(msgVO);
    }

    @Override
    public MsgVO selectById(String openId){
        return  msgVOMapper.selectById(openId);
    }
}

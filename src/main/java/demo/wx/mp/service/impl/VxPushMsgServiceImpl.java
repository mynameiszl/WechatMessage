package demo.wx.mp.service.impl;

import com.alibaba.fastjson.JSONObject;
import demo.wx.mp.pojo.MsgVO;
import demo.wx.mp.service.VxPushMsgService;
import demo.wx.mp.util.DateUtil;
import demo.wx.mp.util.HttpUtil;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-12-10 14:40
 */
@Service
public class VxPushMsgServiceImpl implements VxPushMsgService {
 
    private static final Logger log = LoggerFactory.getLogger(VxPushMsgServiceImpl.class);

    public static final String appId = "******"; //修改自己的
    public static final String secret = "*****"; //修改自己的

    /**
     * @param msgVO
     */
    @Override
    public void pushRenewalNotice(MsgVO msgVO) {
        //1，基础配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(msgVO.getOpenId())//用户openid
                .templateId(msgVO.getTemplateId())//模版id
                .url("")//需要跳转网页URL
                .build();
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        //调用天气API
        JSONObject weather = this.getWeather(msgVO.getCity());
        //历史上的今天
        String time = this.getTime();
        //情话
        String love =this.getLove();

        //3,模版消息
        templateMessage.addData(new WxMpTemplateData("name", msgVO.getName()+","+DateUtil.getDateSx(), "#ff7875"));//用户
        templateMessage.addData(new WxMpTemplateData("time", dateFormat.format(date)+" "+ DateUtil.getWeekOfDate(new Date()), "#001EFF"));//时间
        templateMessage.addData(new WxMpTemplateData("city", weather.get("city").toString(), "#bc31fb"));//城市
        templateMessage.addData(new WxMpTemplateData("code", weather.get("adcode").toString(), "#4edbf2"));//城市code
        templateMessage.addData(new WxMpTemplateData("temp", weather.get("temperature").toString()+"°C", "#4edbf2"));//气温
        templateMessage.addData(new WxMpTemplateData("weather", weather.get("weather").toString(), "#4edbf2"));//天气
        templateMessage.addData(new WxMpTemplateData("windDirection", weather.get("winddirection").toString(), "#315efb"));//风向
        templateMessage.addData(new WxMpTemplateData("windPower", weather.get("windpower").toString(), "#315efb"));//风力描述
        templateMessage.addData(new WxMpTemplateData("humidity", weather.get("humidity").toString(), "#315efb"));//湿度值

        templateMessage.addData(new WxMpTemplateData("history", time, "#ff7875"));//历史上的今天
        templateMessage.addData(new WxMpTemplateData("love", love, "#ff7875"));//情话

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.info("微信公众号推送消息给openId:{},templateId:{},客户姓名:{}", msgVO.getOpenId(), msgVO.getTemplateId(), msgVO.getName());
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
 
    }

    /**
     * 获取天气
     * @param city
     * @return
     */
    private JSONObject getWeather(String city){
        //获取城市天气，KEY修改自己的即可
        String usrl="https://restapi.amap.com/v3/weather/weatherInfo?city={{0}}&key=KEY";
        String replace = usrl.replace("{{0}}", city);
        log.info("查询城市：{}",city);
        String rest = HttpUtil.get(replace);
        String lives = JSONObject.parseObject(rest).getJSONArray("lives").get(0).toString();
        JSONObject jsonObject = JSONObject.parseObject(lives);
        return jsonObject;
    }

    /**
     * 历史上的今天
     * @return
     */
    private String getTime(){
        String url="https://api.oick.cn/lishi/api.php";
        String rest = HttpUtil.get(url);
        String result = JSONObject.parseObject(rest).get("result").toString();
        String res = JSONObject.parseArray(result).stream().toArray()[0].toString();
        String title = JSONObject.parseObject(res).get("title").toString();
        return title;
    }
    
    /**
     * 情话
     */
    private String getLove(){
        String url="https://api.lovelive.tools/api/SweetNothings";
        return HttpUtil.get(url);
    }


    /**
     *
     * @param appid
     * @param secret
     * @return
     */
    @Override
    public String getToken(String appid,String secret){
        String url="https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> map = new HashMap<>();
        map.put("grant_type","client_credential");
        map.put("appid",appid);
        map.put("secret",secret);
        String json = HttpUtil.getMap(url, map);
        return JSONObject.parseObject(json).get("access_token").toString();
    }
}
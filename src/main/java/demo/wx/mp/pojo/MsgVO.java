package demo.wx.mp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("wx_user")
public class MsgVO {
    @TableField(value = "name")
    private String name;
    @TableId(value = "open_id")
    private String openId;
    @TableField(value = "template_id")
    private String templateId;
    @TableField(value = "city")
    private String city;
    @TableField(value = "birthday")
    private Date birthday;
    @TableField(value = "disable")
    private boolean disable;
    @TableField(value = "loading")
    private boolean loading;
}

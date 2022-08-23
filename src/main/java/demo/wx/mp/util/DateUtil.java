package demo.wx.mp.util;

import java.util.Calendar;
import java.util.Date;

/**
 *  @Author: zhanglin
 *  @Date: 2022/8/22 09:38
 */
public class DateUtil {
    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getDateSx(){
        String nihao = "";
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 8) {
            System.out.println("早上好");
            nihao = "早上好";
        } else if (hour >= 8 && hour < 11) {
            System.out.print("上午好");
            nihao = "上午好";
        } else if (hour >= 11 && hour < 13) {
            System.out.print("中午好");
            nihao = "中午好";
        } else if (hour >= 13 && hour < 18) {
            System.out.print("下午好");
            nihao = "下午好";
        } else {
            System.out.print("晚上好");
            nihao = "晚上好";
        }
        return nihao;
    }

    /**
     * 两个时间差计算
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }
}

package com.liushuai.treasurechest.remind;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description 提醒
 * @Author ls
 * @Date 2019/8/1 20:45
 */
public class Remind {
    public static Date getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +3);// 时间加3天
        date = calendar.getTime();
        return date;
    }


    public boolean compareTime(Date compareDate) {
        boolean flag = false;
        try {
            Date nowTime = new Date();
            Date compareTime = getDay(compareDate);
            if (nowTime.before(compareTime)) {
                flag = true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}

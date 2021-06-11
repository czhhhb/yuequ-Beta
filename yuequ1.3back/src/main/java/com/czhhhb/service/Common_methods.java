package com.czhhhb.service;

import com.czhhhb.entity.Clock_record;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class Common_methods {
    public String nowdate()
    {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(date);
        return dateStr;
    }
    public long date_subtract(String data1,String data2)
    {
        System.out.println(data1+data2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long d1=format.parse(data1).getTime();
            long d2=format.parse(data2).getTime();
            return (d2-d1)/60000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long count_date_subtract(String data1)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String data2 = format.format(date);
        try {
            long d1=format.parse(data1).getTime();
            long d2=format.parse(data2).getTime();
            return (d1-d2)/86400000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long date_subtract_miao(String data1,String data2)
    {
        System.out.println(data1+data2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long d1=format.parse(data1).getTime();
            long d2=format.parse(data2).getTime();
            return (d2-d1)/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


}

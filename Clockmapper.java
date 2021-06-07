package com.czhhhb.mapper;

import com.czhhhb.entity.Clock_record;
import com.czhhhb.service.Common_methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class Clockmapper {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    Common_methods common_methods;
    String yinghao="\"";
    public void Clock_record_add(Clock_record clock)
    {
        String sql = "insert into clock_record(USER_ID,CLOCK_BEGIN_TIME,CLOCK_STATE) values(?,?,?)";
        Object[] objects = new Object[3];
        objects[0] = clock.getUSER_ID();
        objects[1] = clock.getCLOCK_BEGIN_TIME();
        objects[2] = clock.getCLOCK_STATE();
        jdbcTemplate.update(sql, objects);
    }
    public List<Map<String, Object>> Clock_record_selectByidandstate(String USER_ID,int state)
    {
        String sql = "select * from clock_record where USER_ID="+yinghao+USER_ID+yinghao+" and CLOCK_STATE="+state;
        return jdbcTemplate.queryForList(sql);
    }
    public void Clock_record_updata(Clock_record clock)
    {
        int i = 0;
        String sql = "update clock_record set CLOCK_OVER_TIME=?,CLOCK_TIME=?,CLOCK_STATE=? where clock_id = ?";
        Object[] objects = new Object[4];
        objects[0] = clock.getCLOCK_OVER_TIME();
        objects[1] = clock.getCLOCK_TIME();
        objects[2] = clock.getCLOCK_STATE();
        objects[3] = clock.getClock_id();
        jdbcTemplate.update(sql, objects);
    }
    public Clock_record Clock_record_maps(Map<String, Object> map)
    {
        Clock_record clock=new Clock_record();
        clock.setUSER_ID(map.get("USER_ID").toString());
        clock.setClock_id(Integer.parseInt((map.get("clock_id").toString())));
        clock.setCLOCK_TIME(Integer.parseInt((map.get("CLOCK_TIME").toString())));
        clock.setCLOCK_BEGIN_TIME(map.get("CLOCK_BEGIN_TIME").toString());
        clock.setCLOCK_OVER_TIME(map.get("CLOCK_OVER_TIME").toString());
        clock.setCLOCK_STATE(0);
        System.out.println("Clock_record.Clock_record_maps clock is "+clock.toString());
        return clock;
    }
    public List<Map<String, Object>> Clock_record_histroy(String USER_ID)
    {
        String sql = "select * from clock_record where USER_ID="+yinghao+USER_ID+yinghao+"and CLOCK_STATE=0"+" order by CLOCK_BEGIN_TIME desc";
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }
}

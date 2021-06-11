package com.czhhhb.mapper;

import com.czhhhb.entity.Clock_record;
import com.czhhhb.entity.Tomato_clock;
import com.czhhhb.service.Common_methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Tomatomapper {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    Common_methods common_methods;
    String yinghao="\"";
    public void tomato_clock_add(Tomato_clock tomato)
    {
        String sql = "insert into tomato_clock(USER_ID,start_time,time,tomato_state) values(?,?,?,?)";
        Object[] objects = new Object[4];
        objects[0] = tomato.getUSER_ID();
        objects[1] = tomato.getStart_time();
        objects[2] = tomato.getTime();
        objects[3] = 1;
        jdbcTemplate.update(sql, objects);
    }
    public List<Map<String, Object>> Tomato_clock_selectByidandstate(String USER_ID, int state)
    {
        String sql = "select * from tomato_clock where USER_ID="+yinghao+USER_ID+yinghao+" and tomato_state="+state;
        return jdbcTemplate.queryForList(sql);
    }
    public void Tomato_clock_updatastate(String USER_ID)
    {
        int i = 0;
        String sql = "update tomato_clock set tomato_state=? where USER_ID = ? and tomato_state = ?";
        Object[] objects = new Object[3];
        objects[0] = 0;
        objects[1] = USER_ID;
        objects[2] = 1;
        jdbcTemplate.update(sql, objects);
    }
}

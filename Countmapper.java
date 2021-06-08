package com.czhhhb.mapper;

import com.czhhhb.service.Common_methods;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.czhhhb.entity.count_down;

import java.util.List;
import java.util.Map;

@Component
public class Countmapper {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    Common_methods common_methods;
    String yinghao="\"";
    public void Count_add(count_down count)
    {
        int i = 0;
        String sql = "insert into count_down(USER_ID,EVENT_NAME,END_TIME) values(?,?,?)";
        Object[] objects = new Object[3];
        objects[0] = count.getUSER_ID();
        objects[1] = count.getEVENT_NAME();
        objects[2] = count.getEND_TIME();
        System.out.println("Count_add . count is "+count.toString());
        jdbcTemplate.update(sql, objects);
    }
    public List<Map<String, Object>> Count_selectbyid(String USER_ID)
    {
        String sql="select * from count_down where USER_ID="+yinghao+USER_ID+yinghao+"order by END_TIME";
        List<Map<String, Object>> maps=jdbcTemplate.queryForList(sql);
        int i=0;
        for(i=0;i<maps.size();i++)
        {
            maps.get(i).put("remain_day",common_methods.count_date_subtract(maps.get(i).get("END_TIME").toString()));
        }
        System.out.println(maps);
        return maps;
    }
    public void Count_delete(String USER_ID,String EVENT_ID)
    {
        String sql="delete from count_down where USER_ID="+yinghao+USER_ID+yinghao+"and EVENT_ID="+EVENT_ID;
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }
    public int Count_isexit(String USER_ID,String EVENT_ID)
    {
        String sql="select * from count_down where USER_ID="+yinghao+USER_ID+yinghao+"and EVENT_ID="+EVENT_ID;
        return jdbcTemplate.queryForList(sql).size();
    }
}

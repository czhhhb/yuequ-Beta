package com.czhhhb.mapper;


import com.czhhhb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Usermapper {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String yinghao="\"";
    public List<Map<String, Object>> test() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps.toString());
        return maps;
    }

    public int Usermapper_add(User user) {
        int i = 0;
        String sql = "insert into user(USER_ID,USER_NICKNAME,USER_SEX,USER_PHONENUM,USER_CLOCK_NUM,USER_headimage) values(?,?,?,?,?,?)";
        Object[] objects = new Object[6];
        objects[0] = user.getUSER_ID();
        objects[1] = user.getUSER_NICKNAME();
        objects[2] = user.getUSER_SEX();
        objects[3] = user.getUSER_PHONENUM();
        objects[4] = user.getUSER_CLOCK_NUM();
        objects[5] = user.getUSER_headimage();
        i=jdbcTemplate.update(sql, objects);
        return i;
    }
    public int Usermapper_selectByid(String USER_ID)
    {
        String yinghao="\"";
        String sql = "select * from user where USER_ID="+yinghao+USER_ID+yinghao;
        return jdbcTemplate.queryForList(sql).size();
    }
    public int Usermapper_delect(String USER_ID)
    {
        String sql = "delect  from user where USER_ID="+USER_ID;
        return jdbcTemplate.queryForList(sql).size();
    }
    public int Usermapper_updata(User user)
    {
        return 1;
    }
    public List<Map<String, Object>> Usermapper_select_rank()
    {
        String sql="SELECT user.USER_ID,USER_NICKNAME,USER_headimage,SUM(CLOCK_TIME) as sumtime \n" +
                "FROM clock_record INNER join user on clock_record.USER_ID = user.USER_ID\n" +
                "group by user.USER_ID\n" +
                "order by SUM(CLOCK_TIME) DESC\n" +
                "limit 7";
        return jdbcTemplate.queryForList(sql);
    }
    public Map<String, Object> Usermapper_select_myrank(String USER_ID)
    {
        String sql="SELECT user.USER_ID,USER_NICKNAME,USER_headimage,SUM(CLOCK_TIME) as sumtime\n" +
                "FROM clock_record INNER join user on clock_record.USER_ID = user.USER_ID\n" +
                "group by user.USER_ID\n" +
                "order by SUM(CLOCK_TIME) DESC\n";
        List<Map<String, Object>> maps=jdbcTemplate.queryForList(sql);
        int i=1;
        Map<String, Object> hashmap=new HashMap<String, Object>();
        for (Map<String, Object> map : maps) {
            if(map.get("USER_ID").equals(USER_ID))
            {
                hashmap=map;
                hashmap.put("rankindex",i);
                break;
            }
            i++;
        }
        return hashmap;
    }
    public List<Map<String, Object>> Usermapper_histroy(String USER_ID)
    {
        String sql="SELECT a.click_date as date,ifnull(b.time,0) as time\n" +
                "from (SELECT date_format(curdate(),'%m-%d') as click_date\n" +
                "union all\n" +
                "SELECT date_format(date_sub(curdate(), interval 1 day),'%m-%d') as click_date\n" +
                "union all\n" +
                "SELECT date_format(date_sub(curdate(), interval 2 day),'%m-%d') as click_date\n" +
                "union all\n" +
                "SELECT date_format(date_sub(curdate(), interval 3 day),'%m-%d') as click_date\n" +
                "union all\n" +
                "SELECT date_format(date_sub(curdate(), interval 4 day),'%m-%d') as click_date\n" +
                "union all\n" +
                "SELECT date_format(date_sub(curdate(), interval 5 day),'%m-%d') as click_date\n" +
                "union all\n" +
                "SELECT date_format(date_sub(curdate(), interval 6 day),'%m-%d') as click_date)\n" +
                "a LEFT join (SELECT date_format(CLOCK_BEGIN_TIME,'%m-%d') as date,sum(CLOCK_TIME) as time  \n" +
                "from clock_record                \n" +
                "where USER_ID="+yinghao+USER_ID+yinghao+"and to_days(now())-to_days(date_format(`CLOCK_BEGIN_TIME`,'%Y-%m-%d'))<7\n" +
                "group by date_format(CLOCK_BEGIN_TIME,'%m-%d')\n" +
                "order by date_format(CLOCK_BEGIN_TIME,'%m-%d')\n" +
                "limit 7) b on a.click_date=b.date";
        return jdbcTemplate.queryForList(sql);
    }
    public int Usermapper_library_advice(String USER_ID,String text)
    {
        String sql="insert into library(USER_ID,library_advice) values(?,?)";
        Object[] objects = new Object[2];
        objects[0] = USER_ID;
        objects[1] = text;
        int i=jdbcTemplate.update(sql, objects);
        return i;
    }
}

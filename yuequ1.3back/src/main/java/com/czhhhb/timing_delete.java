package com.czhhhb;

import com.czhhhb.service.Common_methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class timing_delete {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    Common_methods common_methods;

    @Scheduled(cron = "0 0 1 * * ?")
    private void configureTasks() {
        String sql = "delete from clock_record where CLOCK_STATE=1";
        jdbcTemplate.update(sql);
        System.out.println("打卡时间到，忘记打卡的同学本次打卡作废！！！");
    }
}

package com.czhhhb.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class hello {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @PostMapping("/hello")
    public JSONObject helloworld(@RequestBody  JSONObject js) {
        System.out.println(js.toString());
        return js;
    }
    @RequestMapping("/hellotest")
    public String helloworld(  ) {
        return "helloworld123";
    }
        /*
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String s=null;
            while((s=br.readLine())!=null) {
                sb.append(s);
            }
            System.out.println(sb.toString());
            JSONObject js=JSONObject.fromObject(sb.toString());
            System.out.println(js.toString());
            return "server ";
        } catch (IOException e) {
            e.printStackTrace();
    }

        return "helloworld";
    }
    */
        @RequestMapping("/jdbctest")
        public List<Map<String, Object>> jdbstest()
        {
            String sql = "select * from user";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            return maps;
        }
}

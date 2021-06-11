package com.czhhhb.controller;

import com.czhhhb.entity.count_down;
import com.czhhhb.mapper.Clockmapper;
import com.czhhhb.mapper.Countmapper;
import com.czhhhb.service.Common_methods;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/count")
public class countcontroller {
    @Autowired
    Clockmapper clockmapper;
    @Autowired
    Countmapper countmapper;
    @RequestMapping("/add")
    public JSONObject count_add(@RequestBody JSONObject js)
    {
        count_down count=new count_down();
        count.setUSER_ID(js.get("USER_ID").toString());
        count.setEVENT_NAME(js.get("EVENT_NAME").toString());
        count.setEND_TIME(js.get("END_TIME").toString());
        countmapper.Count_add(count);
        JSONObject returnjs=new JSONObject();
        returnjs.accumulate("countadd_success",1);
        return returnjs;
    }
    @RequestMapping("delete")
    public JSONObject count_delete(@RequestBody JSONObject js)
    {
        JSONObject returnjs=new JSONObject();
        countmapper.Count_delete(js.get("USER_ID").toString(),js.get("EVENT_ID").toString());
        if(countmapper.Count_isexit(js.get("USER_ID").toString(),js.get("EVENT_ID").toString())==0)
            returnjs.accumulate("countdelete_success",1);
        else
            returnjs.accumulate("countdelete_success",0);
        return returnjs;
    }
    @RequestMapping("/history")
    public List<Map<String, Object>> count_histroy(@RequestBody JSONObject js)
    {
        return countmapper.Count_selectbyid(js.getString("USER_ID"));
    }
}

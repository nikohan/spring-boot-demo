package com.test.demo.action;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.event.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhaohan on 2016/8/3.
 */
@RestController
@RequestMapping("/event")
public class EventController {
    @RequestMapping("/demo")
    public Map<String, Object> getEvent(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        DemoService demoService = new DemoService();
        demoService.triggerEvent();

        jsonObject.put("result", "the end");
        return jsonObject;
    }
}

package com.test.demo.action;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.Application;
import com.test.demo.event.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

	@Autowired
	private DemoService demoService;

    @RequestMapping("/demo")
    public Map<String, Object> getEvent(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        demoService.triggerEvent();
        demoService.triggerAsyncEvent();

        jsonObject.put("result", "the end");
        return jsonObject;
    }
}

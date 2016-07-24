package com.test.demo;

/**
 * Created by nikohan on 2016/7/24.
 */
@RestController
public class ThisWillActuallyRun {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }
}

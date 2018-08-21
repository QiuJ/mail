package com.itstyle.mail.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: Administrator
 * @Date: 2018/8/20 16:15
 * @Description:
 */
@Controller
public class IndexController {

    /**
     * 页面跳转
     * @param url
     * @return
     */
    @GetMapping("{url}.shtml")
    public String page(@PathVariable("url") String url){
        return url;
    }

    /**
     * 页面跳转(二级目录)
     * @param module
     * @param url
     * @return
     */
    @GetMapping("{module}/{url}.shtml")
    public String page(@PathVariable("module") String module,@PathVariable("url") String url){
        return module + "/" + url;
    }
}

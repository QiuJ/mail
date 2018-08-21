package com.itstyle.mail.web;

import com.itstyle.mail.common.model.Email;
import com.itstyle.mail.common.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Administrator
 * @Date: 2018/8/21 11:08
 * @Description:
 */
@RestController
@RequestMapping("/mail")
public class mailController {

    @PostMapping("send")
    public Result send(Email mail){

        return null;
    }

    @PostMapping("list")
    public Result list(Email mail){
        return null;
    }
}

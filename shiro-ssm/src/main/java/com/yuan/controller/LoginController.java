package com.yuan.controller;

import com.yuan.dto.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author yuan_jx
 * @date 2019/10/16 22:52
 */
@Controller

public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/login")
    public String login(String userName, String  password, HttpSession session){

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userName,password));
            ActiveUser user = (ActiveUser) subject.getPrincipal();
            session.setAttribute("user",user);
            log.info("登陆成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            log.warn("登陆失败");
            return "redirect:index";
        }

        return  "success";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/loginout")
    public void loginout() {

    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/main")
    public String main(){
        return "success";
    }
}

package org.afeng.login.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author afeng
 * @date 2018/11/3 11:35
 **/
@Controller
@RequestMapping("/login")
public class LoginController
{
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/showloginpage")
    public String login(HttpServletRequest request) throws Exception
    {

        return "login/login";
    }

    @RequestMapping("/showloginpage2")
    public String login2(HttpServletRequest request) throws Exception
    {

        return "login/login2";
    }

    @RequestMapping("/showloginpage3")
    public String login3(HttpServletRequest request) throws Exception
    {

        return "login/login3";
    }


    @RequestMapping("/loginnow")
    public String loginnow(HttpServletRequest request, String loginName, String password) throws Exception
    {
        try
        {
            if (loginName == null || "".equals(loginName) || password == null || "".equals(password))
            {
                return "/error/404";
            }
        } catch (Exception e)
        {
            logger.error("登录失败:loginName:" + loginName + ";", e);
            e.printStackTrace();
        }
        return "note/inotecenter";
    }

}

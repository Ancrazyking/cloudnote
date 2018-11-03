package org.afeng.login.bean;

import java.io.Serializable;

/**
 * @author afeng
 * @date 2018/11/3 9:41
 **/
public class User implements Serializable
{
    //需自定义序列化ID
    private static final long serialVersionUID=1169958693886162392L;


    private int userId;
    private String loginName;
    private String password;
    private int type;
    private long registerTime;
    private String personalMail;


    public User()
    {
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public long getRegisterTime()
    {
        return registerTime;
    }

    public void setRegisterTime(long registerTime)
    {
        this.registerTime = registerTime;
    }

    public String getPersonalMail()
    {
        return personalMail;
    }

    public void setPersonalMail(String personalMail)
    {
        this.personalMail = personalMail;
    }
}

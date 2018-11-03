package org.afeng.login.service.impl;

import org.afeng.login.dao.LoginDao;
import org.afeng.login.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author afeng
 * @date 2018/11/3 11:32
 **/
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService
{

    @Resource(name = "loginDaoImpl")
    private LoginDao loginDao;

    @Override
    public boolean login(String userName, String password) throws Exception
    {
        return loginDao.getLoginInfo(userName, password);
    }
}

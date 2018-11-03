package org.afeng.login.dao.impl;

import org.afeng.login.dao.LoginDao;
import org.afeng.util.RedisTools;
import org.afeng.util.constants.Constants;
import org.springframework.stereotype.Repository;


/**
 * @author afeng
 * @date 2018/11/3 9:48
 **/
@Repository("loginDaoImpl")
public class LoginDaoImpl implements LoginDao
{
    @Override
    public boolean getLoginInfo(String userName, String password) throws Exception
    {
        boolean flag = false;
        String userInfo = RedisTools.get(userName);
        if (userInfo != null)
        {
            String[] spilt = userInfo.split("\\" + Constants.STRING_SEPARATOR);
            if (password.equals(spilt[0]))
            {
                flag = true;
            }
        }
        return false;
    }
}

package org.afeng.login.dao;

/**
 * @author afeng
 * @date 2018/11/3 9:48
 **/
public interface LoginDao
{
    boolean getLoginInfo(String userName, String password) throws Exception;
}

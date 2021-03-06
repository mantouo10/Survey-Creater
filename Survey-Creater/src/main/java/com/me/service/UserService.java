package com.me.service;

import com.me.po.User;
import com.baomidou.mybatisplus.service.IService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xff
 * @since 2019-02-22
 */
public interface UserService extends IService<User> {
    /**
     * fetch data by rule id
     *
     * @param email email
     * @param password password
     * @return Result<User>
     * @throws UnsupportedEncodingException zz
     * @throws NoSuchAlgorithmException xx
     */
    User loginCheck(String email,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * fetch data by rule id
     *
     * @param email email
     * @return Result<User>
     */
    Integer selectIdByEmail(String email);
}

package com.example.demo.service;

import com.example.demo.model.dao.User;

import java.util.List;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/10/21
 */
public interface IUserService {

    void batchInsertUser(Integer beginId, Integer endId);

    User queryUserById(Integer userId);

    List<User> queryUsersByGroup(Integer beginId, Integer endId);

}

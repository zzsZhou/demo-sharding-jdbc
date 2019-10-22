package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dao.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/10/21
 */
@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserById(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        User user1 = userMapper.selectByPrimaryKey(user);
        return user1;
    }

    @Override
    public void batchInsertUser(Integer beginId, Integer endId) {

        for (int i = beginId; i <= endId; i++) {
            User userInfo = new User();
            userInfo.setUserId(i);
            userInfo.setAccount("Account" + i);
            userInfo.setPassword("pass" + i);
            userInfo.setUserName("name" + i);
            userInfo.setGroupId(i);
/*            if (i == 3) {
                HintManagerHolder.clear();
                HintManager hintManager = HintManager.getInstance();
                hintManager.addDatabaseShardingValue("user_info", "user_id", 3L);
                hintManager.addTableShardingValue("user_info", "user_id", 3L);
                System.out.println(userId);
            }*/
            userMapper.insert(userInfo);
        }
    }

    @Override
    public List<User> queryUsersByGroup(Integer beginId, Integer endId) {
        List<User> users = userMapper.selectByGroup(beginId, endId);
        return users;
    }
}

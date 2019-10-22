package com.example.demo.controller;

import com.example.demo.model.dao.User;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/10/21
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/users")
public class UserController {


    @Autowired
    private IUserService userService;


    @GetMapping("/{user_id}")
    public User queryUserById(@PathVariable("user_id") Integer userId) {

        log.info("queryUserById id:{}", userId);

        User rs = userService.queryUserById(userId);
        return rs;
    }


    @PostMapping
    public void batchInsertUser(@RequestParam("begin_id") Integer beginId,
                                @RequestParam("end_id") Integer endId) {

        log.info("batchInsertUser begin_id:{},end_id:{}", beginId, endId);

        userService.batchInsertUser(beginId, endId);
    }


    @GetMapping
    public List<User> queryUsersByGroup(@RequestParam("group_begin_id") Integer beginId,
                                        @RequestParam("group_end_id") Integer endId) {

        log.info("queryUsersByGroup group_begin_id:{},group_end_id:{}", beginId, endId);

        return userService.queryUsersByGroup(beginId, endId);
    }


}

package com.example.demo.controller;

import com.example.demo.model.dao.Group;
import com.example.demo.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/11/11
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/groups")
public class GroupController {

    @Autowired
    private IGroupService groupService;


    @PostMapping
    public void batchInsertGroup(@RequestParam("begin_id") Integer beginId,
                                 @RequestParam("end_id") Integer endId) {

        log.info("batchInsertGroup begin_id:{},end_id:{}", beginId, endId);

        groupService.batchInsert(beginId, endId);
    }

    @GetMapping
    public List<Group> queryGroupsById(@RequestParam("begin_id") Integer beginId,
                                         @RequestParam("end_id") Integer endId) {

        log.info("queryGroupsById begin_id:{},end_id:{}", beginId, endId);

        return groupService.queryGroupsById(beginId, endId);
    }

}

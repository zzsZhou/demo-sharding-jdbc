package com.example.demo.service;

import com.example.demo.model.dao.Group;

import java.util.List;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/11/11
 */
public interface IGroupService {

    void batchInsert(Integer beginId, Integer endId);

    List<Group> queryGroupsById(Integer beginId, Integer endId);

}

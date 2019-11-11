package com.example.demo.service.impl;

import com.example.demo.mapper.GroupMapper;
import com.example.demo.model.dao.Group;
import com.example.demo.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/11/11
 */
@Slf4j
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public void batchInsert(Integer beginId, Integer endId) {

        for (int i = beginId; i <= endId; i++) {
            Group groupInfo = new Group();
            groupInfo.setId(i);
            groupInfo.setName("name" + i);
            groupInfo.setUserNumber(i);
            groupMapper.insert(groupInfo);
        }
    }


    @Override
    public List<Group> queryGroupsById(Integer beginId, Integer endId) {
        List<Group> list = groupMapper.selectGroupsById(beginId, endId);
        return list;
    }
}

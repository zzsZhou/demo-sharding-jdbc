package com.example.demo.mapper;

import com.example.demo.model.dao.Group;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroupMapper extends Mapper<Group> {

    List<Group> selectGroupsById(@Param("beginId") Integer beginId, @Param("endId") Integer endId);
}
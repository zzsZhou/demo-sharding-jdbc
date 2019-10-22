package com.example.demo.mapper;

import com.example.demo.model.dao.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    List<User> selectByGroup(@Param("beginId") Integer beginId, @Param("endId") Integer endId);

}
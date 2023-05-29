package com.bobjo.bobjo.login;

import com.bobjo.bobjo.DTO.userDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface loginMapper {

    userDTO selectUser(@Param("id") String id, @Param("pw") String pw) throws Exception;

    void insertUser(userDTO userDTO) throws Exception;
}

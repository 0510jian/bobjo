package com.bobjo.bobjo.login;

import com.bobjo.bobjo.DTO.userDTO;

public interface loginService {
    userDTO selectUser(String id, String pw) throws Exception;

    void insertUser(userDTO userDTO) throws Exception;
}

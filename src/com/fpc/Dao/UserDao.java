package com.fpc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fpc.Entity.User;

@Repository
public interface UserDao {
 public User selectByName(String username);
}

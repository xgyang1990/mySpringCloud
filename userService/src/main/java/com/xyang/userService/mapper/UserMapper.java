package com.xyang.userService.mapper;

import com.xyang.userService.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name} AND PASSWORD = #{password}")
    User login(@Param("name") String name,@Param("password") String password);

    @Insert("INSERT INTO USER(NAME,PASSWORD,EMAIL,PHONE) VALUES(#{name},#{password},#{email},#{phone})")
    void add(@Param("name") String name,@Param("password") String password,@Param("email") String email,@Param("phone") String phone);

    }


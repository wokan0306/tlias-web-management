package com.itstan.mapper;

import com.itstan.controller.DeptController;
import com.itstan.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.logging.Logger;

@Mapper
public interface DeptMapper {

    @Select("SELECT * from dept")
    List<Dept> list();

    @Delete("DELETE FROM dept WHERE id = #{id}")
    void delete(int id);

    @Insert("INSERT INTO dept (name, create_time, update_time) VALUES (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("SELECT * from dept where id = #{id}")
    Dept getDeptById(int id);

    @Update("UPDATE dept SET name = #{name} WHERE id = #{id}")
    void update(Dept dept);

}

package com.itstan.mapper;

import com.itstan.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);


    @Insert("INSERT INTO emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    //    // Tradition query by page
//    @Select("SELECT * FROM emp limit #{start}, #{pageSize}")
//    List<Emp> page(Integer start, Integer pageSize);

//    @Select("select * from emp")
//    @Select("SELECT  COUNT(*) FROM emp")
//    Long count();

    @Select("select * from emp where username=#{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}

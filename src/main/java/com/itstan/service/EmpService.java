package com.itstan.service;

import com.itstan.pojo.Emp;
import com.itstan.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean page(int page, int pageSize, String name, Short gender, LocalDate begin, LocalDate end);
    void delete(List<Integer> ids);
    void save(Emp emp);
    Emp getById(Integer id);
    void update(Emp emp);
    Emp login(Emp emp);
}

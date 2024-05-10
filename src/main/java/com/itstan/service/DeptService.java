package com.itstan.service;

import com.itstan.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();
    void delete(int id);
    void insert(Dept dept);
    Dept getDept(int id);
    void update(Dept dept);
}

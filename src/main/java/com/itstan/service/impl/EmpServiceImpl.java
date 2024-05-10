package com.itstan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itstan.anno.MyLog;
import com.itstan.mapper.EmpMapper;
import com.itstan.pojo.Emp;
import com.itstan.pojo.PageBean;
import com.itstan.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean page(int page, int pageSize) {
//        Long count = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> list = empMapper.page(start, pageSize);
//        return new PageBean(count, list);
//    }

    @MyLog
    @Override
    public PageBean page(int page, int pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);

        List<Emp> empList = empMapper.list(name, gender, begin, end);

        Page<Emp> p = (Page<Emp>) empList;

        return new PageBean(p.getTotal(), p.getResult());

    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}

package com.itstan.service.impl;

import com.itstan.mapper.DeptLogMapper;
import com.itstan.mapper.DeptMapper;
import com.itstan.mapper.EmpMapper;
import com.itstan.pojo.Dept;
import com.itstan.pojo.DeptLog;
import com.itstan.service.DeptLogService;
import com.itstan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional
    @Override
    public void delete(int id) {
        try {
            deptMapper.delete(id);

            empMapper.deleteByDeptId(id);
        }
        finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("Executed the operation of dissolving department. Department deleted: " + id) ;
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDept(int id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}

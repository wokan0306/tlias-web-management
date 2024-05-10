package com.itstan.controller;

import com.itstan.anno.Log;
import com.itstan.pojo.Dept;
import com.itstan.pojo.Result;
import com.itstan.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Log
    @GetMapping("/{id}")
    public Result getDept(@PathVariable int id) {
        log.info("Query department by id: {}", id);
        return Result.success(deptService.getDept(id));
    }

    @Log
    @GetMapping
    public Result list() {
        log.info("Query all department data");
        return Result.success(deptService.list());
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("Delete department data with id = {}", id);
        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept) {
        log.info("Add department data: {}", dept.getName());
        deptService.insert(dept);
        return Result.success();
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("Update department data: {}", dept.getName());
        deptService.update(dept);
        return Result.success();
    }

}

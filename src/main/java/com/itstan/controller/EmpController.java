package com.itstan.controller;

import com.itstan.anno.Log;
import com.itstan.pojo.Emp;
import com.itstan.pojo.PageBean;
import com.itstan.pojo.Result;
import com.itstan.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @Log
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("Query by page, parameter: {}, {}, {}, {}, {}, {}",page, pageSize, name, gender, begin, end );
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("Delete employee data in batch, ids: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("Insert employee data with emp + {} ", emp);
        empService.save(emp);
        return Result.success();
    }

    @Log
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("Get employee data by id, id: {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("Update employee data with emp + {} ", emp);
        empService.update(emp);
        return Result.success();
    }
}

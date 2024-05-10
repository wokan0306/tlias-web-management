package com.itstan.controller;

import com.itstan.pojo.Emp;
import com.itstan.pojo.Result;
import com.itstan.service.EmpService;
import com.itstan.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result Login(@RequestBody Emp emp) {
        log.info("Employee Login: {}", emp);
        Emp e = empService.login(emp);
        // Login Successful then generate JWT and boardcast
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("password", e.getPassword());
            claims.put("username", e.getUsername());
            JwtUtils.generateJwt(claims);

            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("Either username or password is incorrect");
    }
}

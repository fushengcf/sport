package com.sport.config;

import com.sport.entity.Admin;
import com.sport.entity.Result.UserDTO;
import com.sport.entity.Teacher;
import com.sport.service.TeacherService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {
    private static TeacherService teacherService;
    static final String SECRET = "ThisIsASecret";

    public static String generateTeachertToken(Teacher teacher) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("id", teacher.getId());
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))// 1000 hour
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+jwt; //jwt前面一般都会加Bearer
    }
    public static String generateAdmintToken(UserDTO userDTO) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("id", userDTO.getId());
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))// 1000 hour
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+jwt; //jwt前面一般都会加Bearer
    }
}

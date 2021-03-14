package com.achievement.config;

import com.achievement.entity.Teacher;
import com.achievement.service.TeacherService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {
    private static TeacherService teacherService;
    static final String SECRET = "ThisIsASecret";

    public static String generateToken(Teacher teacher) {
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
}

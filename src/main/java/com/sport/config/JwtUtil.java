package com.sport.config;

import com.sport.entity.Admin;
import com.sport.entity.Result.UserDTO;
import com.sport.entity.Venue;
import com.sport.service.VenueService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {
    private static VenueService venueService;
    static final String SECRET = "ThisIsASecret";

    public static String generateVenueToken(Venue venue) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("id", venue.getId());
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))// 1000 hour
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+jwt; //jwt前面一般都会加Bearer
    }
    public static String generateAdminToken(Admin admin) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("id", admin.getId());
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))// 1000 hour
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+jwt; //jwt前面一般都会加Bearer
    }
}

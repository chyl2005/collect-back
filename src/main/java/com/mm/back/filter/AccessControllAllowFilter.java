package com.mm.back.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.mm.back.common.Config;

/**
 * Author:chenyanlong
 * Date:17/6/16
 * Time:14:19
 * Desc:描述该类的作用
 */
@Component
public class AccessControllAllowFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessControllAllowFilter.class);

    @Autowired
    private Config config;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String origin = "*";
        if (StringUtils.hasText(request.getHeader("Origin"))) {
            origin = request.getHeader("Origin");
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,PATCH,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        request.setAttribute("base",config.getBaseUrl());
        filterChain.doFilter(request, response);
    }
}

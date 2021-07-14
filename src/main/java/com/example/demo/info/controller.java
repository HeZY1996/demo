package com.example.demo.info;

import com.example.demo.cTime.TimeFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Configuration
public class controller {
//@ResponseBody
    @RequestMapping("/info")
    public String info(Model model){
        long start =System.nanoTime();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        Map<String, String> map = new HashMap<String, String>();

        //依次获取header信息
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        //依次获取参数信息
        Enumeration ParameterNames = request.getParameterNames();
        while (ParameterNames.hasMoreElements()) {
            String key = (String) ParameterNames.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }

        //将当前请求的参数放入map中
        map.put("HTTP Method",request.getMethod());
        //计算代码执行的时间
        long end=System.nanoTime();
         String time=(end-start)/1000000.0+"ms";
         map.put("Program execution time",time);
         model.addAttribute("map", map);

         return "info";
    }

}

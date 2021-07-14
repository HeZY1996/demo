package com.example.demo.cTime;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

@Component //表明作为spring的一个bean
public class TimeFilter implements Filter {
    private double time;

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.nanoTime();
        //过滤器主要逻辑，整个处理流程
        chain.doFilter(request, response);
        time=(System.nanoTime() - start)/1000000.0;

        PrintWriter pw = response.getWriter();
        //由于执行完成，时间无法存入map，故直接打印到界面
        pw.print("consuming time:"+time+"ms");
        pw.flush();
        System.out.println("time filter 耗时:"+ time+"ms");
        System.out.println("time filter finish");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("time filter init");
    }


}
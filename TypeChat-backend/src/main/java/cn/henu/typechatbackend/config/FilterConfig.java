package cn.henu.typechatbackend.config;


import cn.henu.typechatbackend.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //定义此类为配置类
public class FilterConfig {
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new MyFilter());
        //添加过滤路径

        filterRegistrationBean.addUrlPatterns("/ddchat/user/*");
        filterRegistrationBean.addUrlPatterns("/ddchat/friend/*");
        filterRegistrationBean.addUrlPatterns("/ddchat/group/*");
        filterRegistrationBean.addUrlPatterns("/ddchat/notice/*");
        filterRegistrationBean.addUrlPatterns("/ddchat/message/*");
        filterRegistrationBean.addUrlPatterns("/ddchat/world/*");
        return filterRegistrationBean;
    }

}

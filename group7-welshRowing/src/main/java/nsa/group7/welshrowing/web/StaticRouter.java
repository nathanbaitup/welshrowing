package nsa.group7.welshrowing.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticRouter implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //routing to welcome page when first entering the site.
        registry.addViewController("/").setViewName("forward:/welcome.html");
    }
}

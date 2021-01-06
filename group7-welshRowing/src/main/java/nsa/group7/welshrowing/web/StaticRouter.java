package nsa.group7.welshrowing.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticRouter implements WebMvcConfigurer {

    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/favicon.ico";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //routing to welcome page when first entering the site.
        registry.addViewController("/").setViewName("forward:/welcome.html");
        registry.addViewController("/logout-page").setViewName("forward:/logout-page.html");
    }
}

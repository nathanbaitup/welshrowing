package nsa.group7.welshrowing.security;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import javax.servlet.annotation.WebServlet;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/").permitAll();

            http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
                    .and().csrf().ignoringAntMatchers("/h2-console/**")
                    .and().headers().frameOptions().sameOrigin();
            http
               //      ...
                    .headers()
                    .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self' https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js https://code.jquery.com https://cdnjs.cloudflare.com https://maxcdn.bootstrapcdn.com https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"));
            // ...
        }
        }
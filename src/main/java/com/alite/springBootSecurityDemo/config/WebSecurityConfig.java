package com.alite.springBootSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alite.springBootSecurityDemo.auth.JWTAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests() // 定義哪些url需要被保護
            .antMatchers("/").permitAll()  // 定義匹配到"/" 不需要驗證
            .antMatchers("/swagger-ui.html").permitAll() // 匹配到"/swagger-ui.html", 不需要身份驗證
			.antMatchers("/swagger-ui.html/**").permitAll()      // swagger-ui
			.antMatchers("/webjars/**").permitAll()              // swagger-ui
			.antMatchers("/swagger-resources/**").permitAll()    // swagger-ui
			.antMatchers("/v2/**").permitAll()                   // swagger-ui
			.antMatchers("/csrf").permitAll()                    // swagger-ui
			.antMatchers(HttpMethod.GET, "/user").authenticated()
            .antMatchers(HttpMethod.GET, "/user/*").authenticated()
            .antMatchers(HttpMethod.GET).permitAll()
            .antMatchers(HttpMethod.POST, "/user").permitAll()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/parse").permitAll()
//			.antMatchers("/user/list").hasRole("USER")// 定義匹配到"/user"底下的需要有USER的這個角色才能進去
//	        .antMatchers("/admin").hasRole("ADMIN") // 定義匹配到"/admin"底下的需要有ADMIN的這個角色才能進去
//            .anyRequest().authenticated() // 其他尚未匹配到的url都需要身份驗證
//            .and()
//            .formLogin()
//            .and()
//            .httpBasic();
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.csrf().disable();

    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
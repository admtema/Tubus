package com.admolodtsov.Tubus.config;

import com.admolodtsov.Tubus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth ->{
                    //Доступ для всех
                auth.requestMatchers("/about").permitAll();
                            //Доступ только для НЕзарегистрированных пользователей
                auth.requestMatchers("/login").not().fullyAuthenticated();
                            //Доступ только для админа
                auth.requestMatchers("/users/**", "/registration").hasRole("ADMIN");
                            //Доступ только для зарегистрированных пользователей и админа
                auth.requestMatchers("/*","/projects/my/add", "/projects/my/{id}").hasAnyRole("USER","ADMIN");
                    //Доступ только для зарегистрированных пользователей
//                .antMatchers().hasRole("USER")
                    //Все остальные страницы требуют аутентификации
                auth.anyRequest().authenticated();

                })
                //Настройка для входа в систему
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(formLogout -> formLogout
                        .permitAll()
                        .logoutSuccessUrl("/login"))
                .build();
    }

}

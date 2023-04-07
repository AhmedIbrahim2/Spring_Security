package com.example.demo.security;


import com.example.demo.auth.ApplicationUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class configurattion extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder ;

    private final ApplicationUserServices applicationUserServices;

    public configurattion(PasswordEncoder passwordEncoder, ApplicationUserServices applicationUserServices) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserServices = applicationUserServices;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*", "/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE ,"/managemnet/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST , "/managemnet/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT , "/managemnet/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers( "/managemnet/api/**").hasAnyRole(Admin.name() , AdminTraine.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/courses" , true)
                .and()
                .rememberMe();
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails ahmed = User.builder()
//                .username("ahmed")
//                .password(passwordEncoder.encode("password"))
//                //     .roles(STUDENT.name())
//                .authorities(STUDENT.getGrantedAuthorities())
//                .build();
//
//        UserDetails mohamed = User.builder()
//                .username("mohamed")
//                .password(passwordEncoder.encode("password123"))
//                //      .roles(Admin.name())
//                .authorities(Admin.getGrantedAuthorities())
//                .build();
//
//        UserDetails tom = User.builder()
//                .username("tom")
//                .password(passwordEncoder.encode("password123"))
//                //      .roles(AdminTraine.name())
//                .authorities(AdminTraine.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(ahmed , mohamed , tom);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserServices);
        return provider ;
    }


}

package net.sublime.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
                .antMatchers("/api/catalog/**")
                .permitAll()
//                .and()
//                .formLogin()

        ;
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        var user = User
//                .withUsername("admin")
//                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
}

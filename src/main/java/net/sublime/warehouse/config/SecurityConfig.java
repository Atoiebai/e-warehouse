package net.sublime.warehouse.config;

import lombok.AllArgsConstructor;

import net.sublime.warehouse.jwt.JwtTokenVerifier;
import net.sublime.warehouse.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/users")
                .permitAll()
                .anyRequest()
                .authenticated()


        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        var user = User
                .withUsername("admin")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }
}

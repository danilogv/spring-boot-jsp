package com.springbootjsp.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Seguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioConfiguracao configuracao;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST,"/usuario")
        .permitAll()
        .antMatchers(HttpMethod.GET,"/","/usuario")
        .permitAll().anyRequest().authenticated()
        .and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/empresas",true)
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(configuracao).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers("/css/**","/js/**","/jsp/**","/imagens/**");
    }

}

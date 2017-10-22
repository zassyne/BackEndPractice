package com.configurations.security;

import com.services.UserDetailsServiceImpl;
import com.listeners.CustomSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;

import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()

                .antMatchers("/", "/home", "/sessions/**", "/session/**", "/api/**").permitAll()
                .antMatchers("/about").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        //auth.userDetailsService(new UserService());
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        //authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @ConfigurationProperties("application.properties")
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                                .url("jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                                .username("root")
                                .password("root")
                                .build();
    }

    @Bean
    public HttpSessionListener sessionListener() {
        return new CustomSessionListener();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter(){
        return new ConcurrentSessionFilter(sessionRegistry(), new SimpleRedirectSessionInformationExpiredStrategy("/expired"));
    }

    @Bean
    public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy(){
        return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy(){
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}

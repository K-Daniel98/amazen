package hu.unideb.web.amazen.core.security;

import hu.unideb.web.amazen.core.user.exception.UserDoesNotExistException;
import hu.unideb.web.amazen.core.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final JwtTokenFilter jwtTokenFilter;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(UserRepository userRepository, JwtTokenFilter jwtTokenFilter, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenFilter = jwtTokenFilter;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().disable().anonymous();
        http.csrf().disable();

        http.httpBasic().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/items/**").permitAll()
            .antMatchers("/item/**").permitAll()
            .antMatchers("/storage").permitAll()
            .antMatchers("/storage/**").permitAll()
            .antMatchers("/error").permitAll()
            .antMatchers("/public").permitAll()
            .antMatchers("/register").permitAll()
            .antMatchers("/h2-console/**/**").permitAll()
            .anyRequest().authenticated()
            .and();

        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
        web.ignoring().antMatchers("/error");
        web.ignoring().antMatchers("/public");
        web.ignoring().antMatchers("/api/error");
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
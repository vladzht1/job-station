package edu.rut_miit.job_station.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import edu.rut_miit.job_station.entities.UserRoles;
import edu.rut_miit.job_station.repositories.UserRepository;
import edu.rut_miit.job_station.services.impl.AppUserDetailsService;

@Configuration
public class SecurityConfig {
    private UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, SecurityContextRepository securityContextRepository) throws Exception {
        httpSecurity
            .authorizeHttpRequests(
                authorizeHttpRequests ->
                    authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/", "/auth/signup", "/auth/login", "/auth/login_error").permitAll()
                        .requestMatchers("/vacancies/{id}/apply", "/companies/new").authenticated()
                        .requestMatchers("/vacancies/**", "/companies/**", "/rating").permitAll()
                        .requestMatchers("/admin/**").hasRole(UserRoles.ADMIN.name())
                        .anyRequest().authenticated())
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/auth/login")
                    .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                    .defaultSuccessUrl("/")
                    .failureForwardUrl("/auth/login_error"))
            .rememberMe(rememberMe ->
                rememberMe
                    .rememberMeParameter("remember")
                    .key("remember")
                    .rememberMeCookieName("remember")
                    .tokenValiditySeconds(10000))
            .logout(logout ->
                logout
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true))
            .securityContext(context -> context.securityContextRepository(securityContextRepository));

        return httpSecurity.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService(userRepository);
    }
}

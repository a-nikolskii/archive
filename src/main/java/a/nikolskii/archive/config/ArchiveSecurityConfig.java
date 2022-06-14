package a.nikolskii.archive.config;

import a.nikolskii.archive.service.ArchiveUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class ArchiveSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ArchiveUserDetailsService archiveUserDetailsService;

    public ArchiveSecurityConfig(ArchiveUserDetailsService archiveUserDetailsService) {
        this.archiveUserDetailsService = archiveUserDetailsService;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(archiveUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin**").hasAuthority("USER_REGISTER")
                .antMatchers("/document/edit/**").hasAnyAuthority("DOCUMENT_EDIT")
                .antMatchers("/document/create/**").hasAnyAuthority("DOCUMENT_CREATE", "DOCUMENT_EDIT")
                .antMatchers("/document/view/**").hasAnyAuthority("DOCUMENT_VIEW", "DOCUMENT_EDIT", "DOCUMENT_CREATE")
                .antMatchers("/css/**").permitAll()
                .antMatchers("/accessDenied").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (request, response, accessDeniedException) -> response.sendRedirect("/accessDenied");
    }
}

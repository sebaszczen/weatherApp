package sebaszczen.security;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import sebaszczen.services.userService.MyUserDetailsService;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public SpringSecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("http://localhost:4200/login").and().
//                formLogin().
        csrf().
                csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
                and().addFilterAfter(
                new StatelessCSRFFilter(), CsrfFilter.class);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    CORSFilter corsFilter() {
        CORSFilter filter = new CORSFilter();
        return filter;
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        List<String> allowedOrigins = Arrays.asList("http://localhost:4200");
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "DELETE", "GET", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//
//        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept",
//                "Authorization", "Access-Control-Allow-Credentials", "Access-Control-Allow-Headers", "Access-Control-Allow-Methods",
//                "Access-Control-Allow-Origin", "Access-Control-Expose-Headers", "Access-Control-Max-Age",
//                "Access-Control-Request-Headers", "Access-Control-Request-Method", "Age", "Allow", "Alternates",
//                "Content-Range", "Content-Disposition", "Content-Description"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
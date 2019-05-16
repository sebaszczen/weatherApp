package sebaszczen.security;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder());
//        auth.authenticationProvider(authenticationProvider());
        String password = passwordEncoder().encode("a");
        System.out.println(password);
        auth.inMemoryAuthentication().withUser("a").password(password).roles("*");
//                .and().withUser("").password("").roles("");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().
                csrf().disable()
//                addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("http://localhost:4200/login").permitAll()
                .successHandler(loginSuccessHandler())
//                failureHandler(authenticationFailureHandler());
                .and().httpBasic();
//                formLogin().
//        csrf().
//                csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
//                and().addFilterAfter(
//                new StatelessCSRFFilter(), CsrfFilter.class);
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        //poniewaz AuthenticationSuccessHandler ma tylko jedna metode mozna uzyc lambda
        //parametry tej metody to:request,response,authentication
        System.out.println("asda");
        return (request, response, authentication) -> response.setStatus(200);
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, authentication) -> {
            if (authentication.getMessage().equals("User is disabled")) {
                response.sendRedirect("/login?disabled");
            } else {
                response.sendRedirect("/login?error");
            }
        };
//                request.getUserPrincipal().getName();  response.sendRedirect( "/logged");
    }


//    @Bean
//    CORSFilter corsFilter() {
//        CORSFilter filter = new CORSFilter();
//        return filter;
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("POST","PUT","DELETE","GET","OPTIONS"));
        configuration.setAllowCredentials(true);

        configuration.setAllowedHeaders(ImmutableList.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
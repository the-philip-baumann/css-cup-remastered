package ch.css.lernende.csscupremasteredbackend.config;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.interceptor.RequestInterceptor;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import ch.css.lernende.csscupremasteredbackend.repository.repo.player.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PlayerRepository playerRepository;
    private final RequestInterceptor requestInterceptor;

    public WebSecurityConfig(PlayerRepository playerRepository, RequestInterceptor requestInterceptor) {
        this.playerRepository = playerRepository;
        this.requestInterceptor = requestInterceptor;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http = http.cors().and().csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                                    );

                        }
                ).and();

        http.authorizeRequests()
                .regexMatchers(HttpMethod.GET, "/team/all").hasAnyRole("ADMIN", "CAPTAIN", "PARTICIPANT")
                .antMatchers(HttpMethod.GET, "/team/**").hasRole("ADMIN")
                .regexMatchers(HttpMethod.POST, "/team/add").hasAnyRole("ADMIN", "CAPTAIN")
                .antMatchers(HttpMethod.DELETE, "/team/delete/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/team/join/**").hasAnyRole("PARTICIPANT")
                .regexMatchers(HttpMethod.GET, "/player/all").hasAnyRole("ADMIN")
                .regexMatchers(HttpMethod.GET, "/player/solo").hasAnyRole("ADMIN", "CAPTAIN")
                .antMatchers(HttpMethod.DELETE, "/player/**").hasRole("ADMIN")
                .regexMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .regexMatchers(HttpMethod.POST, "/auth/register").permitAll()
        .anyRequest().permitAll();

        http.addFilterBefore(
                requestInterceptor,
                UsernamePasswordAuthenticationFilter.class
                );


    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(email ->
                playerRepository.findByEmail(email).orElseThrow(
                        () -> new UsernameNotFoundException("Email not found")
                )
        );
    }
    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Encoder fetched");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}

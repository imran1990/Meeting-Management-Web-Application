
package bd.edu.seu.managemeeting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").
                permitAll().anyRequest()
                .authenticated()
                .and().logout()
                .permitAll()
                .and().httpBasic();

       /*http.csrf().disable().
                authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .hasRole("ADMIN").anyRequest().authenticated()
                .antMatchers(HttpMethod.OPTIONS, "/user")
                .hasRole("USER").and()
                .httpBasic();*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set your configuration on the auth object
        auth.inMemoryAuthentication()
                .withUser("shakur")
                .password("{noop}1234")
                .roles("ADMIN")
                .and()
                .withUser("imran")
                .password("{noop}1234")
                .roles("USER");
    }



}

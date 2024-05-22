package org.seng2050.lab08;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    // @Bean
    // public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {

    //     UserDetails teacherUserDetails = User.withUsername("teacher")
    //         .password(passwordEncoder.encode("pass1"))
    //         .roles("staff")
    //         .build();

    //     UserDetails studentUserDetails = User.withUsername("student")
    //         .password(passwordEncoder.encode("secret"))
    //         .roles("student")
    //         .build();

    //     return new InMemoryUserDetailsManager(teacherUserDetails, studentUserDetails);
    // } 

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {    

        httpSecurity.csrf(csrf -> csrf.disable());

        // add configuration
    
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage.html")
                .failureUrl("/login.html?error");
        });

        httpSecurity.logout(logout -> {
            logout.logoutUrl("/perform_logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login.html");
        });

        httpSecurity.authorizeHttpRequests(requestConstraints -> {
                
            // only allow staff users access to /staff urls
            requestConstraints.requestMatchers("/staff.html").hasRole("staff");

            // only allow students access to /student urls
            requestConstraints.requestMatchers("/student.html").hasRole("student");

            // allow anybody to access the home page
            requestConstraints.requestMatchers("/homepage.html").authenticated();
            
            // allow anybody to access the login form
            requestConstraints.requestMatchers("/login*").permitAll();

            // allow anybody to access any other page (e.g. out quiz, cart or movie applications)
            requestConstraints.requestMatchers("/**").permitAll();

        });

        return httpSecurity.build();
    }
}

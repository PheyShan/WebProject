package org.generation.WebProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

//This annotation needed for login authentication
@Configuration
public class WebSecurityConfig {

    /*
    1) To provide a datasource : Create an object for connection to the database so that we can query our Users table. Datasource refers to the URL database, login credential to the database (admin/passw0rd#).
    We will need to perform DI (Dependency Injection) - Autowired annotation to an object with the datasource, Spring Boot will auto-inject the information from the application.properties on the related configuration of the database that it requires
    */

    @Autowired //inject all the datasource information from application.properties into this DataSource
    private DataSource dataSource;

    /*
    2) Once we have the datasource object, we can now connect to our database, make our query to the Users table to get the username, password, enabled, and the role for checking.
     */
    /*
    username=? : represents a parameter to be supplied by the client (from the browser) through the thymeleaf Security package
    usersByUsernameQuery : sets the query to be used for finding a user by their username
    authoritiesByUsernameQuery : Sets the query to be used for finding a user's
    authorities by their username
    */

    //DI is required only into own class "configAuthentication"
    @Autowired //@Autowire Dependency Injection the whole method instead pass in
    public void configAuthentication( AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)  //passing any datasource information to the dataScource() method
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }

    /*
    3) Using SecurityFilterChain method to customise the views based on auth. We need to configure which views need to login, which views do not require login.
     */

    //DI is NOT required into Spring Framework ready made class "SecurityFilterChain"
    @Bean //In Spring, everything is bean(same as object in Java)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //Configuration on the security filter, we need to customize (need to do the config our own based on the requirements)
        //CORS (@CrossOrigin) - disable cross-domain restriction for localhost development
        //csrf : Cross-Site Request Forgery
        http.csrf().disable();

        //Not using the Spring Security HttpSecurity default login page
        http.formLogin().loginPage("/login");

        //if user successfully login, user will be directed to the productform.html
        http.formLogin()
                .defaultSuccessUrl("/productform");

        //if user successfully logout, user will be directed to the index.html
        http.logout()
                .logoutSuccessUrl("/index");

        /*.antMatchers(......).permitAll() - tells Spring Security that these webpages
         do not need to have login services

        .antMatchers(.....).hasRole("ADMIN") - tells Spring Security that only user
        with ADMIN role will be able to access the productform.html

        logout method : configure logout functionality provided by Spring Security -
        ensure that the login session to be invalidated. Kill the session for us.
         */
        http.authorizeRequests()
                .antMatchers("/", "/products", "/aboutus").permitAll()  // "/" means the root
                .antMatchers("/productform/**").hasRole("ADMIN")   //is going to pass in ROLE_ADMIN, then go to part 2 .authoritiesByUsernameQuery() method // ** means any of internal path can be accessed /productform/add/item/...
                .and()
                .formLogin()
                //done in http.formLogin().loginPage("/login"); // http.formLogin().defaultSuccessUrl("/productform");
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

        return http.build();
    }




}

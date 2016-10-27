package com.example;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.models.Account;
import com.example.models.AccountDao;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
  @Autowired
  AccountDao accountRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        log.info("account " + account);
        if(account != null) {
        	log.info("account != null");
        return new User(account.getUsername(), account.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
        } else {
        	log.info("could not find the user");
          throw new UsernameNotFoundException("could not find the user '"
                  + username + "'");
        }
      }
      
    };
  }
  
  @Bean
  ServletRegistrationBean h2servletRegistration(){
      ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
      registrationBean.addUrlMappings("/console/*");
      return registrationBean;
  }
}
package demo.wx.mp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAccessDeineHandler customAccessDeineHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //允许登录接口匿名访问
                .antMatchers("/login").anonymous()
                //除此之外的所有接口需要鉴权认证
                .anyRequest().authenticated();
        http.formLogin()
                .defaultSuccessUrl("/");
        http.csrf().disable();
        http.logout();
        http.exceptionHandling().accessDeniedHandler(customAccessDeineHandler);//权限不足

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("test").password(new BCryptPasswordEncoder().encode("123456@")).roles("USER")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("zl206518")).roles("USER","ADMIN");
    }
}


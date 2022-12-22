@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.hatmani.securityservice.Config


import com.hatmani.securityservice.Service.Jwt.CustomAccessDeniedHandler
import com.hatmani.securityservice.Service.Jwt.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//je desactive le class WebSecurityConfig et je deminuer la version de spring boot
//car je doit utilise WebSecurityConfigurerAdapter
//car la version de intellij 2021.1 present une beug ou elle signale quelle
//n'a pas trouve le bean httpSecurity
//d'ou cette bug a ete corrige dans les version suivant que je peut pas l'avoir
//https://youtrack.jetbrains.com/issue/IDEA-296926/Spring-Boot-HttpSecurity-bean-not-detected-by-IDE
//en cas ou vous avez la version superieur que moi vou pouvez
//veuillez augmenter la version de spring boot Spring Boot 2.7.0 or newer
//>>>activer le class ErrorHandlerressource
//>>>desactiver ou supprimer ce class WebSecurityConfigadp
//>>> activer le class WebSecurityConfig
@Deprecated("Deprecated Class veuillez lire les note dans WebSecurityConfig")
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(
    prePostEnabled=true)
class WebSecurityConfigadp(private val userDetailsService: UserDetailsServiceImpl,
                           private val unauthorizedHandler: AuthEntryPointJwt,
                           private val jwtAuthenticationFilter: AuthTokenFilter,
                           private val accesDenid : CustomAccessDeniedHandler


): WebSecurityConfigurerAdapter() {





    //***********//
    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }


   @Bean
    @Throws(java.lang.Exception::class)
    fun  authenticationManager(authConfig: AuthenticationConfiguration):AuthenticationManager
    {
        return authConfig.authenticationManager
    }

    @Throws(java.lang.Exception::class)
    override fun configure(http: HttpSecurity?) {



        if (http != null) {
            http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/authapi/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/protected/**").authenticated()
                .antMatchers("/h2-console").permitAll()
                .and()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(accesDenid)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
        }


        if (http != null) {
            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        }
        if (http != null) {
            http.getSharedObject(AuthenticationManagerBuilder::class.java)
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
        }

        if (http != null) {
            http.headers().frameOptions().disable()
        }


    }
}
@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.hatmani.securityservice.Config


//je desactive ce class WebSecurityConfig et je deminuer la version de spring boot
//car je doit utilise WebSecurityConfigurerAdapter
//car la version de intellij 2021.1 present une beug ou elle signale quelle
//n'a pas trouve le bean httpSecurity
//d'ou cette bug a ete corrige dans les version suivant que je peut pas l'avoir
//https://youtrack.jetbrains.com/issue/IDEA-296926/Spring-Boot-HttpSecurity-bean-not-detected-by-IDE
//en cas ou vous avez la version superieur que moi vou pouvez activez ce class
//veuillez augmenter la version de spring boot Spring Boot 2.7.0 or newer
//activer le class ErrorHandlerressource
//desactiver ou supprimer WebSecurityConfigadp
//et activer ce class
/*
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(
    prePostEnabled=true)
class WebSecurityConfig(private val userDetailsService: UserDetailsServiceImpl,
                        private val unauthorizedHandler: AuthEntryPointJwt,
                        private val jwtAuthenticationFilter: AuthTokenFilter,
                        private val accesDenid : CustomAccessDeniedHandler


) {
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


    @Bean
    @Throws(java.lang.Exception::class)
     fun configure(http: HttpSecurity): SecurityFilterChain {



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


        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    http.getSharedObject(AuthenticationManagerBuilder::class.java)
        .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())//.authenticationManager(authenticationManager)

        http.headers().frameOptions().disable()

    return http.build()
    }
}

 */
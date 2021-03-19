package com.salesianostriana.dam.artSpace.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ConfigurationPasswordEncoder {

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

}
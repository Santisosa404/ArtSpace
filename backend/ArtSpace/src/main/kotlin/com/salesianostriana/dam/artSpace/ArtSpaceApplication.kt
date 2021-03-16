package com.salesianostriana.dam.artSpace

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class ArtSpaceApplication {
    @Bean
    fun commandLine() = CommandLineRunner {


    }
}

fun main(args: Array<String>) {
    runApplication<ArtSpaceApplication>(*args)
}

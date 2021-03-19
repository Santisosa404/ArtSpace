package com.salesianostriana.dam.artSpace

import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class ArtSpaceApplication {
    @Bean
    fun commandLine(
        uR : UserRepository,
        passwordEncoder: PasswordEncoder
    ) = CommandLineRunner {
    var user1 = User("ssosa",passwordEncoder.encode("miclave123"),"Santiago Sosa Díaz","santi@correo.com","conde de bustillo 89","Sevilla","Descripcion descriptiva del descriptor")
    uR.save(user1)
    }
}

fun main(args: Array<String>) {
    runApplication<ArtSpaceApplication>(*args)
}

package com.salesianostriana.dam.artSpace

import com.salesianostriana.dam.artSpace.models.ArtWork
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.repositories.ArtWorkRepository
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
        aR : ArtWorkRepository,
        passwordEncoder: PasswordEncoder
    ) = CommandLineRunner {
        var user1 = User("ssosa",passwordEncoder.encode("miclave123"),"Santiago Sosa Díaz","santi@correo.com","conde de bustillo 89","Sevilla","Descripcion descriptiva del descriptor",
            mutableSetOf("USER"))
        var user2 = User("LmLopez",passwordEncoder.encode("miclave123"),"Luismi Lopez Magaña","luismi@correo.com","conde de bustillo 89","Sevilla","Descripcion descriptiva del descriptor",
            mutableSetOf("USER"))


        var art1 = ArtWork("Mushroom Rings",20.10,"Descripcion de los pendientes","Poliester",user1)
        var art2 = ArtWork("Oleo sobre lienzo",20.10,"Descripcion de los oleos","Poliester",user1)
        var art3 = ArtWork("Cenicero barro",20.10,"Descripcion de los cenicero","Poliester",user1)
        var art4 = ArtWork("Campanilla campana",20.10,"Descripcion de los campana","Poliester",user2)
        var art5 = ArtWork("Mushroom Rings de oro rechulos",20.10,"Descripcion de los rechulos","Poliester",user2)

        user1.addPost(art1)
        user1.addPost(art2)
        user1.addPost(art3)
        user2.addPost(art4)
        user2.addPost(art5)

        uR.save(user1)
        uR.save(user2)
        aR.saveAll(mutableListOf(art1,art2,art3,art4,art5))

    }
}

fun main(args: Array<String>) {
    runApplication<ArtSpaceApplication>(*args)
}

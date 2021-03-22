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

        //Usuarios
        var user1 = User("ssosa",passwordEncoder.encode("miclave123"),"Santiago Sosa","santi@correo.com","conde de bustillo 89","Sevilla","Descripcion descriptiva del descriptor",
            mutableSetOf("USER"))
        var user2 = User("lmLopes",passwordEncoder.encode("miclave123"),"Luismi Lopez","luismi@correo.com","conde de bustillo 89","Sevilla","Descripcion descriptiva del descriptor",
            mutableSetOf("USER"))
        var user3 = User("the_goblin_shop",passwordEncoder.encode("miclave123"),"Candela Parish","thegoblinshop@correo.com","London","London","I'm a goblin and this is my shop\nMagic polymer clay jewelry, Dreadlock art, dream catchers,face paint & more magic stuff for all forest creatures", mutableSetOf("USER"))
        var user4 = User("deskolorao",passwordEncoder.encode("miclave123"),"Manuel Alvarez","deskolorao@correo.com","Calle Joaquin Morales y Torres","Sevilla, Santa justa","Pagina de artes varios", mutableSetOf("USER"))


        var art1 = ArtWork("Mushroom Rings",20.10,"Pendientes hechoisde arcilla en forma de seta","Arcilla",user3)
        var art2 = ArtWork("Doña Loli y Luisillo",15.7,"Te extrañamos Doña Loll ","Oleo",user4)
        var art3 = ArtWork("Cenicero barro",5.0,"Cenicero basico de arcilla","Arcilla",user1)
        var art4 = ArtWork("Campanilla campana",20.10,"Descripcion de los campana","Poliester",user3)
        var art5 = ArtWork("El interior de la caverna de Platón",21.50,"Aquella vez en cazalla no habia agua caliente","Poliester",user4)
        var art6 = ArtWork("Examen de SQL",20.10,"Si has conseguido esto eres un buen padawan mostrando las publicsaciones de gente a la que no sigues","ORGULLO",user2)

        //Agregacion de post
        user3.addPost(art1)
        user4.addPost(art2)
        user1.addPost(art3)
        user3.addPost(art4)
        user4.addPost(art5)
        user2.addPost(art6)

        //Seguimiento
        user1.addFollower(user3)
        user1.addFollower(user4)
        user3.addFollower(user1)
        user3.addFollower(user4)
        user4.addFollower(user3)
        user2.addFollower(user1)


        //Guardado
        uR.saveAll(mutableListOf(user1,user2,user3,user4))
        aR.saveAll(mutableListOf(art1,art2,art3,art4,art5,art6))

    }
}

fun main(args: Array<String>) {
    runApplication<ArtSpaceApplication>(*args)
}

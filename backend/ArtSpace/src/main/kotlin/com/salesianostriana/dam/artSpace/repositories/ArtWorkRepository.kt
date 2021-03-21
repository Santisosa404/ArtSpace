package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.ArtWork
import com.salesianostriana.dam.artSpace.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ArtWorkRepository : JpaRepository<ArtWork,UUID> {

    @Query("SELECT a FROM User u JOIN u.following f JOIN f.artWorks a WHERE u.id = :user_principal_id")
    fun followingArtWorks(@Param("user_principal_id") id : UUID) : MutableList<ArtWork>

    @Query("SELECT a FROM ArtWork a where a.user NOT IN :user_following AND a.user.id != :user_principal_id")
    fun notFollowingArtWorks(@Param("user_following") following : MutableList<User>, @Param("user_principal_id") id: UUID) : MutableList<ArtWork>
}


/*
SELECT * FROM User u JOIN u.artWorks a JOIN u.following f WHERE a.user.id != :user_principal_id AND a.user.id != f.id
SELECT * FROM ArtWork a WHERE a.user.id != :user_principal_id AND a.user.id NOT IN SELECT a FROM User u JOIN u.following f JOIN f.artWorks a WHERE u.id = :user_principal_id

SELECT a FROM ArtWork a where a.user.id NOT IN :user_following

Tengo que buscar todos las publicaciones cuyo usuario id no este en la tabla following

 */
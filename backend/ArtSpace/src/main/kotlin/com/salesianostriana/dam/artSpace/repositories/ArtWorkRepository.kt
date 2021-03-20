package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.ArtWork
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ArtWorkRepository : JpaRepository<ArtWork,UUID> {

    @Query("SELECT a FROM User u JOIN u.following f JOIN f.artWorks a WHERE u.id = :user_principal_id")
    fun followingArtWorks(@Param("user_principal_id") id : UUID) : MutableList<ArtWork>
}
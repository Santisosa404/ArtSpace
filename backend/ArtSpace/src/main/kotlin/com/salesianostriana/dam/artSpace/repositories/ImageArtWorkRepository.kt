package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.ImageArtWork
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ImageArtWorkRepository : JpaRepository<ImageArtWork,UUID> {

}
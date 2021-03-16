package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.ArtWork
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ArtWorkRepository : JpaRepository<ArtWork,UUID> {
}
package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.ArtWork
import com.salesianostriana.dam.artSpace.repositories.ArtWorkRepository
import org.springframework.stereotype.Service

@Service
class ArtWorkService(
    private val artR: ArtWorkRepository
) {
    fun save(artWork : ArtWork) = artR.save(artWork)



}
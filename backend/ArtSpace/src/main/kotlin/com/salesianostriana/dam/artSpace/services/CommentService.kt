package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.Comment
import com.salesianostriana.dam.artSpace.repositories.CommentRepository
import org.springframework.stereotype.Service


@Service
class CommentService(
    private val cR : CommentRepository
) {

    fun save(comment: Comment) = cR.save(comment)

}
package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.Comment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CommentRepository : JpaRepository<Comment,UUID> {
}
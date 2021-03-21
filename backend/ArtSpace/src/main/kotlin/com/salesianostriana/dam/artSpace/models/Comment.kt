package com.salesianostriana.dam.artSpace.models

import java.util.*
import javax.persistence.*
import javax.validation.constraints.*

@Entity
class Comment(
        @get:NotBlank(message = "{comment.body.notNull}")
        @Lob var body: String,

        @ManyToOne
        var artWork: ArtWork?,
        @Id @GeneratedValue var id: UUID?=null
) {
        fun toDTO() = CommentDTO(this.body)
}
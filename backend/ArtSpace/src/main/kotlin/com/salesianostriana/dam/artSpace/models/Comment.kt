package com.salesianostriana.dam.artSpace.models

import java.util.*
import javax.persistence.*

@Entity
class Comment(
        @Lob var body: String,

        @ManyToOne
        var artWork: ArtWork?,
        @Id @GeneratedValue var id: UUID?=null
) {
        fun toDTO() = CommentDTO(this.body)
}
package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.*

@Entity
class Comment(
        @Lob var body: String,

        @ManyToOne
        var artWork: ArtWork?,
        @Id @GeneratedValue var id: UUID
) {
}
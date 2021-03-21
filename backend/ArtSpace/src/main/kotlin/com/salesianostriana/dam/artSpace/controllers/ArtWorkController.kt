package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.EntityNotfoundException
import com.salesianostriana.dam.artSpace.exceptions.ListEntityNotFoundException
import com.salesianostriana.dam.artSpace.exceptions.SingleEntityNotFoundException
import com.salesianostriana.dam.artSpace.models.*
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.CommentService
import com.salesianostriana.dam.artSpace.services.ImageArtWorkService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/artwork")
class ArtWorkController(
    private val imgS: ImageArtWorkService,
    private val artS: ArtWorkService,
    private val commS: CommentService
) {


    @PostMapping("/")
    fun creatArtWork(
        @RequestPart artWorkDTO: ArtWorkNewDTO,
        @RequestPart file: MultipartFile,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<ArtWorkNewDTO> {
        var artWork = ArtWork(artWorkDTO.tittle, artWorkDTO.price, artWorkDTO.description, artWorkDTO.material, user)
        artS.save(artWork)
        var iReal = imgS.save(file)
        artWork.addImg(iReal)
        artS.save(artWork)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/")
    fun listAll(): ResponseEntity<Any> {
        var all = artS.findAll()
        return if (all.isNotEmpty())
            ResponseEntity.status(200).body(all.map { it.toDTO() })
        else
            ResponseEntity.noContent().build()

    }

    @DeleteMapping("/{id}")
    fun deleteArtWork(@PathVariable id: UUID): ResponseEntity<Any> {


            var art = artS.findById(id).orElseThrow { ListEntityNotFoundException(ArtWork::class.java) }
            artS.delete(art)
            artS.save(art)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun getDetails(@PathVariable id: UUID): ResponseEntity<ArtWorkDTO> {
        var art = artS.findById(id).orElseThrow { ListEntityNotFoundException(ArtWork::class.java) }
        return ResponseEntity.ok().body(art.toDTO())
    }

    @PutMapping("/{id}")
    fun editArtWork(@PathVariable id: UUID, @RequestBody artWorkEditDTO: ArtWorkEditDTO): ResponseEntity<Any> {
            artS.findById(id).map {
                it.tittle = artWorkEditDTO.tittle
                it.description = artWorkEditDTO.description
                it.material = artWorkEditDTO.material
                it.price = artWorkEditDTO.price
                artS.save(it)
            }.orElseThrow {
                SingleEntityNotFoundException(id.toString(), ArtWork::class.java)
            }
            return ResponseEntity.status(204).build()
    }

        @PostMapping("/{id}/comment")
        fun addComment(@PathVariable id: UUID, @RequestBody comment: Comment): ResponseEntity<Any> {
            return if (artS.existById(id)) {
                var art = artS.findById(id).get()
                art.addComment(comment)
                commS.save(comment)
                artS.save(art)
                ResponseEntity.ok().build()
            } else {
                ResponseEntity.notFound().build()
            }
        }

    }




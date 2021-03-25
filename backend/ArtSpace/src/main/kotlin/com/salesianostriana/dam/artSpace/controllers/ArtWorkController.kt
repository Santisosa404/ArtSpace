package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.ListEntityNotFoundException
import com.salesianostriana.dam.artSpace.exceptions.SingleEntityNotFoundException
import com.salesianostriana.dam.artSpace.models.*
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.CommentService
import com.salesianostriana.dam.artSpace.services.ImageArtWorkService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/artwork")
class ArtWorkController(
        private val imgS: ImageArtWorkService,
        private val artS: ArtWorkService,
        private val commS: CommentService
) {

    @ApiOperation(value = "Crea un artwork junto con una imagen del mismo",notes = "Es necesario pasar la imagen")
    @PostMapping("/")
    fun creatArtWork(
        @ApiParam(value = "Artwork", required = true, type = "ArtWorkDTO") @Valid @RequestPart artWorkDTO: ArtWorkNewDTO,
        @ApiParam(value = "Imagen del Artwork a subir",required = true, type = "multipartfile") @RequestPart file: MultipartFile,
        @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User
    ): ResponseEntity<ArtWorkNewDTO> {
        var artWork = ArtWork(artWorkDTO.tittle, artWorkDTO.price, artWorkDTO.description, artWorkDTO.material, user)
        artS.save(artWork)
        var iReal = imgS.save(file)
        artWork.addImg(iReal)
        artS.save(artWork)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
    @ApiOperation(value = "Devuelve todos los artworks en formato DTO")
    @GetMapping("/")
    fun listAll(): ResponseEntity<Any> {
        var all = artS.findAll()
        return if (all.isNotEmpty())
            ResponseEntity.status(200).body(all.map { it.toDTO() })
        else
            ResponseEntity.noContent().build()

    }
    @ApiOperation(value = "Elimina un artwork a traves de su id")
    @DeleteMapping("/{id}")
    fun deleteArtWork(@ApiParam(value = "Id del artwork a elimniar",required = true, type = "UUID") @PathVariable id: UUID): ResponseEntity<Any> {
        var art = artS.findById(id).orElseThrow { ListEntityNotFoundException(ArtWork::class.java) }
        artS.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation(value = "Devuelve un artwork mediante su id")
    @GetMapping("/{id}")
    fun getDetails(@ApiParam(value = "Id del artwork a buscar",required = true, type = "UUID") @PathVariable id: UUID): ResponseEntity<ArtWorkDTO> {
        var art = artS.findById(id).orElseThrow { ListEntityNotFoundException(ArtWork::class.java) }
        return ResponseEntity.ok().body(art.toDTO())
    }
    @ApiOperation(value = "Edita un artwork mediante su id", notes = "No se puede editar la imagen")
    @PutMapping("/{id}")
    fun editArtWork(@ApiParam(value = "Id del artwork a editar",required = true, type = "UUID") @PathVariable id: UUID, @ApiParam(value = "Artwork editado",required = true, type = "ArtworkEditDTO") @Valid @RequestBody artWorkEditDTO: ArtWorkEditDTO): ResponseEntity<Any> {
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
    @ApiOperation(value = "Agrega un comentario a un artwork mediante su id", notes = "Pese a tener dos valores el comment, solo es necesario el body para la creacion")
    @PostMapping("/{id}/comment")
    fun addComment(@ApiParam(value = "Id del artwork ",required = true, type = "UUID") @PathVariable id: UUID, @ApiParam(value = "Comentario a agregar",required = true,type = "CommentDTO") @Valid @RequestBody commentDTO: CommentDTO): ResponseEntity<Any> {
        return if (artS.existById(id)) {
            var comment = Comment(commentDTO.body)
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




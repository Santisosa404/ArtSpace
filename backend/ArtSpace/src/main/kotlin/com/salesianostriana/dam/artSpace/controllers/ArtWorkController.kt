package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.*
import com.salesianostriana.dam.artSpace.repositories.ArtWorkRepository
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.ImageArtWorkService
import com.salesianostriana.dam.artSpace.upload.ImgurBadRequest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.handler.ResponseStatusExceptionHandler
import java.util.*

@RestController
@RequestMapping("/artwork")
class ArtWorkController(
    private val imgS: ImageArtWorkService,
    private val artS: ArtWorkService,
    private val artR : ArtWorkRepository
) {


    @PostMapping("/")
    fun creatArtWork(
        @RequestPart artWorkDTO: ArtWorkNewDTO,
        @RequestPart file: MultipartFile,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<ArtWorkNewDTO> {
        //Objeto imagenVivienda
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

        if (artS.existById(id)) {
            var art = artS.findById(id).get()
            artS.delete(art)
        }
            return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun getDetails(@PathVariable id: UUID): ResponseEntity<ArtWorkDTO> {
        var art = artS.findById(id).get()
        return ResponseEntity.ok().body(art.toDTO())
    }

    @PutMapping("/{id}")
    fun editArtWork(@PathVariable id: UUID, @RequestBody artWorkEditDTO: ArtWorkEditDTO) : ResponseEntity<Any>{
      return if (artS.existById(id)){
           artS.findById(id).map {
               it.tittle = artWorkEditDTO.tittle
               it.description = artWorkEditDTO.description
               it.material = artWorkEditDTO.material
               it.price = artWorkEditDTO.price
               artS.save(it)
           }
           ResponseEntity.status(204).build()
       }else{
           ResponseEntity.notFound().build()
      }

    }

}



package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.*
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.ImageArtWorkService
import com.salesianostriana.dam.artSpace.upload.ImgurBadRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/artwork")
class ArtWorkController(
        private val imgS: ImageArtWorkService,
        private val artS: ArtWorkService
) {


    @PostMapping("/")
    fun creatArtWork(@RequestPart artWorkDTO: ArtWorkNewDTO, @RequestPart file: MultipartFile, @AuthenticationPrincipal user: User): ResponseEntity<ArtWorkNewDTO> {
        //Objeto imagenVivienda
        var artWork = ArtWork(artWorkDTO.tittle, artWorkDTO.price, artWorkDTO.description, artWorkDTO.material, user)
        artS.save(artWork)
        var iReal = imgS.save(file)
        artWork.addImg(iReal)
        artS.save(artWork)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }


}



package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.ImageArtWork
import com.salesianostriana.dam.artSpace.repositories.ImageArtWorkRepository
import com.salesianostriana.dam.artSpace.upload.ImgurImageAttribute
import com.salesianostriana.dam.artSpace.upload.ImgurService
import com.salesianostriana.dam.artSpace.upload.ImgurStorageService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

open class BaseService<T, ID, R : JpaRepository<T, ID>> {

    @Autowired
    protected lateinit var repository: R

    open fun save(t: T) = repository.save(t)
    open fun findAll(): List<T> = repository.findAll()
    open fun findById(id: ID) = repository.findById(id)
    open fun edit(t: T) = save(t)

    open fun deleteById(id: ID) {
        findById(id).ifPresent { this.delete(it) }
    }

    open fun delete(t: T) = repository.delete(t)

    open fun deleteAll() = repository.deleteAll()

}

@Service
class ImageArtWorkService(
    private val imageStorageService: ImgurStorageService,
) : BaseService<ImageArtWork, UUID, ImageArtWorkRepository>() {

    val logger: Logger = LoggerFactory.getLogger(ImageArtWork::class.java)


    fun save(file: MultipartFile) : ImageArtWork {
        var imageAttribute : Optional<ImgurImageAttribute> = Optional.empty()
        if (!file.isEmpty) {
            imageAttribute = imageStorageService.store(file)
        }

        var imageArtWork = ImageArtWork()
        imageArtWork.img = imageAttribute.orElse(null)
        return save(imageArtWork)
    }

    override fun delete(imageArtWork: ImageArtWork) {
        logger.debug("Eliminando la imagen $imageArtWork")
        super.delete(imageArtWork)
    }

}


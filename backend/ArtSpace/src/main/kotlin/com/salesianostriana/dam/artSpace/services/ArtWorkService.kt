package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.ArtWork
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.repositories.ArtWorkRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArtWorkService(
    private val artR: ArtWorkRepository
) {
    /**
     * Guarda un Artwork en la base de datos
     * @author Santiago Sosa
     * @param artWork; publicacion a guardar
     */
    fun save(artWork : ArtWork) = artR.save(artWork)
    /**
     * Devuelve un booleano si existe el Artwork
     * @param artWork; publicacion a guardar
     * @author Santiago Sosa
     * @return True si existe, False si no
     */
    fun existById(id : UUID) = artR.existsById(id)
    /**
     * Busca un Artwork
     *
     * @author Santiago Sosa
     * @param artWork; publicacion a guardar
     */
    fun findById(id: UUID) = artR.findById(id)
    fun findAll()=artR.findAll()
    fun deleteById(id: UUID)= artR.deleteById(id)
    fun delete(artWork: ArtWork) = artR.delete(artWork)
    fun allFollowingArtWorks(id: UUID) = artR.followingArtWorks(id)
    fun allNotFollowingArtsWorks(following : MutableList<User>, id: UUID) = artR.notFollowingArtWorks(following, id)
    fun allArtWorkById(cartId : MutableList<UUID>) = artR.getAllById(cartId)

    fun price(artWorks : MutableList<ArtWork>) : Double{
        var res = 0.0
        artWorks.forEach {
            res += it.price
        }
        return res
    }
}
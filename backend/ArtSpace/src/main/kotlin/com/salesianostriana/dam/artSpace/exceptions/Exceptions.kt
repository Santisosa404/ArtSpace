package com.salesianostriana.dam.artSpace.exceptions

import java.lang.RuntimeException
import javax.persistence.EntityNotFoundException

open class EntityNotfoundException(val msg: String) : RuntimeException(msg)

data class SingleEntityNotFoundException(
    val id : String,
    val javaClass: Class<out Any>
) : EntityNotFoundException("La entidad de tipo ${javaClass.name} con ID: ${id} no se ha podido encontrar")

data class ListEntityNotFoundException(
    val javaClass: Class<out Any>
) : EntityNotFoundException("No se pueden entidades del tipo ${javaClass.name} para esta consulta")



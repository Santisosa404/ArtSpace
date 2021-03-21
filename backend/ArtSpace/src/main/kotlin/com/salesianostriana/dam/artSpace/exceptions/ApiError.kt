package com.g2t4.realState.error

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import java.time.LocalDate
import java.time.LocalDateTime
import javax.management.monitor.StringMonitor

data class ApiError (
    val estado: HttpStatus,
    val mensaje: String?,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val subErrores: List<out ApiSubError>? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    val fecha: LocalDateTime = LocalDateTime.now()
)

open abstract class ApiSubError

data class ApiValidationSubError(
    val objeto : String,
    val campo : String,
    val valorRechazado : Any?,
    val mensaje : String?
) : ApiSubError()
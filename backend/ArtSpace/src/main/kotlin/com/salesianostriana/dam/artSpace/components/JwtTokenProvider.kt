package com.salesianostriana.dam.artSpace.components

import com.salesianostriana.dam.artSpace.models.User
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.security.core.Authentication
import java.lang.Exception
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class JwtTokenProvider {

    companion object {
        const val TOKEN_HEADER = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        const val TOKEN_TYPE = "JWT"
    }

    private val jwtSecreto: String = "4jqrkfrqifh329r`hj9r324ur30ru0uie09reqwu9qweuf09+eqwj9f123456llevalatararaunvestidoblancollenodecascabeles"
    private val jwtDuracionToken: Long = 3
    private val jwtDuracionRefreshToken: Long = 10

    private fun generateTokens(user: User, isRefreshToken: Boolean): String {
        val tokenExpirationDate =
            Date.from(
                Instant.now().plus(if (isRefreshToken) jwtDuracionRefreshToken else jwtDuracionToken, ChronoUnit.DAYS)
            )
        val builder = Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(jwtSecreto.toByteArray()), SignatureAlgorithm.HS512)
            .setHeaderParam("typ", TOKEN_TYPE)
            .setSubject(user.id.toString())
            .setExpiration(tokenExpirationDate)
            .setIssuedAt(Date())
            .claim("refresh", isRefreshToken)

        if (!isRefreshToken) {
            builder
                .claim("fullname", user.fullname)
                .claim("roles", user.roles.joinToString())

        }
        return builder.compact()
    }

    //Genera el token para el user autenticado

    fun generateToken(auth: Authentication): String {
        val user: User = auth.principal as User
        return generateTokens(user, false)
    }

    //Genera el token para un user determinado
    fun generateToken(user: User) = generateTokens(user, false)

    //Genera el token de refresco para el user autenticadp
    fun generateRefreshToken(auth: Authentication): String {
        val user: User = auth.principal as User
        return generateTokens(user, true)
    }

    //Genera el token de refresto para un user
    fun generateRefreshToken(user: User) = generateTokens(user, true)


    //Procesar el token
    private val parser = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.toByteArray())).build()

    private val logger: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    //Recibe el token y devuelve el id del user
    fun getUserIdFromJWT(token: String): UUID = UUID.fromString(parser.parseClaimsJws(token).body.subject)


    //Metodo de validacion del token. Comprueba que no tenga errores y si estamos usuando el token de refresco
    private fun validateToken(token: String, isRefreshToken: Boolean): Boolean {
        try {
            val claims = parser.parseClaimsJws(token)
            if (isRefreshToken != claims.body["refresh"])
                throw  UnsupportedJwtException("No se a utiliado el token correcto")
            return true
        } catch (ex: Exception) {
            with(logger) {
                when (ex) {
                    is SignatureException -> info("Error en la firma del token ${ex.message}")
                    is MalformedJwtException -> info("Token malformado ${ex.message}")
                    is ExpiredJwtException -> info("Token expirado ${ex.message}")
                    is UnsupportedJwtException -> info("Token no soportado ${ex.message}")
                    is IllegalArgumentException -> info("Token incompleto (claims vacío) ${ex.message}")
                    else -> info("Error indeterminado")
                }
            }
        }
        return false
    }

    //Recibe token de refresco y lo valida
    fun validateRefreshToken(token: String) = validateToken(token, true)

    //Recibe token de autentificacion y lo valida
    fun validateAuthToken(token: String) = validateToken(token, false)


}
package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.components.BearerTokenExtractor
import com.salesianostriana.dam.artSpace.components.JwtTokenProvider
import com.salesianostriana.dam.artSpace.models.*
import com.salesianostriana.dam.artSpace.services.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val bearerTokenExtractor: BearerTokenExtractor,
    private val uS: UserService
) {
    @ApiOperation(value = "Loguea a un usuario mediante su nombre de usuario y su contraseña, devolviendo un DTO con el token")
    @PostMapping("/login")
    fun login(@ApiParam(value = "Usuario a hacer login",required = true,type = "UserLoginDTO") @Valid @RequestBody userLoginDTO: UserLogDTO): ResponseEntity<UserTokenDTO> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(userLoginDTO.username, userLoginDTO.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val user = authentication.principal as User
        val jwtToken = jwtTokenProvider.generateToken(user)
        val jwtRefreshToken = jwtTokenProvider.generateRefreshToken(user)
        return ResponseEntity.status(200).body(UserTokenDTO(jwtToken, jwtRefreshToken, user.toUserRespDTO()))
    }
    @ApiOperation(value = "Registra a un usuario en la api")
    @PostMapping("/register")
    fun register(@ApiParam(value = "Usuario a registrar",required = true, type = "UserRegDTO") @Valid @RequestBody userRegDTO: UserRegDTO) =
        uS.createUser(userRegDTO).map { ResponseEntity.status(HttpStatus.CREATED).body(it.toUserRespDTO()) }
            .orElseThrow {
                ResponseStatusException(HttpStatus.BAD_REQUEST)
            }


}
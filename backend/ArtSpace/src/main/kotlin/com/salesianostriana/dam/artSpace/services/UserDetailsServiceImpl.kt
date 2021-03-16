package com.salesianostriana.dam.artSpace.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsService")
class UserDetailsServiceImpl(
    private val uS: UserService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        uS.findByUsername(username).orElseThrow {
            UsernameNotFoundException("El usuario $username no ha sido encontrado")
        }


}
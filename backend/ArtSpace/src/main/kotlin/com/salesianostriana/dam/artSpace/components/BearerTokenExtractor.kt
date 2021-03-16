package com.salesianostriana.dam.artSpace.components

import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class BearerTokenExtractor {


    fun getJwtFromRequest(request: HttpServletRequest) : Optional<String>{
        val bearerToken = request.getHeader(JwtTokenProvider.TOKEN_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtTokenProvider.TOKEN_PREFIX))
            Optional.of(bearerToken.substring(JwtTokenProvider.TOKEN_PREFIX.length,bearerToken.length)) else Optional.empty()
    }



}
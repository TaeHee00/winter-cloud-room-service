package com.wintercloud.room.auth

import com.wintercloud.room.exception.BusinessException
import com.wintercloud.room.exception.ErrorCode
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.UUID

@Component
class LoginUserArgumentResolver : HandlerMethodArgumentResolver {
    private final val logger = KotlinLogging.logger {}

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser::class.java)
        val hasUserDtoType = UUID::class.java.isAssignableFrom(parameter.getParameterType())

        return hasLoginUserAnnotation && hasUserDtoType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<in Any> {

        logger.info { exchange.request.headers.toString() }
        val userId = exchange.request.headers.getFirst("X-USER-ID")
            ?: return Mono.error(BusinessException(ErrorCode.UNAUTHORIZED))
//        val rolesString: String? = webRequest.getHeader("X-USER-ROLES")

//        val roles: MutableList<String?>? =
//            if (rolesString != null) Arrays.asList(rolesString.split(",".toRegex()).dropLastWhile { it.isEmpty() }
//                .toTypedArray()) else mutableListOf<String?>()

        //
        return Mono.just(UUID.fromString(userId))
    }

}
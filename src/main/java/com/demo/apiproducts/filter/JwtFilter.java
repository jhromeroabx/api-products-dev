package com.demo.apiproducts.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

   @Value("${jwt.secret}")
   private String secret;

   @Override
   protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain) throws ServletException, IOException {

      String header = request.getHeader("Authorization");
      if (Objects.nonNull(header) && header.startsWith("Bearer ")) {
         String jwt = header.replace("Bearer ", "");
         Algorithm algorithm = Algorithm.HMAC256(secret);
         var verifier = JWT.require(algorithm).build();
         String userId = verifier.verify(jwt).getClaim("userId").asInt().toString();

         UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                 new User(userId,
                          "",
                          Collections.emptyList()
                 ), null, Collections.emptyList());
         SecurityContextHolder.getContext().setAuthentication(authToken);
      }

      filterChain.doFilter(request, response);
   }

}

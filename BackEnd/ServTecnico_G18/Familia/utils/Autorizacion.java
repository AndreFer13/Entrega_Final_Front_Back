package com.ServTecnico_G18.Familia.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class Autorizacion implements Filter {
    // Llave de cifrado y descifrado
    public static final String KEY = "bjhhjgffgdvkjbkjbkjbjhvhjgd";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                     //Obtener el path principal 
                     HttpServletRequest httpServletRequest=(HttpServletRequest) request;
                
                     HttpServletResponse httpServletResponse=(HttpServletResponse) response;
                     httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                     httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
     
     

        String url = httpServletRequest.getRequestURI();
        // http://localhost:8080 ------->url

        if (url.contains("/api/usuarios") || url.contains("/api/usuarios/login")) {
            chain.doFilter(request, response);
        }else{
            String hash=httpServletRequest.getHeader("Authorization");
            if(hash==null || hash.trim().equals("")){
                response.setContentType("application/json");
                String body="{\"autorizacion\":\"NO\"}";
                response.getWriter().write(body);
            }try{
                //Lectura de carga util del JWT
                Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                if((url.contains("/api/verificar")||url.contains("/api/Agenda") || url.contains("/api/Stecnico"))&& (!claims.getBody().get("username").equals(""))){
                    chain.doFilter(request, response);
                }

            }catch(MalformedJwtException e){
                response.setContentType("application/json");
                String body="{\"autorizacion\":\"TOKEN NO VALIDO\"}";
                response.getWriter().write(body);
            }catch(Exception e){
                System.out.println(e);
            }



                
        }
    }

}

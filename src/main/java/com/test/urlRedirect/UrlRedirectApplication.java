package com.test.urlRedirect;

import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UrlRedirectApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlRedirectApplication.class, args);
    }

    /*
     * @GetMapping("/") public String index(){ return "index.html" ; }
     */

    @GetMapping("/")
    public String demo(Authentication auth) {
        return String.format("Hello, %s. You have authorities: %s", auth.getPrincipal(),
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));
    }

}

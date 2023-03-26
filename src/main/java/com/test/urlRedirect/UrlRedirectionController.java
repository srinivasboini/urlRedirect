package com.test.urlRedirect;

import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import waffle.servlet.WindowsPrincipal;

@RestController
public class UrlRedirectionController {

    Logger logger = LoggerFactory.getLogger(UrlRedirectionController.class);

    @Autowired
    private AppConfiguration configuration;

    @GetMapping("/dispatch")
    public void redirect(HttpServletResponse response, Authentication auth) {

        String username = null;
        if (auth != null) {

            WindowsPrincipal principal1 = (WindowsPrincipal) auth.getPrincipal();
            if (principal1 != null && principal1.getName().contains("\\")) {
                username = principal1.getName().split(Pattern.quote("\\"))[1];
            }

            logger.debug("username" + username);

        }

        String finalUsername = username;
        String url = configuration.getList().stream()
                .filter(e -> Arrays.asList(e.getGroup().split(",")).contains(finalUsername)).findFirst()
                .map(Config::getUrl).orElse("http://localhost:8080/default");

        response.setHeader("Location", url);
        response.setStatus(302);

    }

}

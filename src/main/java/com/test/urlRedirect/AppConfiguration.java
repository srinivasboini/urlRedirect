package com.test.urlRedirect;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "config")
public class AppConfiguration {

    public List<Config> getList() {
        return list;
    }

    public void setList(List<Config> configs) {
        this.list = configs;
    }

    private List<Config> list = new ArrayList<>();
}

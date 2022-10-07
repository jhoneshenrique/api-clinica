package com.jhones.apiclinica.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jhones.apiclinica"))
                .paths(PathSelectors.any()) //URI root
                .build()
                .apiInfo(metaInfo());

    }



    //API Information
    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Clínica Média Restful API",
                "Essa é uma API de uma clínica médica. Ela provê os recursos necessários para gerenciar" +
                        " médicos (e seus horários disponíveis), pacientes e suas consultas.",
                "1.0",
                "Terms of Service",
                new Contact("Jhones Henrique", "https://github.com/jhoneshenrique/api-clinica",
                        "jhoneshenriquedasilva@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}

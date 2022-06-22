package com.zzami.alarm.api.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.common.collect.Lists;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder
                .builder()
                .operationsSorter(OperationsSorter.ALPHA)
                .tagsSorter(TagsSorter.ALPHA)
                .build();
    }
    
    @Bean
    public Docket api() {
        ArrayList<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessageBuilder().code(400).message("400 잘못된 요청입니다.").build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("401 인증체크 실패입니다.").build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("403 인증 성공 후 권한체크 실패입니다.").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("404 존재하지 않는 파일입니다.").build());
        responseMessages.add(new ResponseMessageBuilder().code(405).message("405 사용되지 않는 method 입니다.").build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("500 알 수 없는 오류가 발생하였습니다.").build());
        
        ParameterBuilder aParamBuilder = new ParameterBuilder();
        aParamBuilder.name("timezone")
            .description("타임존 지역명")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();
        List<Parameter> aParams = new ArrayList<>();
        aParams.add(aParamBuilder.build());
        
      return new Docket(DocumentationType.SWAGGER_2)
//              .consumes(getConsumeContentTypes())
              .useDefaultResponseMessages(false)
              .globalResponseMessage(RequestMethod.GET, responseMessages)
              .globalResponseMessage(RequestMethod.POST, responseMessages)
              .globalResponseMessage(RequestMethod.PUT, responseMessages)
              .globalResponseMessage(RequestMethod.DELETE, responseMessages)//
              //.globalOperationParameters(aParams)
              .select()//
              .apis(RequestHandlerSelectors.basePackage("com.zzami.alarm.api.controller"))
              .paths(PathSelectors.regex("/api.*"))//
              .build()//
              .apiInfo(metadata())//
              .useDefaultResponseMessages(false)//
              .securitySchemes(Lists.newArrayList(apiKey()))
              .securityContexts(Lists.newArrayList(securityContext())) //
              .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo metadata() {
      return new ApiInfoBuilder()
          .title("짜미 알람 REST API")
          .description("[설명]<br>"
              + "/api/o/**  인증이 필요없는 API(권한필요없음)<br>"
              + "/api/p/**  인증 필요<br>")
          .version("1.0.0")
          .license("Copyright ⓒ zzami.com All rights reserved.")
          .contact(new Contact(null, null, "hamal0381@gmail.com"))//
          .build();
    }
    
    private ApiKey apiKey() {
        return new ApiKey("JWT","Authorization", "header");
    }
    
    private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
        return springfox.documentation.spi.service.contexts.SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }
    
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        
        return Lists.newArrayList(new SecurityReference("JWT",authorizationScopes));
        
    }
    
    //swagger에서 request header강제 세팅시 사용
    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }
    
    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
}


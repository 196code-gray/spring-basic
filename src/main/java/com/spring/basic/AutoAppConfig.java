package com.spring.basic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
/*
현재 AppConfig에 Configuration 애너테이션이 붙어있으므로 궅이 ComponentScan 이 아니여도 Bean에 등록이 됨.
따라서 Configuration 애너테이션은 제외해주겠다는 으미.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}

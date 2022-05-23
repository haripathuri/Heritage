package com.heritage.annotations;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:33 PM
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RequestMapping("/api/v1/heritage/migration")
public @interface APIUrl {
}

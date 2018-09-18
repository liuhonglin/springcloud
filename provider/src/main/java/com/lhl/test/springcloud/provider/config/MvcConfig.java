package com.lhl.test.springcloud.provider.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.*;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();

        SerializerFeature[] serializerFeatures = new SerializerFeature[] {
                SerializerFeature.BrowserCompatible,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullStringAsEmpty,
        };

        config.setSerializerFeatures(serializerFeatures);
        config.setCharset(Charset.forName("UTF-8"));

        converter.setFastJsonConfig(config);

        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(mediaTypes);

        converters.add(converter);
    }
}

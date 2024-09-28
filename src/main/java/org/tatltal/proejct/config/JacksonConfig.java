package org.tatltal.proejct.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {
    @Bean
    public JsonMapper objectMapper() {
        return JsonMapper.builder()
                .addModules(new ParameterNamesModule(), new Jdk8Module(), new JavaTimeModule())
                // 모르는 property 를 역직렬화 할 때 오류없이 무시하게 한다.
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 모르는 ENUM 값을 역직렬화 할 때 null로 취급하게 한다.
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                // 시간 관련 객체(LocalDateTime, java.util.Date)를 직렬화 할 때 timestamp 숫자값이 아닌 포맷팅 문자열로 한다.
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                // 숫자를 문자로 직렬화하기, BigDecimal 보호?
                .defaultTimeZone(TimeZone.getDefault())
                .defaultLocale(Locale.getDefault())
                .addModule(new SimpleModule().addDeserializer(ObjectId.class, objectIdJsonDeserializer()).addSerializer(ObjectId.class, objectIdJsonSerializer()))
                .build();
    }

    @Bean
    public JsonDeserializer<ObjectId> objectIdJsonDeserializer() {
        return new JsonDeserializer<>() {
            @Override
            public ObjectId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return new ObjectId(p.getText());
            }
        };
    }

    @Bean
    public JsonSerializer<ObjectId> objectIdJsonSerializer() {
        return new JsonSerializer<>() {
            @Override
            public void serialize(ObjectId value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.toString());
            }
        };
    }
}

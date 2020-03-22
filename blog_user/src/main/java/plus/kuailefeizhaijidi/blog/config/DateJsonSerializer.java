package plus.kuailefeizhaijidi.blog.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * <p>
 * Jackson 实体的序列化与反序列化类
 * </p>
 *
 * @author dl
 * @since 2020-03-11
 */
@JsonComponent
public class DateJsonSerializer {

    public static class Serializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        }
    }

//    public static class Deserializer extends JsonDeserializer<LocalDateTime> {
//        @Override
//        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//            return null;
//        }
//    }
}

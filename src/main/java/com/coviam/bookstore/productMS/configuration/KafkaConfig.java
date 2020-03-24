package com.coviam.bookstore.productMS.configuration;
import com.coviam.bookstore.productMS.dto.SearchDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig
{
    //    @Value(value = "${kafka.bootstrapAddress}")
//    private String bootstrapAddress;
    @Bean
    public ProducerFactory producerFactory()
    {
        Map<String, Object> config= new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate()
    {
        return new KafkaTemplate<String, String>(producerFactory());
    }
}

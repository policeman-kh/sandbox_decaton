package sandbox;

import static sandbox.Constants.BOOTSTRAP_SERVER;
import static sandbox.Constants.TOPIC_NAME;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.decaton.client.DecatonClient;
import com.linecorp.decaton.protobuf.ProtocolBuffersSerializer;

import sandbox.protocol.TaskOuterClass.Task;

@Configuration
public class AppConfiguration {
    @Bean
    public DecatonClient<Task> decatonClient() {
        final Properties config = new Properties();
        config.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "sandbox-client");
        config.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        return DecatonClient.producing("sandbox-topic",
                                       new ProtocolBuffersSerializer<Task>())
                            .applicationId(TOPIC_NAME)
                            .instanceId("localhost")
                            .producerConfig(config)
                            .build();
    }
}

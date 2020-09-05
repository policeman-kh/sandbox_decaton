package sandbox.subscriber;

import static com.linecorp.decaton.processor.ProcessorProperties.CONFIG_PROCESSING_RATE;
import static sandbox.Constants.BOOTSTRAP_SERVER;
import static sandbox.Constants.TOPIC_NAME;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.stereotype.Component;

import com.linecorp.decaton.processor.ProcessorsBuilder;
import com.linecorp.decaton.processor.Property;
import com.linecorp.decaton.processor.StaticPropertySupplier;
import com.linecorp.decaton.processor.runtime.ProcessorSubscription;
import com.linecorp.decaton.processor.runtime.SubscriptionBuilder;
import com.linecorp.decaton.protobuf.ProtocolBuffersDeserializer;

import sandbox.protocol.TaskOuterClass.Task;

@Component
public class Subscriber {
    public Subscriber() {
        try {
            postConstruct();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postConstruct() throws Exception {
        final Properties config = new Properties();
        config.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "sandbox-processor");
        config.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        config.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "sandbox-processor");

        final ProcessorSubscription subscription =
                SubscriptionBuilder
                        .newBuilder("sandbox-processor")
                        .processorsBuilder(
                                ProcessorsBuilder.consuming(
                                        TOPIC_NAME,
                                        new ProtocolBuffersDeserializer<>(Task.parser()))
                                                 .thenProcess(new TaskProcessor()))
                        .properties(StaticPropertySupplier.of(
                                // Definition for rate limiting.
                                Property.ofStatic(CONFIG_PROCESSING_RATE, 1L)))
                        .consumerConfig(config)
                        .buildAndStart();
    }
}

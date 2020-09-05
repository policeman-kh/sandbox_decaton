package sandbox.producer;

import org.springframework.stereotype.Service;

import com.linecorp.decaton.client.DecatonClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sandbox.protocol.TaskOuterClass.Task;

@AllArgsConstructor
@Service
public class ProducerService {
    private final DecatonClient<Task> decatonClient;

    public void sendEvent(Task event) {
        Mono.fromFuture(decatonClient.put("key", event))
            .subscribeOn(Schedulers.newSingle("sub"))
            .subscribe(str -> System.out.println(str));
    }
}

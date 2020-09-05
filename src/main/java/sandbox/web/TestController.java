package sandbox.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sandbox.producer.ProducerService;
import sandbox.protocol.TaskOuterClass.Task;

@AllArgsConstructor
@RestController
public class TestController {
    private final ProducerService producerService;

    @GetMapping
    public void test() {
        producerService.sendEvent(Task.newBuilder().setKey("key").setValue("value").build());
    }
}

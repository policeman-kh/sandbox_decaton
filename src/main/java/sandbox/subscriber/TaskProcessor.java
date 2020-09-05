package sandbox.subscriber;

import java.util.concurrent.atomic.AtomicInteger;

import com.linecorp.decaton.processor.DecatonProcessor;
import com.linecorp.decaton.processor.ProcessingContext;

import lombok.extern.slf4j.Slf4j;
import sandbox.protocol.TaskOuterClass.Task;

@Slf4j
public class TaskProcessor implements DecatonProcessor<Task> {
    private final AtomicInteger i = new AtomicInteger(0);

    @Override
    public void process(ProcessingContext<Task> context, Task task) throws InterruptedException {
        log.info("consuming task. i={}, task={}", i.incrementAndGet(), task);
        Thread.sleep(100L);
    }
}

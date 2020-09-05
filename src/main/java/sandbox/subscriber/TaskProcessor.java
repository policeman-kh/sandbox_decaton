package sandbox.subscriber;

import com.linecorp.decaton.processor.DecatonProcessor;
import com.linecorp.decaton.processor.ProcessingContext;

import lombok.extern.slf4j.Slf4j;
import sandbox.protocol.TaskOuterClass.Task;

//@Slf4j
public class TaskProcessor implements DecatonProcessor<Task> {
    @Override
    public void process(ProcessingContext<Task> context, Task task) throws InterruptedException {
        System.out.println("consuming task. task=" + task);
    }
}

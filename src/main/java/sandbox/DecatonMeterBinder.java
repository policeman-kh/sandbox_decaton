package sandbox;

import org.springframework.stereotype.Component;

import com.linecorp.decaton.processor.metrics.Metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

@Component
public class DecatonMeterBinder implements MeterBinder {
    @Override
    public void bindTo(MeterRegistry registry) {
        // To setup decaton metrics.
        Metrics.register(registry);
    }
}

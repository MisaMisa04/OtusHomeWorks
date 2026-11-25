package ru.koshkin.dataprocessor;

import ru.koshkin.model.Measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        // группирует выходящий список по name, при этом суммирует поля value
        try {
            final Map<String, Double> result = new HashMap<>();
            data.forEach((measurement -> {
                result.compute(measurement.name(), (key, val) -> val == null ? measurement.value() : val + measurement.value());
            }));
            return result;
        } catch (Exception e) {
            throw new FileProcessException("Some error appeared: " + e);
        }
    }
}

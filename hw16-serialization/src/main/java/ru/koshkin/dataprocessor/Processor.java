package ru.koshkin.dataprocessor;

import java.util.List;
import java.util.Map;
import ru.koshkin.model.Measurement;

public interface Processor {

    Map<String, Double> process(List<Measurement> data);
}

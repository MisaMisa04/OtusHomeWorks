package ru.koshkin.dataprocessor;

import java.util.List;
import ru.koshkin.model.Measurement;

public interface Loader {

    List<Measurement> load();
}

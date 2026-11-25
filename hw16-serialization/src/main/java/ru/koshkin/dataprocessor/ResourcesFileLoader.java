package ru.koshkin.dataprocessor;

import ru.koshkin.model.Measurement;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;
    private static final ObjectMapper mapper = JsonMapper.builder().build();

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        final URL url = this.getClass().getClassLoader().getResource(this.fileName);
        assert url != null;
        final File file = new File(url.getFile());
        Measurement[] measurement = mapper.readValue(file, Measurement[].class);
        return Arrays.asList(measurement);
    }
}

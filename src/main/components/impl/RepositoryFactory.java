package components.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import components.Repository;

/**
 * Singleton factory class with methods to create instances of type
 * {@link Repository} initialized with objects of type {@code <T>}
 * retrieved from a JSON file.
 */
class RepositoryFactory {

    /*
     * {@code Jackson's} {@link ObjectMapper}.
     */
    private final ObjectMapper jsonObjectMapper;

    /**
     * Constructor of the {@link RepositoryFactory} with injected reference.
     * @param jsonObjectMapper injected reference to {@code jackson's} {@link ObjectMapper}.
     */
    RepositoryFactory(ObjectMapper jsonObjectMapper) {
        this.jsonObjectMapper = jsonObjectMapper;
    }

    /**
     * Create {@link Repository} of objects of type {@code <T>} from JSON file.
     * @param <T> type of objects stored in the {@link Repository}.
     * @param <ID> type of the {@code <ID>} used to index objects in the {@link Repository}.
     * @param jsonFileName name of the JSON file from which records of objects are read.
     * @param idMapper function to retrieve the {@code id} from the object of type {@code <T>}.
     * @param objMapper function to create an object of type {@code <T>} from a key-value map
     * created from a {@code JSON} record.
     * @return {@link Repository} instance filled with objects of type {@code <T>}.
     */
    <T, ID> Repository<T, ID> createFromJsonFile(
        String jsonFileName,
        Function<T, ID> idMapper,
        Function<Map<String, Object>, Optional<T>> objMapper
    ) {
        var repository = new RepositoryImpl<T, ID>(idMapper);
        // 
        JsonNodeStream(jsonFileName)
            // 
            // map attributes of json object to key-value map
            .map(json -> jsonObjectMapper.convertValue(json, new TypeReference<Map<String, Object>>(){}))
            // 
            // convert attribute key-value map to Optional<T> object of type <T>
            .map(kvmap -> objMapper.apply(kvmap))
            .mapMulti(Optional<T>::ifPresent)
            // 
            // .flatMap(Optional::stream)
            // .flatMap(o -> o.isPresent()? Stream.of(o.get()) : Stream.empty())
            // .filter(Optional::isPresent)
            // .map(Optional::get)
            // 
            .forEach(obj -> {
                var id = idMapper.apply(obj);
                // 
                var saved = repository.save(obj);
                // 
                if(saved != null && saved != obj) {
                    System.out.println(String.format("object of same id: '%s' already existed in repository (replaced by saved object)", id));
                }
            });
        // 
        return repository;
    }

    /**
     * Open {@link Stream} of {@link JsonNode}s from file.
     * @param fileName name of JSON-file to open.
     * @return {@link Stream} of opened JSON-file.
     */
    private Stream<JsonNode> JsonNodeStream(String fileName) {
        try(InputStream is = Optional.ofNullable(
                getClass().getClassLoader().getResourceAsStream(fileName))
                    // .orElseThrow(() -> new IOException(String.format("File not found: %s", fileName)))
                    .orElseGet(() -> fallbackResourceStream(fileName))
        ) {
            if(is==null) {
                throw new IOException(String.format("File not found: %s", fileName));
            }
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                jsonObjectMapper.readTree(is).elements(), 0), false);
        // 
        } catch(IOException /*| JsonProcessingException*/ e) {
            System.err.println(String.format("---> %s", e.getMessage()));
        }
        return Stream.empty();
    }

    /**
     * Attempt to open {@link InputStream} from alternative paths,
     * as needed for running unit tests.
     * @param fileName name of JSON-file to open.
     * @return {@link InputStream} of opened JSON-file
     */
    private InputStream fallbackResourceStream(String fileName) {
        var candidatePaths = List.of(
            Paths.get("src", "resources", fileName),
            Paths.get("src", "main", "resources", fileName)
        );
        for (Path path : candidatePaths) {
            try {
                if (Files.exists(path)) {
                    return Files.newInputStream(path);
                }
            } catch (IOException e) {
                System.err.println(String.format("---> Failed opening fallback resource %s: %s", path, e.getMessage()));
            }
        }
        return null;
    }
}

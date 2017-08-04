package softuni.course.products.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("JsonParser")
public class JsonParser implements Serializer {

    private Gson gson;


    private final FileParser fileParser;

    @Autowired
    public JsonParser(FileParser fileParser) {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        this.fileParser = fileParser;
    }

//    public <T> T importJson(Class<T> clazz, String fileName) throws IOException {
//        String file = this.fileParser.readFile(fileName);
//        T object = this.gson.fromJson(file, clazz);
//        return object;
//    }
//
//    public <T> List<T> importJsonList(Class<T> clazz, String fileName) throws IOException {
//        Type type = new TypeToken<ArrayList<T>>() {}.getType();
//        String file = this.fileParser.readFile(fileName);
//        return this.gson.fromJson(file, type);
//    }
//
//    public <T> void exportJson(T object, String fileName) throws IOException {
//        String content = this.gson.toJson(object);
//        this.fileParser.writeFile(fileName, content);
//    }

    @Override
    public <T> void serialize(T t, String fileName) {
        String json = gson.toJson(t);
        try {
            fileParser.writeFile(fileName, json);
        } catch (IOException e) {
            throw new SerializeException(json + " was not serialized to " + fileName, e);
        }
    }

    @Override
    public <T> T deserialize(Class<T> tClass, String fileName) {
        try {
            String file = this.fileParser.readFile(fileName);
            T object = this.gson.fromJson(file, tClass);
            return object;
        } catch (IOException e) {
            //log here
            throw new SerializeException(fileName + " can not be read", e);
        }
    }
}

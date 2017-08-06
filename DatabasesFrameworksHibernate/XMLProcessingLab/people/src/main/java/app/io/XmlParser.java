package app.io;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component
public class XmlParser implements Serializer {

    @Override
    public <T> void serialize(T t, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            String path = System.getProperty("user.dir") + File.separator + fileName;
            File f = new File(path);
            if(!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller
                    .setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            try( OutputStream os = new FileOutputStream(fileName);
                 BufferedWriter bfrw = new BufferedWriter(new OutputStreamWriter(os))) {
                marshaller.marshal(t, bfrw);
            }
        } catch (JAXBException e) {
            throw new SerializeException("Could not serialize " + t + " to XML" , e);
        } catch (IOException e) {
            throw new SerializeException("Could not serialize " + t + " unable to write to " + fileName , e);
        }
    }

    @Override
    public <T> T deserialize (Class<T> tClass, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            try( InputStream is = tClass.getResourceAsStream(fileName);
                 BufferedReader bfr = new BufferedReader(new InputStreamReader(is))) {

                T t = (T) unmarshaller.unmarshal(bfr);
                return t;
            }

        } catch (JAXBException e) {
            throw new SerializeException("Could not deserialize to class " + tClass + " to XML" , e);
        } catch (IOException e) {
            throw new SerializeException("Could not deserialize to class" + tClass + " unable to read from " + fileName , e);
        }

    }
}

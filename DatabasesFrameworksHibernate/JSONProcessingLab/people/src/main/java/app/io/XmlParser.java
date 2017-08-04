package app.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("XmlParser")
public class XmlParser implements Serializer{

    @Autowired
    private FileParser fileParser;

    //public <T> void writeXML(T object, String fileName) throws JAXBException, IOException {
    //    String path = System.getProperty("user.dir") + File.separator + fileName;
    //    File f = new File(path);
    //    if(!f.exists()) {
    //        f.getParentFile().mkdirs();
    //        f.createNewFile();
    //    }
//
    //    JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
    //    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    //    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    //    OutputStream outputStream = new FileOutputStream(fileName);
    //    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    //    jaxbMarshaller.marshal(object, bufferedWriter);
    //}

    @Override
    public <T> void serialize(T t, String fileName) {

    }

    @Override
    public <T> T deserialize(Class<T> tClass, String fileName) {
        return null;
    }
}

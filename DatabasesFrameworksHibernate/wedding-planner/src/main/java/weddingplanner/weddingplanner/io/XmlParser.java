package weddingplanner.weddingplanner.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

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
                marshaller.marshal(t, os);
            }
        } catch (JAXBException e) {
            throw new SerializeException("Could not serialize " + t + " to XML" , e);
        } catch (IOException e) {
            throw new SerializeException("Could not serialize " + t + " unable to write to " + fileName , e);
        }
    }

    @Override
    public <T> T deserialize(Class<T> tClass, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            try( InputStream is = tClass.getResourceAsStream(fileName);
                 BufferedReader bfr = new BufferedReader(new InputStreamReader(is))) {
//                StringBuilder sb = new StringBuilder();
//                String line;
//                while ((line = bfr.readLine()) != null) {
//                    sb.append(line).append(System.lineSeparator());
//                }
//
//                String currentXml = sb.toString();
//                File file = new File(System.getProperty("user.dir") + "/src/main/resources" + fileName);
//                if(currentXml.indexOf("<?xml") > 0) {
//                    String validXml = currentXml.substring(currentXml.indexOf("<?xml"));
//                    String currentFileName = fileName.replace(".xml", "Valid.xml");
//                    this.fileParser.writeFile("E:\\Programming\\JavaCoursesSoftUni\\DatabasesFrameworksHibernate\\XMLProcessingExercises\\car_sales\\src\\main\\resources\\in\\carsValid.xml", validXml);
//                    file = new File(System.getProperty("user.dir") + "/src/main/resources" + currentFileName);
//                }
                //T t = (T) unmarshaller.unmarshal(is);
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

package app.terminal;

import app.domain.dto.DTOConvertUtil;
import app.domain.dto.json.PersonJsonDto;
import app.domain.model.Person;
import app.io.Serializer;
import app.serviceImpl.PersonServiceImpl;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private static final String  PERSON_INPUT_JSON = "/files/input/json/person.json";
    private static final String  PEOPLE_INPUT_JSON = "/files/input/json/persons.json";
    private static final String  PEOPLE_OUTPUT_JSON = "src/main/resources/files/output/json/people.json";

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    @Qualifier("JsonParser")
    private Serializer serializer;

    @Override
    public void run(String... strings) throws Exception {
  //      this.importPerson();
       // this.importPeople();
        exportPersonByCountry();
//        this.importJsons();
//        this.exportJson();
//        this.exportJsons();


    }

    private void exportPersonByCountry() {
        List<PersonJsonDto> bulgarians = this.personService.findByCountry("Bulgaria");
        serializer.serialize(bulgarians, PEOPLE_OUTPUT_JSON);
    }

    private void importPeople() {
        PersonJsonDto[] personJsonDtos = serializer.deserialize(PersonJsonDto[].class, PEOPLE_INPUT_JSON);
        for (PersonJsonDto personJsonDto : personJsonDtos) {
            
            this.savePerson(personJsonDto);
        }
    }

    private void importPerson() {
        PersonJsonDto personJsonDto = serializer.deserialize(PersonJsonDto.class, PERSON_INPUT_JSON);
        this.savePerson(personJsonDto);
    }

    private void savePerson(PersonJsonDto personJsonDto) {
        Person person = DTOConvertUtil.convert(personJsonDto, Person.class);
        this.personService.create(person);
    }


//
//    private void exportJson() throws IOException {
//        Person person = this.personService.findById(1);
//        PersonJsonDto personJsonDto = DTOConvertUtil.convert(person, PersonJsonDto.class);
//
//        long startTime = System.currentTimeMillis();
//        this.jsonParser.exportJson(personJsonDto, "src/main/resources/files/output/json/person.json");
//        long endTime = System.currentTimeMillis();
//        double time = (endTime - startTime);
//        System.out.println("JSON " + time);
//
//    }
//
//    private void exportJsons() {
//        List<PersonJsonDto> personJsonDto = this.personService.findByCountry("Bulgaria");
//        try {
//            this.jsonParser.exportJson(personJsonDto, "src/main/resources/files/output/json/persons.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void importJson() throws IOException {
//        PersonJsonDto personJsonDto = this.jsonParser.importJson(PersonJsonDto.class, "/files/input/json/person.json");
//        storeDto(personJsonDto);
//    }
//
//    private void storeDto(PersonJsonDto personJsonDto) {
//        Person person = DTOConvertUtil.convert(personJsonDto, Person.class);
//
//        for (Iterator<PhoneNumber> i = person.getPhoneNumbers().iterator(); i.hasNext();) {
//            PhoneNumber phoneNumber = i.next();
//            try {
//                //phoneNumber.setPerson(person);
//                phoneService.create(phoneNumber);
//            } catch (ConstraintViolationException e) {
//                i.remove();
//                System.out.println("Invalid number detected. Not stored in DB : " + phoneNumber.getNumber());
//            }
//        }
//
//        personService.create(person);
//
//
//
//    }
//
//    private void importJsons() throws IOException {
//        PersonJsonDto[] personJsonDtos = this.jsonParser.importJson(PersonJsonDto[].class, "/files/input/json/persons.json");
//
//        for (PersonJsonDto personJsonDto : personJsonDtos) {
//            storeDto(personJsonDto);
//        }
//    }
}

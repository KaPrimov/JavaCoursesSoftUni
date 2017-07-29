package app.console;

import app.entities.Course;
import app.entities.Homework;
import app.entities.Resource;
import app.entities.Student;
import app.enums.ContentType;
import app.services.api.CourseService;
import app.services.api.HomeworkService;
import app.services.api.ResourceService;
import app.services.api.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private CourseService<Course, Long> courseService;

    private HomeworkService<Homework, Long> homeworkService;

    private ResourceService<Resource, Long> resourceService;

    private StudentService<Student, Long> studentService;

    @Autowired
    public ConsoleRunner(CourseService<Course, Long> courseService, HomeworkService<Homework, Long> homeworkService, ResourceService<Resource, Long> resourceService, StudentService<Student, Long> studentService) {
        this.courseService = courseService;
        this.homeworkService = homeworkService;
        this.resourceService = resourceService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... strings) throws Exception {
        //seedData();

        //NOTE Because of the random generator you might not get results for some of the queries

        //1st Task:
        //studentService.findAllStudentsAndTheirHomeworks().stream()
        //        .forEach(s -> System.out.printf("%s %s %s%n", s[0], s[1], s[2]));

        //2nd Task:
        //courseService.findAllCoursesAndResources().forEach(c -> System.out.println(String.join(", ",
        //     Arrays.stream(c)
        //            .filter(Objects::nonNull)
        //            .map(Object::toString)
        //             .collect(Collectors.toList()))));

        //3rd Task: The query returns the courses with more than 3 resources, because of the lack of data
        //this.courseService.findAllCoursesWithMoreThan3Resources()
        //        .forEach(System.out::println);

        //4th Task
        //this.courseService.findAllActiveCourses()
        //        .forEach(System.out::println);

        // 5th Task
        //this.studentService.studentsInfo()
        //        .forEach(s -> System.out.printf("%s %s %s %s%n", s[0], s[1], s[2], s[3]));
    }

    private void seedData() throws ParseException {
        String[] names = {"Pesho", "Gosho", "Ivan", "Maria", "Petran", "Stefan"};
        String[] courses = {"DB", "OOP", "Spring", "node.js"};
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date[] endDate = {formatter.parse("20/01/2016"), formatter.parse("18/01/2014"), formatter.parse("15/05/2013")};
        Date[] startDate = {formatter.parse("20/01/2012"), formatter.parse("18/01/2010"), formatter.parse("15/05/2011")};
        ContentType[] types = ContentType.values();

        List<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Student student = new Student(names[random.nextInt(names.length)], "213124214" + i, startDate[random.nextInt(startDate.length)],endDate[random.nextInt(startDate.length)]);
            studentService.save(student);
            students.add(student);
        }

        List<Course> savedCourses = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Course course = new Course(courses[random.nextInt(courses.length)], startDate[random.nextInt(startDate.length)],endDate[random.nextInt(startDate.length)], new BigDecimal("200").multiply(new BigDecimal("" + i)));
            course.setStudents(new HashSet<>(students));
            courseService.save(course);
            savedCourses.add(course);
        }

        for (int i = 0; i < 5; i++) {
            Homework homework = new Homework(names[random.nextInt(names.length)] + courses[random.nextInt(courses.length)],startDate[random.nextInt(startDate.length)], types[random.nextInt(types.length)]);
            homework.setCourse(savedCourses.get(i));
            homework.setStudent(students.get(i));
            homeworkService.save(homework);
        }

        for (int i = 0; i < 5; i++) {
            Resource resource = new Resource(courses[random.nextInt(courses.length)],courses[random.nextInt(courses.length)], "some", savedCourses.get(i));
            resourceService.save(resource);
        }
    }
}

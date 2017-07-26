package app.entities;

import app.enums.ContentType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "homeworks")
public class Homework {

    private long id;
    private String content;
    private Date submissionDate;
    private ContentType contentType;
    private Student student;
    private Course course;

    public Homework(String content, Date submissionDate, ContentType contentType) {
        this.content = content;
        this.submissionDate = submissionDate;
        this.contentType = contentType;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "content", columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "submission_date", columnDefinition = "DATE")
    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Column(name = "content_type")
    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}

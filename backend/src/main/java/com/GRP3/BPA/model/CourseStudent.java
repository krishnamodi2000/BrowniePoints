package com.GRP3.BPA.model;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="course_student")
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudent {

    /**
     * The ID of the course-student relationship.
     */
    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * The student enrolled in the course.
     */
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="banner_id")
    private Student student;

    /**
     * The course the student is enrolled in.
     */
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    /**
     * The number of points the student has earned in the course.
     */
    @Getter @Setter
    @Column(name = "points")
    private Integer points;
}


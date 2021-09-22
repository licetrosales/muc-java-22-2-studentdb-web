package de.neuefische.spring_request_params.service;

import de.neuefische.spring_request_params.model.Student;
import de.neuefische.spring_request_params.repo.StudentRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Test
    public void testSearchStudent() {
        //GIVEN
        StudentRepo studentRepo = mock(StudentRepo.class);
        when(studentRepo.getStudents()).thenReturn(
                List.of(
                        new Student("1", "Paul"),
                        new Student("2", "Maria"),
                        new Student("3", "Hannah"),
                        new Student("4", "Marianne")
                )
        );
        StudentService studentService = new StudentService(studentRepo);

        //WHEN
        List<Student> actual = studentService.search("Mari");

        //THEN
        assertThat(actual, containsInAnyOrder(
                new Student("2", "Maria"),
                new Student("4", "Marianne")
        ));
    }

    @Test
    public void testAddStudent(){
        //GIVEN

        StudentRepo studentRepo = mock(StudentRepo.class);
        Student studentToAdd = new Student("5", "Hans");
        when(studentRepo.add(studentToAdd)).thenReturn(studentToAdd);
        StudentService studentService = new StudentService(studentRepo);

        //WHEN
        Student actual = studentService.addStudent(studentToAdd);

        //THEN
        assertThat(actual, is(studentToAdd));
        verify(studentRepo).add(studentToAdd);
    }

}
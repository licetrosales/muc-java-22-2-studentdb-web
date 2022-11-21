package de.neuefische.spring_request_params.service;

import de.neuefische.spring_request_params.model.Student;
import de.neuefische.spring_request_params.repo.StudentRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    private final StudentRepo studentRepo = mock(StudentRepo.class);
    private final IdService idService = mock(IdService.class);

    @Test
    public void testListStudents() {
        //GIVEN
        when(studentRepo.getStudents()).thenReturn(
                List.of(
                        new Student("1", "Paul"),
                        new Student("2", "Maria"),
                        new Student("3", "Hannah"),
                        new Student("4", "Marianne")
                )
        );
        StudentService studentService = new StudentService(studentRepo, idService);

        //WHEN
        List<Student> actual = studentService.list();

        //THEN
        assertThat(actual, containsInAnyOrder(
                new Student("1", "Paul"),
                new Student("2", "Maria"),
                new Student("3", "Hannah"),
                new Student("4", "Marianne")
        ));
    }

    @Test
    public void testSearchStudent() {
        //GIVEN
        when(studentRepo.getStudents()).thenReturn(
                List.of(
                        new Student("1", "Paul"),
                        new Student("2", "Maria"),
                        new Student("3", "Hannah"),
                        new Student("4", "Marianne")
                )
        );
        StudentService studentService = new StudentService(studentRepo, idService);

        //WHEN
        List<Student> actual = studentService.search("Mari");

        //THEN
        assertThat(actual, containsInAnyOrder(
                new Student("2", "Maria"),
                new Student("4", "Marianne")
        ));
    }

    @Test
    public void testAddStudent() {
        //GIVEN
        Student studentToAdd = new Student("5", "Hans");
        when(studentRepo.add(studentToAdd)).thenReturn(studentToAdd);
        StudentService studentService = new StudentService(studentRepo, idService);

        //WHEN
        Student actual = studentService.addStudent(studentToAdd);

        //THEN
        assertThat(actual, is(studentToAdd));
        verify(studentRepo).add(studentToAdd);
    }

    @Test
    public void testFindById() {
        //GIVEN
        when(studentRepo.findById("2")).thenReturn(Optional.of(new Student("2", "Maria")));
        StudentService studentService = new StudentService(studentRepo, idService);

        //WHEN
        Student actual = studentService.findById("2");

        //THEN
        assertThat(actual, is(new Student("2", "Maria")));
    }

    @Test
    public void testFindByIdWithNotExistingId() {
        //GIVEN
        when(studentRepo.findById("9")).thenReturn(Optional.empty());
        StudentService studentService = new StudentService(studentRepo, idService);

        //WHEN
        try {
            studentService.findById("2");
            fail();
        } catch (IllegalArgumentException e) {
            //THEN
            assertEquals(e.getMessage(), "Id not found!");
        }
    }

    @Test
    public void testDelete() {
        //GIVEN
        when(studentRepo.findById("2")).thenReturn(Optional.of(new Student("2", "Maria")));
        StudentService studentService = new StudentService(studentRepo, idService);

        //WHEN
        studentService.delete("2");

        //THEN
        verify(studentRepo).delete(new Student("2", "Maria"));

    }

    @Test
    public void testAddStudentWithRandomId() {
        //GIVEN
        Student studentToAdd = new Student(null, "Hans");
        Student addedStudent = new Student("7abc", "Hans");

        when(idService.generateId()).thenReturn("7abc");
        when(studentRepo.add(addedStudent)).thenReturn(addedStudent);
        StudentService studentService = new StudentService(studentRepo, idService);

        //WHEN
        Student actual = studentService.addStudent(studentToAdd);

        //THEN
        assertThat(actual, is(studentToAdd));
        verify(studentRepo).add(studentToAdd);
    }
}

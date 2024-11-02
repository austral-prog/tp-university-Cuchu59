package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.html.CSS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UniversityTest {

    Student test_student = new Student("Pedro Juan","pedrito@gmail.com");
    Student test_student_2 = new Student("Julian Martin","un_capo200@gmail.compare");

    Evaluation eva1 = new Evaluation("Examen","ORAL_EXAM");
    Evaluation eva2 = new Evaluation("TP2","PRACTICAL_WORK");

    Course test_course = new Course("Fisica");
    Course test_course_2 = new Course("Quimica");
    

    @Test
    void studentTesting() {
        assertEquals("Pedro Juan", test_student.getName());
        assertEquals("pedrito@gmail.com", test_student.getEmail());
        
        assertEquals(false, test_student.equals(test_student_2));
        assertEquals(true, test_student.equals(test_student));
        
    }

    @Test
    void evaluationTesting() {
    
        assertEquals("Examen",eva1.getName()); 
        assertEquals("ORAL_EXAM",eva1.getType()); 
        Map<String, Float[]> test_scoreMap = new HashMap<>();
        
        for(float i = 0; i <= 10; ++i){
            eva1.addScores(test_student, i);
            
        }
        
        test_student.AddEvaluation(eva1);
        test_student.AddEvaluation(eva2);
        
        assertTrue(test_student.hasEvaluation(eva1.getName()) && test_student.hasEvaluation(eva2.getName()));
        test_student.AddEvaluation(eva2);
        
        
        Set<Evaluation> evas_test = new TreeSet<>(Comparator.comparing(Evaluation::getName));;
        evas_test.add(eva1);
        evas_test.add(eva2);
        
        assertEquals(evas_test, test_student.getEvaluations());
        

    }

    @Test
    void courseTesting() {        
        assertEquals("Fisica", test_course.getName());
        assertFalse(test_course.equals(test_course_2));
        
        test_course.AddStudent(null);
        test_course.AddStudent(test_student);
        assertTrue(test_course.hasStudent(test_student));
        assertFalse(test_course.hasStudent(test_student_2));
        
        assertFalse(test_course.hasStudent(null));

        Set<Student> students_test = new HashSet<>();
        students_test.add(test_student);
        assertTrue(test_course.getStudents().equals(students_test)); 

    }

    @Test
    void University() {
        assertTrue(University.hasStudent("Pedro Juan"));
        assertFalse(University.hasStudent(""));
        assertEquals(test_student, University.getStudent("Pedro Juan"));
        assertEquals(test_course, University.getCourse("Fisica"));
        assertEquals(null, University.getCourse(""));
        assertFalse(University.hasCourse(""));
        assertTrue(University.hasCourse("Fisica"));
        
        University.AddStudentTo(test_student,test_course);
        University.AddStudentTo(test_student,test_course_2);
        University.AddStudentTo(test_student,test_course_2);
        
        Set<Course> expected_courses = new TreeSet<>(Comparator.comparing(Course::getName));;
        expected_courses.add(test_course); expected_courses.add(test_course_2);
        
        assertEquals(expected_courses, University.getStudentCourses(test_student));
        assertEquals(Set.of(), University.getStudentCourses(test_student_2));
    }

    @Test
    void testCourseStudentCreation() {
        // Simulamos datos de entrada en CSV_Treat.currentCSV_data
        List<String[]> testData = new ArrayList<>();
        testData.add(new String[]{"1", "Mathematics", "John Doe", "john.doe@example.com"});
        testData.add(new String[]{"2", "Physics", "Jane Smith", "jane.smith@example.com"});
        testData.add(new String[]{"3", "Mathematics", "Alice Brown", "alice.brown@example.com"});
        testData.add(new String[]{"4", "Chemistry", "Alice Brown", "alice.brown@example.com"});

        CSV_Treat.currentCSV_data = testData;
        
        // Llama al método que queremos probar
        Creator.create_Courses_Students();

        // Verifica que los cursos han sido creados
        assertTrue(University.hasCourse("Mathematics"));
        assertTrue(University.hasCourse("Physics"));

        // Verifica que los estudiantes han sido creados
        assertTrue(University.hasStudent("John Doe"));
        assertTrue(University.hasStudent("Jane Smith"));
        assertTrue(University.hasStudent("Alice Brown"));

        // Verifica que los estudiantes están asociados correctamente con los cursos
        Course mathCourse = University.getCourse("Mathematics");
        Course physicsCourse = University.getCourse("Physics");

        assertTrue(mathCourse.getStudents().contains(University.getStudent("John Doe")));
        assertTrue(mathCourse.getStudents().contains(University.getStudent("Alice Brown")));
        assertTrue(physicsCourse.getStudents().contains(University.getStudent("Jane Smith")));
    }

    @Test
    void testCreateEvaluations() {
        Course course = new Course("Math");
        Course course_2 = new Course("Language");

        // Configura datos en CSV_Treat.currentCSV_data
        CSV_Treat.currentCSV_data = new ArrayList<>();
        CSV_Treat.currentCSV_data.add(new String[]{"Alice", "Math", "Quiz", "Quiz1", "Ej1", "85"});
        CSV_Treat.currentCSV_data.add(new String[]{"Alice", "Math", "Quiz", "Quiz1", "Ej2", "50"});
        CSV_Treat.currentCSV_data.add(new String[]{"Bob", "Math", "Exam", "Midterm", "Ej1", "90"});
        CSV_Treat.currentCSV_data.add(new String[]{"Bob", "Chemistry", "Exam", "Midterm", "Ej3", "70"});
        CSV_Treat.currentCSV_data.add(new String[]{"None", "Math", "Exam", "Midterm", "Ej3", "70"});
        
        // Crea estudiantes en la clase University
        Student alice = new Student("Alice","");
        Student bob = new Student("Bob","");
        Student none = new Student("None None", "");
        
        // Agrega los estudiantes a la Universidad
        University.AddStudentTo(alice, course);
        University.AddStudentTo(bob, course);
        University.AddStudentTo(none, course);
        
        // Ejecuta el método a probar
        Creator.create_evaluationsPerCourse();

        // Verifica que Alice tenga la evaluación "Quiz1"
        
        assertTrue(alice.hasEvaluation("Quiz1"));
        Evaluation aliceEvaluation = alice.getEvaluation("Quiz1");
        assertEquals("Quiz", aliceEvaluation.getType());
        
        // Verifica que Bob tenga la evaluación "Midterm"
        assertTrue(bob.hasEvaluation("Midterm"));
        Evaluation bobEvaluation = bob.getEvaluation("Midterm");
        assertEquals("Exam", bobEvaluation.getType());
        
        // Verifica que Bob tenga la evaluación "Midterm"
        Student non = new Student("non", "null");
        assertFalse(non.hasEvaluation("Midterm"));
        Evaluation nonEvaluation = non.getEvaluation("Midterm");
        
        
    }

}

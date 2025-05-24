import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeEvaluationTest {

    @Test
    void testPath1_invalidGrade() {
        int[] grades = {-1};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    void testPath2_allPassed() {
        int[] grades = {80, 90, 100};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("All students passed.", result);
    }

    @Test
    void testPath3_nonePassed() {
        int[] grades = {10, 20, 30};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("No students passed.", result);
    }

    @Test
    void testPath4_somePassed() {
        int[] grades = {40, 60, 20};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    @Test
    void testPath5_invalidAfterSomeFails() {
        int[] grades = {30, 20, -5};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    void testPath6_somePassedAfterFails() {
        int[] grades = {30, 40, 60};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    @Test
    void testPath7_nonePassedWithLoop() {
        int[] grades = {10, 20, 30, 40};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("No students passed.", result);
    }

    @Test
    void testPath8_somePassedWithLoop() {
        int[] grades = {10, 20, 80};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    @Test
    void testPath9_invalidAfterPasses() {
        int[] grades = {70, 80, 150};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    void testPath10_allPassedMoreGrades() {
        int[] grades = {60, 70, 80, 90};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("All students passed.", result);
    }

    @Test
    void testPath11_nonePassedAfterSomePasses() {
        int[] grades = {60, 30, 10};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    @Test
    void testPath12_somePassedMixed() {
        int[] grades = {50, 49, 51};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    @Test
    void testPath13_invalidAtStart() {
        int[] grades = {200, 80};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    void testPath14_invalidThenAllPassed() {
        int[] grades = {200, 60, 90};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    void testPath15_invalidThenNonePassed() {
        int[] grades = {200, 40, 30};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    void testPath16_invalidThenSomePassed() {
        int[] grades = {200, 40, 60};
        String result = GradeEvaluation.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }
}

package pro.sky.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private static final QuestionService EMPTY_QUESTION_SERVICE = new JavaQuestionService();
    private static final Question TEST_QUESTION = new Question("test", "test");
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
        questionService.add(TEST_QUESTION);
    }

    @Test
    void getCurrentAmountTest() {
        assertEquals(0, new JavaQuestionService().getCurrentAmount());
        assertEquals(1, questionService.getCurrentAmount());
    }

    @Test
    void addTest() {
        Question q = questionService.add("123", "456");
        assertEquals(2, questionService.getCurrentAmount());
        assertEquals(new Question("123", "456"), q);
    }

    @Test
    void addBlankQuestionShouldThrowsException() {
        assertThrows(ResponseStatusException.class, () -> questionService.add("  ", "456"));
    }

    @Test
    void addEmptyAnswerShouldThrowsException() {
        assertThrows(ResponseStatusException.class, () -> questionService.add("test", ""));
    }

    @Test
    void addNullQuestionShouldThrowsException() {
        assertThrows(ResponseStatusException.class, () -> questionService.add(null));
    }

    @Test
    void addExistingQuestionShouldThrowsException() {
        assertThrows(ResponseStatusException.class,
                () -> questionService.add(TEST_QUESTION));
    }


    @Test
    void testRemove() {
        Question q = questionService.remove("test", "test");
        assertEquals(0, questionService.getCurrentAmount());
        assertEquals("test", q.getQuestion());
        assertEquals("test", q.getAnswer());
    }

    @Test
    void removeNonExistentQuestionShouldThrowsException() {
        assertThrows(ResponseStatusException.class,
                () -> questionService.remove("111", "222"));
    }

    @Test
    void removeNullQuestionShouldThrowsException() {
        assertThrows(ResponseStatusException.class, () -> questionService.remove(null));
    }

    @Test
    void getAllTest() {
        Collection<Question> questions = questionService.getAll();
        assertNotNull(questions);
        assertEquals(HashSet.class, questions.getClass());
        assertEquals(1, questions.size());

        assertTrue(EMPTY_QUESTION_SERVICE.getAll().isEmpty());
    }

    @Test
    void getRandomQuestionFromEmptyShouldThrowsException() {
        assertThrows(ResponseStatusException.class,
                EMPTY_QUESTION_SERVICE::getRandomQuestion);
    }

    @Test
    void getRandomQuestionFromOneQuestionShouldReturnTheSameQuestion() {
        Question q = questionService.getRandomQuestion();
        assertEquals(TEST_QUESTION, q);
    }

    @Test
    void getRandomQuestionTest() {
        QuestionService qService = generate(10);
        Question q = qService.getRandomQuestion();
        assertNotNull(q);
        assertTrue(qService.getAll().contains(q));
    }

    private static QuestionService generate(int size) {
        QuestionService qService = new JavaQuestionService();
        for (int i = 0; i < size; i++) {
            qService.add(String.valueOf(i), String.valueOf(i));
        }
        return qService;
    }


}
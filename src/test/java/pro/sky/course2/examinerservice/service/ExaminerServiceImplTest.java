package pro.sky.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private final QuestionService questionService = mock(JavaQuestionService.class);

    private final ExaminerService examinerService = new ExaminerServiceImpl(questionService);

    @Test
    void getQuestionsTest() {
        int questionsAmount = 1;
        when(questionService.getCurrentAmount()).thenReturn(questionsAmount);
        when(questionService.getRandomQuestion())
                .thenReturn(new Question("test", "test"));
        Collection<Question> questions = examinerService.getQuestions(1);
        Assertions.assertEquals(1, questions.size());
    }

}
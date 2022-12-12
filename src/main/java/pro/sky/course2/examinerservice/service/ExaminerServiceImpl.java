package pro.sky.course2.examinerservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getCurrentAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid amount");
        }
        Collection<Question> questions = new HashSet<>(amount);
        while (questions.size() != amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}

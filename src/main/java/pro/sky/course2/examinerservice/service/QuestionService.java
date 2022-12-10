package pro.sky.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(String question);
    Question remove(String question);

    Collection<Question> getAll();
    Question getRandomQuestion();



}

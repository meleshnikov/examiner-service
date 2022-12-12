package pro.sky.course2.examinerservice.service;

import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}

package pro.sky.course2.examinerservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionService implements QuestionService {

    private final Collection<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question q = checkAndCreateQuestion(question, answer);
        return add(q);
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No question!");
        }
        if (!questions.add(question)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The question is already on the list!");
        }
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question q = checkAndCreateQuestion(question, answer);
        return remove(q);
    }

    @Override
    public Question remove(Question question) {
        if (question == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No question!");
        }
        if (!questions.remove(question)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question not found!");
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return this.questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List of questions is empty");
        }
        int size = questions.size();
        int rnd = ThreadLocalRandom.current().nextInt(size);
        int i = 0;
        Question question = null;
        for (Question q : questions) {
            if (i == rnd) {
                question = q;
                break;
            }
            i++;
        }
        return question;
    }

    @Override
    public int getCurrentAmount() {
        return this.questions.size();
    }

    private Question checkAndCreateQuestion(String question, String answer) {
        if (isNotValid(question)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No question!");
        }
        if (isNotValid(answer)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No answer!");
        }
        return new Question(question, answer);
    }

    private boolean isNotValid(String s) {
        return s == null || s.trim().isEmpty();
    }

}

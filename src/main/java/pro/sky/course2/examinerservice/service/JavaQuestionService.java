package pro.sky.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question add(String question) {
        return null;
    }

    @Override
    public Question remove(String question) {
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return this.questions;
    }

    @Override
    public Question getRandomQuestion() {
        int size = questions.size();
        int rnd = ThreadLocalRandom.current().nextInt(size);
        return null;
    }
}

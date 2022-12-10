package pro.sky.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.examinerservice.domain.Question;
import pro.sky.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaController {

    private final QuestionService service;

    public JavaController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    Question addQuestion(@RequestParam("question") String question,
                         @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping
    Collection<Question> getQuestions() {
        return service.getAll();
    }


}

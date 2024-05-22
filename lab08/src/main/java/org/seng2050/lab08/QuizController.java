package org.seng2050.lab08;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizController {
    
    @GetMapping("/quiz")
    public ModelAndView showQuiz() {
        var mav = new ModelAndView("quiz");
        mav.addObject("quiz", new Quiz());
        return mav;
    }

    @PostMapping("/quiz")
    public ModelAndView submitQuiz(
        @RequestParam("answer1") String answer1,
        @RequestParam("answer2") String answer2,
        @RequestParam("answer3") String answer3,
        @RequestParam("answer4") String answer4        
    ) {
        
        Quiz quiz = new Quiz();
        quiz.setAnswer1(answer1);
        quiz.setAnswer2(answer2);
        quiz.setAnswer3(answer3);
        quiz.setAnswer4(answer4);

        var mav = new ModelAndView("quiz");
        mav.addObject("quiz", quiz);
        mav.addObject("submitted", true);
        return mav;
    }
}

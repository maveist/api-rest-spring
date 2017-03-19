package controller;

import model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import repository.SkillRepository;

import javax.validation.Valid;

/**
 * Created by mmaheo on 09/03/2017.
 */
@Controller
@EnableMongoRepositories("repository")
public class SkillController extends WebMvcConfigurerAdapter {

    @Autowired
    private SkillRepository repository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/add/skill")
    public String showForm(Skill skill) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid Skill skill, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        repository.save(new Skill(skill.getName()));

        return "redirect:/results";
    }

}

package controller;

import model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import repository.SkillRepository;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/skills")
    public ModelAndView retrieve() {
        List<Skill> skills = repository.findAll();
        ModelAndView model = new ModelAndView("skills");
        model.addObject("skills", skills);

        return model;
    }

    @GetMapping("/add/skill")
    public String showForm(Skill skill) {
        return "form";
    }

    @PostMapping("/")
    public String checkSkill(@Valid Skill skill, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        repository.save(new Skill(skill.getName()));

        return "redirect:/results";
    }

}
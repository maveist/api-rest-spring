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
@RequestMapping("/skills")
@EnableMongoRepositories("repository")
public class SkillController extends WebMvcConfigurerAdapter {

    @Autowired
    private SkillRepository repository;

    @GetMapping("/")
    public ModelAndView retrieve() {
        List<Skill> skills = repository.findAll();
        ModelAndView model = new ModelAndView("skill/index");
        model.addObject("skills", skills);

        return model;
    }

    @GetMapping("/add")
    public String showForm(Skill skill) {
        return "skill/create";
    }

    @RequestMapping("/{id}")
    public @ResponseBody ModelAndView showDetails(@PathVariable(value="id") String id) {
        Skill skill = repository.findOne(id);
        ModelAndView model = new ModelAndView("skill/details_skills");
        model.addObject("skill", skill);
        model.addObject("ressources", skill.getRessources());
        return model;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/skills";

    }

    @PostMapping("/")
    public String checkSkill(@Valid Skill skill, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "skill/create";
        }

        repository.save(new Skill(skill.getName()));

        return "redirect:/skills";
    }

}

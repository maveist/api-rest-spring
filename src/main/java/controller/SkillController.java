package controller;

import model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("")
    public ModelAndView retrieve() {
        List<Skill> skills = repository.findAll(new Sort(Sort.Direction.ASC, "name"));
        ModelAndView model = new ModelAndView("skill/index");
        model.addObject("skills", skills);

        return model;
    }

    @GetMapping("/add")
    public String showForm(Skill skill) {
        return "skill/create";
    }

    @RequestMapping("/{id}")
    public
    @ResponseBody
    ModelAndView showDetails(@PathVariable(value = "id") String id) {
        Skill skill = repository.findOne(id);
        ModelAndView model = new ModelAndView("skill/details");

        if (skill != null) {
            model.addObject("skill", skill);
            model.addObject("ressources", skill.getRessources());
            return model;
        }

        return new ModelAndView("error/404");
    }

    @RequestMapping("/{id}/edit")
    public
    @ResponseBody
    ModelAndView editSkill(@PathVariable(value = "id") String id){
        Skill skill = repository.findOne(id);
        ModelAndView model = new ModelAndView("skill/edit");
        if(skill != null){
            model.addObject("skill", skill);
            return model;
        }

        return new ModelAndView("error/404");

    }

    @PostMapping("/{id}")
    public String updateSkill(@PathVariable(value = "id") String id, @Valid Skill skill){
        Skill oldSkill = repository.findOne(id);
        oldSkill.setName(skill.getName());
        repository.save(oldSkill);

        return "redirect:/skills";
    }

    @PostMapping("")
    @RequestMapping("/delete/{id}")
    public
    String delete(@PathVariable(value = "id") String id) {
        if (repository.exists(id)) {
            repository.delete(id);
        }

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

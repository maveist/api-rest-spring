package controller;

import model.XP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import repository.XPRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by leopa on 23/03/2017.
 */
@Controller
@RequestMapping("/xp")
@EnableMongoRepositories("repository")
public class XPController extends WebMvcConfigurerAdapter {

    @Autowired
    private XPRepository repository;

    @GetMapping("")
    public ModelAndView retrieve() {
        List<XP> xp = repository.findAll();
        ModelAndView model = new ModelAndView("xp/index");
        model.addObject("xp", xp);

        return model;
    }

    @GetMapping("/add")
    public String showForm(XP xp) {
        return "xp/create";
    }

    @RequestMapping("/{id}")
    public
    @ResponseBody
    ModelAndView showDetails(@PathVariable(value = "id") String id) {
        XP xp = repository.findOne(id);
        ModelAndView model = new ModelAndView("xp/details_xp");

        if (xp != null) {
            model.addObject("xp", xp);
            model.addObject("ressources", xp.getRessources());
            return model;
        }

        return new ModelAndView("error/404");
    }

    @PostMapping("")
    public String checkXP(@Valid XP xp, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "xp/create";
        }

        repository.save(new XP(xp.getName()));

        return "redirect:/xp";
    }

}

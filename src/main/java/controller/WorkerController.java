package controller;

import model.Skill;
import model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import repository.SkillRepository;
import repository.WorkerRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mmaheo on 09/03/2017.
 */
@Controller
@RequestMapping("/workers")
@EnableMongoRepositories("repository")
public class WorkerController extends WebMvcConfigurerAdapter {

    @Autowired
    private WorkerRepository repository;

    @GetMapping("")
    public ModelAndView retrieve() {
        List<Worker> workers = repository.findAll();
        ModelAndView model = new ModelAndView("worker/index");
        model.addObject("workers", workers);

        return model;
    }

    @GetMapping("/add")
    public String showForm(Worker skill) {
        return "worker/create";
    }

    @RequestMapping("/{id}")
    public
    @ResponseBody
    ModelAndView showDetails(@PathVariable(value = "id") String id) {
        Worker worker = repository.findOne(id);
        ModelAndView model = new ModelAndView("worker/details");

        if (worker != null) {
            model.addObject("worker", worker);
            return model;
        }

        return new ModelAndView("error/404");
    }

    @RequestMapping("/delete/{id}")
    public
    String delete(@PathVariable(value = "id") String id) {
        if (repository.exists(id)) {
            repository.delete(id);
        }

        return "redirect:/workers";
    }

    @PostMapping("/")
    public String checkWorker(@Valid Worker worker, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "worker/create";
        }
        repository.save(new Worker(worker.getFirstName(), worker.getLastName()));

        return "redirect:/workers";
    }

}

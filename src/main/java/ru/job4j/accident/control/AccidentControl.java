package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentService accidents;

    public AccidentControl(AccidentService accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = accidents.getAccidentTypesList();
        model.addAttribute("types", types);
        Collection<Rule> rules = accidents.getAllRule();
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String showAccident(@RequestParam("id") int id, Model model) {
        List<AccidentType> types = accidents.getAccidentTypesList();
        model.addAttribute("types", types);
        model.addAttribute("accident", accidents.findAccident(id));
        Collection<Rule> rules = accidents.getAllRule();
        model.addAttribute("rules", rules);
        return "accident/edit";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        System.out.println(ids);
        Arrays.stream(ids).forEach(id -> accident.addRule(accidents.findByIdRule(Integer.parseInt(id))));
        accident.setType(accident.getType());
        accidents.createOrUpdate(accident);
        return "redirect:/";
    }
}

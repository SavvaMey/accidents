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
import ru.job4j.accident.service.AccidentHbmService;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.JdbcService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
//    private final AccidentService accidents;
//    private final JdbcService jdbcService;
    private final AccidentHbmService accidentHbmService;

    public AccidentControl( AccidentHbmService accidentHbmService) {
        this.accidentHbmService = accidentHbmService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = accidentHbmService.getAccidentTypesList();
        model.addAttribute("types", types);
        Collection<Rule> rules = accidentHbmService.getAllRule();
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String showAccident(@RequestParam("id") int id, Model model) {
        List<AccidentType> types = accidentHbmService.getAccidentTypesList();
        model.addAttribute("types", types);
        model.addAttribute("accident", accidentHbmService.findAccident(id));
        Collection<Rule> rules = accidentHbmService.getAllRule();
        model.addAttribute("rules", rules);
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Arrays.stream(ids).forEach(id -> accident.addRule(accidentHbmService.findByIdRule(Integer.parseInt(id))));
//        accident.setType(jdbcService.findTypeById(accident.getType().getId()));
       accidentHbmService.createOrUpdate(accident);
        return "redirect:/";
    }
}

package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentHbmService;
import ru.job4j.accident.service.AccidentJPAService;
import ru.job4j.accident.service.JdbcService;

@Controller
public class IndexControl {
    private final AccidentJPAService accidentService;

    public IndexControl(AccidentJPAService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.accidentGetAll());
        return "index";
    }
}
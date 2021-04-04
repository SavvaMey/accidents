package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.JdbcService;

@Controller
public class IndexControl {
//    private final AccidentService accidentService;
    private final JdbcService jdbcService;

    public IndexControl(JdbcService jdbcService) {
        this.jdbcService = jdbcService;
    }

//    public IndexControl(AccidentService accidentService) {
//        this.accidentService = accidentService;
//    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", jdbcService.accidentGetAll());
        return "index";
    }
}
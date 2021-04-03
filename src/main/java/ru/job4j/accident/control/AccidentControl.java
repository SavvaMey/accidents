package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {
    private final AccidentService accidents;

    public AccidentControl(AccidentService accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @GetMapping("/edit")
    public String showAccident(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findAccident(id));
        return "accident/edit";
    }

    @PostMapping("/edit")
    public String updateAccident(@RequestParam("id") int id, @ModelAttribute Accident accident) {
        accident.setId(id);
        accidents.update(accident);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/";
    }
}

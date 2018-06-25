package ru.jug.jps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jug.jps.dto.Participant;
import ru.jug.jps.dto.ParticipantSearchForm;
import ru.jug.jps.service.ParticipantService;

import java.util.List;

@Controller
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/info")
    public String infoSearch(Model model) {
        model.addAttribute("participant_form", new ParticipantSearchForm());
        return "participants_form";
    }

    @PostMapping("/info")
    public String infoSearchPost(@ModelAttribute("participant_form") ParticipantSearchForm participantForm, Model model) {
        model.addAttribute("participants", getParticipants(participantForm));
        return "participants_info";
    }

    private List<Participant> getParticipants(ParticipantSearchForm participantForm) {
        return participantService.findByEitherField(participantForm.getSearchValue());
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("participants", participantService.findAll());
        return "participants_info";
    }
}

package mum.edu.cs.cs425.etdm.controller;

import mum.edu.cs.cs425.etdm.model.Athlete;
import mum.edu.cs.cs425.etdm.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AthleteController {

    private AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping(value = "/athlete")
    public ModelAndView athlete(@RequestParam(defaultValue = "0") int pageNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("athletes", athleteService.getAllAthletesPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("athlete/index");
        return modelAndView;
    }

    @GetMapping(value = "/athlete/add")
    public String addAthleteForm(Model model) {
        model.addAttribute("athlete", new Athlete());
        return "athlete/add";
    }

    @PostMapping(value = "/athlete/add")
    public String addAthlete(@Valid @ModelAttribute Athlete athlete, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "athlete/add";
        }

        athlete = athleteService.saveAthlete(athlete);
        return "redirect:/athlete";
    }

    @GetMapping(value = "/athlete/search")
    public ModelAndView searchAthlete(@RequestParam(defaultValue = "0") int pageNo, @RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("athletes", athleteService.getAthletesByFullName(pageNo,searchString));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("athlete/index");
        return modelAndView;
    }

    @GetMapping(value = "/eliteAthlete")
    public ModelAndView eliteAthlete(@RequestParam(defaultValue = "0") int pageNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("athletes", athleteService.getAllEliteAthletesPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("eliteAthlete/index");
        return modelAndView;
    }
}

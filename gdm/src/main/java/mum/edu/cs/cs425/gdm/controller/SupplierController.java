package mum.edu.cs.cs425.gdm.controller;

import mum.edu.cs.cs425.gdm.model.Supplier;
import mum.edu.cs.cs425.gdm.service.SupplierService;
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
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(value = {"/secured/supplier"})
    public ModelAndView supplier(@RequestParam(defaultValue = "0") int pageNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("suppliers", supplierService.getAllSuppliersPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/supplier/index");
        return modelAndView;
    }

    @GetMapping(value = "/secured/supplier/add")
    public String addSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "secured/supplier/add";
    }

    @PostMapping(value = "/secured/supplier/add")
    public String addSupplier(@Valid @ModelAttribute("supplier") Supplier supplier,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/supplier/add";
        }

        supplier = supplierService.saveSupplier(supplier);
        return "redirect:/secured/supplier";
    }

    @GetMapping(value = "/secured/supplier/search")
    public ModelAndView getSuppliersByName(@RequestParam(defaultValue = "0") int pageNo, @RequestParam String searchString)  {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("suppliers", supplierService.getSuppliersByName(searchString, pageNo));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/supplier/index");
        return modelAndView;
    }
}

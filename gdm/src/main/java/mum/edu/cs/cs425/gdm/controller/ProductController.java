package mum.edu.cs.cs425.gdm.controller;

import mum.edu.cs.cs425.gdm.model.Product;
import mum.edu.cs.cs425.gdm.service.ProductService;
import mum.edu.cs.cs425.gdm.service.SupplierService;
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
public class ProductController {

    private ProductService productService;
    private SupplierService supplierService;

    public ProductController(ProductService productService, SupplierService supplierService) {
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @GetMapping(value = {"/secured/product"})
    public ModelAndView product(@RequestParam(defaultValue = "0") int pageNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.getAllProductsPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/product/index");
        return modelAndView;
    }

    @GetMapping(value = "/secured/product/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "secured/product/add";
    }

    @PostMapping(value = "/secured/product/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/product/add";
        }

        product = productService.saveProduct(product);

        return "redirect:/secured/product";
    }

    @GetMapping(value = "/secured/product/search")
    public ModelAndView searchProduct(@RequestParam(defaultValue = "0") int pageNo, @RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.getAllProductsByNamePaged(searchString,pageNo));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/product/index");
        return modelAndView;
    }
}

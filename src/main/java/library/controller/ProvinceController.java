package library.controller;

import library.model.Customer;
import library.model.Province;
import library.service.customer.CustomerService;
import library.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ModelAndView showListProvince() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("province/list");
        modelAndView.addObject("province", provinces);
        return modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView viewProvince(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        if (!province.isPresent()){
            return new ModelAndView("province/err-404");
        }
        Iterable<Customer> customers = customerService.findAllByProvince(province.get());

        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province", province.get());
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProvince(@ModelAttribute Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message","New province created successfully");
        return modelAndView;
    }
@GetMapping("/{id}/edit")
    public ModelAndView showEditProvince(@PathVariable Long id){
    Optional  province =provinceService.findById(id);
    if (province.isPresent()){
        ModelAndView modelAndView = new ModelAndView("province/edit");
        modelAndView.addObject("province",province.get());
        return modelAndView;
    }else {
     ModelAndView modelAndView = new ModelAndView("province/err-404");
     return modelAndView;
    }
}
@PostMapping("/edit")
    public ModelAndView editProvince(@ModelAttribute Province province){
provinceService.save(province);
ModelAndView modelAndView = new ModelAndView("province/edit");
modelAndView.addObject("province",province);
modelAndView.addObject("message","Province updated successfully");
return modelAndView;
}
@GetMapping("/{id}/delete")
    public ModelAndView showDelete(@PathVariable Long id){
        Optional province = provinceService.findById(id);
        if (province.isPresent()){
            ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
            provinceService.remove(id);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("province/err-404");
            return modelAndView;
        }
}
}
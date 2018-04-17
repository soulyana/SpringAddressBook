package me.soulyana.demo.Controllers;

import me.soulyana.demo.Entities.Contact;
import me.soulyana.demo.Repositories.AddressbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    AddressbookRepository addressbookRepository;

    @RequestMapping("/")
    public String listContacts(Model model) {
        model.addAttribute("contacts", addressbookRepository.findAll());
        return "list";
    }

    @RequestMapping("/add")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contactform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "contactform";
        }
        addressbookRepository.save(contact);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showContact(@PathVariable("id") long id, Model model) {
        //NEED .GET FOR SPRING 2
        model.addAttribute("contact", addressbookRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, Model model) {
        //NEED .GET FOR SPRING 2
        model.addAttribute("contact", addressbookRepository.findById(id).get());
        return "contactform";
    }

    @RequestMapping("/delete/{id}")
    public String delBook(@PathVariable("id") long id) {
        addressbookRepository.deleteById(id);
        return "redirect:/";
    }
}
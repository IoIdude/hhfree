package com.example.hhfree.controller;

import com.example.hhfree.entity.Company;
import com.example.hhfree.entity.CompanyType;
import com.example.hhfree.entity.Role;
import com.example.hhfree.entity.User;
import com.example.hhfree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class RegController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String StartRegUser(User user, Model model) {
//        Collection<Role> roles = service.getRoles();
//        model.addAttribute("role", roles);
        return "reg";
    }

    @PostMapping("/reg")
    public String AddUser(@Valid User user, BindingResult result, @ModelAttribute("role") Role role, Model model) {
        if (!result.hasErrors()) {
            if (service.getUserByLogin(user.getLogin()) == null) {
                user.setRoles(new ArrayList<>());
                service.addUser(user);
                service.addRoleToUser(user.getLogin(), "USER");

                return "redirect:/login";
            } else {
                model.addAttribute("userExist", true);
            }
        }

        return "reg";
    }
}

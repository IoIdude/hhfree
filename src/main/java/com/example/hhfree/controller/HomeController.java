package com.example.hhfree.controller;

import com.example.hhfree.entity.*;
import com.example.hhfree.repository.CompanyRepo;
import com.example.hhfree.repository.WorkRepo;
import com.example.hhfree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService service;

    @GetMapping("/home")
    public String Preview(Model model) {
        Collection<Work> works = service.getWorks();
        Collection<Position> positions = service.getPositions();

        model.addAttribute("works", works);
        model.addAttribute("positions", positions);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();

        if (role.equals("[ADMIN]")) {
            return "admin_home";
        }
        else {
            return "home";
        }
    }

    @GetMapping("/home_request")
    public String PreviewRequest(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "positionName", required = false) String positionName, Model model) {
        Collection<Work> works = service.getWorks();
        Collection<Position> positions = service.getPositions();

        if ((search == null || search.isEmpty()) && (positionName == null || positionName.isEmpty())) { }
        else if (search != null)
        {
            works = service.getWorksByContainingName(search);
        }
        else if (works != null)
        {
            works.clear();
            Collection<WorkDetails> workDetails = service.getWorksDetailsByIdPosition(service.getPositionByName(positionName));

            for (var el : workDetails) {
                works.add(service.getWorkByDetails(el));
            }
        }

        model.addAttribute("works", works);
        model.addAttribute("search", search);
        model.addAttribute("positions", positions);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();

        if (role.equals("[ADMIN]")) {
            return "admin_home";
        }
        else {
            return "home";
        }
    }

    @GetMapping("/work_request")
    public String PreviewWorkRequest(@RequestParam(value = "search", required = false) String search, Model model) {
        Collection<Work> works = service.getWorks();

        if ((search == null || search.isEmpty())) { }
        else
        {
            works = service.getWorksByContainingName(search);
        }

        model.addAttribute("works", works);

        return "work_list";
    }

    @GetMapping("/user_request")
    public String PreviewUserRequest(@RequestParam(value = "search", required = false) String search, Model model) {
        Collection<User> users = service.getUsers();

        if ((search == null || search.isEmpty())) { }
        else
        {
            users = service.getUsersBySurname(search);
        }

        model.addAttribute("users", users);

        return "user_list";
    }

    @GetMapping("/create_company")
    public String CreateCompany(Company company, Model model) {
        Collection<CompanyType> companyTypes = service.getCompanyTypes();
        model.addAttribute("types", companyTypes);
        return "create_company";
    }

    @PostMapping("/create_company")
    public String AddCompany(@Valid Company company, BindingResult result, @ModelAttribute("types") CompanyType type, Model model) {
        if (!result.hasErrors()) {
            var company_db = service.getCompanyByName(company.getName());
            var type_db = service.getCompanyType(type.getTittle());

            if (company_db != null) {
                if (company_db.getName() == company.getName() && company_db.getCompany_type_id() != company.getCompany_type_id()) {
                    company.setCompany_type_id(type_db);
                    service.addCompany(company);

                    return "redirect:/create_details_work";
                }
                else {
                    Collection<CompanyType> companyTypes = service.getCompanyTypes();
                    model.addAttribute("types", companyTypes);
                    model.addAttribute("userExist", true);
                }
            }
            else {
                company.setCompany_type_id(type_db);
                service.addCompany(company);

                return "redirect:/create_details_work";
            }
        }
        else {
            Collection<CompanyType> companyTypes = service.getCompanyTypes();
            model.addAttribute("types", companyTypes);
        }

        return "create_company";
    }

    @GetMapping("/create_details_work")
    public String createDetails(Model model) {
        WorkDetails details = new WorkDetails();
        Collection<Sphere> spheres = service.getSpheries();
        Collection<Position> positions = service.getPositions();
        Collection<Country> countries = service.getCountries();
        Collection<Education> educations = service.getEducations();
        Collection<Employment> employments = service.getEmployments();

        model.addAttribute("spheres", spheres);
        model.addAttribute("positions", positions);
        model.addAttribute("countries", countries);
        model.addAttribute("educations", educations);
        model.addAttribute("employments", employments);
        model.addAttribute("details", details);

        return "create_details_work";
    }

    @PostMapping("/create_details_work")
    public String AddDetails(@ModelAttribute("details") WorkDetails details, @ModelAttribute("spheres") Sphere sphere, @ModelAttribute("positions") Position position,
            @ModelAttribute("countries") Country country,
            @ModelAttribute("educations") Education education,
            @ModelAttribute("employments") Employment employment, Model model) {

        Country country_db = service.getCountry(country.getCountryName());
        Education education_db = service.getEducation(education.getEducationName());
        Employment employment_db = service.getEmployment(employment.getEmploymentName());
        Sphere sphere_db = service.getSphere(sphere.getSphereName());
        Position position_db = service.getPositionByName(position.getPositionName());
        System.out.println(country.getCountryName() + " " + education.getEducationName() + " " + employment.getEmploymentName() + " " + sphere.getSphereName() + " " + position.getPositionName());

        details.setCountry_id(country_db);
        details.setEducation_id(education_db);
        details.setEmployment_id(employment_db);
        details.setSphere_id(sphere_db);
        details.setPositionId(position_db);

        service.addWorkDetails(details);

        return "redirect:/create_work";
    }

    @GetMapping("/create_work")
    public String createWorks(Work work, Model model) {
        Collection<WorkDetails> workDetails = service.getWorksDetails();
        Collection<Company> companies = service.getCompanies();

        model.addAttribute("companies", companies);
        model.addAttribute("workDetails", workDetails);

        return "create_work";
    }

    @PostMapping("/create_work")
    public String AddWorks(@Valid Work work, BindingResult result, @ModelAttribute("companies") Company company, @ModelAttribute("workDetails") WorkDetails workDetail, Model model) { // @ModelAttribute("companies") Company company, @ModelAttribute("workDetails") WorkDetails workDetails,
        if (!result.hasErrors()) {
            WorkDetails workDetails_db = service.getWorkDetailsByName(workDetail.getInfo());
            Company company_db = service.getCompanyByName(company.getName());

            work.setCompany_id(company_db);
            work.setWorkDetailsId(workDetails_db);

            Collection<Work> work_db = service.getWorksByName(work.getWorkName());
            if (!work_db.isEmpty()) {
                Collection<WorkDetails> workDetails = service.getWorksDetails();
                Collection<Company> companies = service.getCompanies();

                model.addAttribute("companies", companies);
                model.addAttribute("workDetails", workDetails);
                model.addAttribute("userExist", true);
            }
            else {
                service.addWork(work);

                return "redirect:/home";
            }
        }
        else {
            Collection<WorkDetails> workDetails = service.getWorksDetails();
            Collection<Company> companies = service.getCompanies();

            model.addAttribute("companies", companies);
            model.addAttribute("workDetails", workDetails);
        }

        return "create_work";
    }

    @GetMapping("/user_details")
    public String CheckUserDetails(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        User current_user = service.getUserByLogin(login);
        model.addAttribute("user", current_user);

        return "user_details";
    }

    @GetMapping("/edit_user_details")
    public String EditUserDetails(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        User current_user = service.getUserByLogin(login);

        Collection<Citizenship> citizenship = service.getCitizenships();
        Collection<Country> country = service.getCountries();
        Collection<Education> education = service.getEducations();
        Collection<Employment> employment = service.getEmployments();
        Collection<Position> position = service.getPositions();
        Collection<Sphere> sphere = service.getSpheries();
        Collection<Status> status = service.getStatuses();
        current_user.setPassword("");

        model.addAttribute("user", current_user);
        model.addAttribute("citizenship", citizenship);
        model.addAttribute("country", country);
        model.addAttribute("education", education);
        model.addAttribute("employment", employment);
        model.addAttribute("position", position);
        model.addAttribute("sphere", sphere);
        model.addAttribute("status", status);

        return "edit_user_details";
    }

    @PostMapping("/edit_user_details")
    public String SaveEditUserDetails(@Valid User user, BindingResult result,
                                      @ModelAttribute("citizenship") Citizenship citizenship,
                                      @ModelAttribute("country") Country country,
                                      @ModelAttribute("education") Education education,
                                      @ModelAttribute("employment") Employment employment,
                                      @ModelAttribute("position") Position position,
                                      @ModelAttribute("sphere") Sphere sphere,
                                      @ModelAttribute("status") Status status, Model model) {

        if (!result.hasErrors()) {
            User updUser = service.getUserById(user.getId());

            updUser.setLogin(user.getLogin());
            updUser.setSurname(user.getSurname());
            updUser.setFirst_name(user.getFirst_name());
            updUser.setMiddle_name(user.getMiddle_name());
            updUser.setAge(user.getAge());
            updUser.setPassword(user.getPassword());
            updUser.setCitizenship_id(service.getCitizenshipByName(citizenship.getNameCitizenship()));
            updUser.setCountry_id(service.getCountry(country.getCountryName()));
            updUser.setEducation_id(service.getEducation(education.getEducationName()));
            updUser.setEmployment_id(service.getEmployment(employment.getEmploymentName()));
            updUser.setPositionId(service.getPositionByName(position.getPositionName()));
            updUser.setSphere_id(service.getSphere(sphere.getSphereName()));
            updUser.setStatus_id(service.getStatusByName(status.getNameStatus()));

            service.addUser(updUser);
            return "redirect:/check_user/" + updUser.getId().toString();
        }
        else
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String login = authentication.getName();

            User current_user = service.getUserByLogin(login);

            Collection<Citizenship> citizenshi = service.getCitizenships();
            Collection<Country> countr = service.getCountries();
            Collection<Education> educatio = service.getEducations();
            Collection<Employment> employmen = service.getEmployments();
            Collection<Position> positio = service.getPositions();
            Collection<Sphere> spher = service.getSpheries();
            Collection<Status> statu = service.getStatuses();
            current_user.setPassword("");

            model.addAttribute("user", current_user);
            model.addAttribute("citizenship", citizenshi);
            model.addAttribute("country", countr);
            model.addAttribute("education", educatio);
            model.addAttribute("employment", employmen);
            model.addAttribute("position", positio);
            model.addAttribute("sphere", spher);
            model.addAttribute("status", statu);
        }

        return "edit_user_details";
    }

    @GetMapping( "/index/{id}")
    public String WorkDetails(@PathVariable("id") Integer id, Model model) {
        Work current_selected_work = service.getWorkById(id);

        model.addAttribute("work", current_selected_work);

        return "index";
    }

    @GetMapping("/work_list")
    public String GetWorkList(Model model)
    {
        Collection<Work> works = service.getWorks();
        model.addAttribute("works", works);

        return "work_list";
    }

    @GetMapping("/user_list")
    public String GetUserList(Model model)
    {
        Collection<User> users = service.getUsers();
        model.addAttribute("users", users);

        return "user_list";
    }

    @GetMapping("/check_user/{id}")
    public String GetUser(@PathVariable("id") Integer id, Model model)
    {
        User user = service.getUserById(id);
        model.addAttribute("user", user);

        return "check_user";
    }

    @GetMapping("/edit_user/{id}")
    public String EditUser(@PathVariable("id") Integer id, Model model) {
        User user = service.getUserById(id);


        Collection<Citizenship> citizenship = service.getCitizenships();
        Collection<Country> country = service.getCountries();
        Collection<Education> education = service.getEducations();
        Collection<Employment> employment = service.getEmployments();
        Collection<Position> position = service.getPositions();
        Collection<Sphere> sphere = service.getSpheries();
        Collection<Status> status = service.getStatuses();
        user.setPassword("");

        model.addAttribute("user", user);
        model.addAttribute("citizenship", citizenship);
        model.addAttribute("country", country);
        model.addAttribute("education", education);
        model.addAttribute("employment", employment);
        model.addAttribute("position", position);
        model.addAttribute("sphere", sphere);
        model.addAttribute("status", status);

        return "edit_user";
    }

    @PostMapping("/edit_user")
    public String EditUser(@Valid User user, BindingResult result,
                                      @ModelAttribute("citizenship") Citizenship citizenship,
                                      @ModelAttribute("country") Country country,
                                      @ModelAttribute("education") Education education,
                                      @ModelAttribute("employment") Employment employment,
                                      @ModelAttribute("position") Position position,
                                      @ModelAttribute("sphere") Sphere sphere,
                                      @ModelAttribute("status") Status status, Model model) {

        if (!result.hasErrors()) {
            User updUser = service.getUserById(user.getId());

            updUser.setLogin(user.getLogin());
            updUser.setSurname(user.getSurname());
            updUser.setFirst_name(user.getFirst_name());
            updUser.setMiddle_name(user.getMiddle_name());
            updUser.setAge(user.getAge());
            updUser.setPassword(user.getPassword());
            updUser.setCitizenship_id(service.getCitizenshipByName(citizenship.getNameCitizenship()));
            updUser.setCountry_id(service.getCountry(country.getCountryName()));
            updUser.setEducation_id(service.getEducation(education.getEducationName()));
            updUser.setEmployment_id(service.getEmployment(employment.getEmploymentName()));
            updUser.setPositionId(service.getPositionByName(position.getPositionName()));
            updUser.setSphere_id(service.getSphere(sphere.getSphereName()));
            updUser.setStatus_id(service.getStatusByName(status.getNameStatus()));

            service.addUser(updUser);
            return "redirect:/user_details";
        }
        else
        {
            User use = service.getUserById(user.getId());


            Collection<Citizenship> citizenshi = service.getCitizenships();
            Collection<Country> countr = service.getCountries();
            Collection<Education> educatio = service.getEducations();
            Collection<Employment> employmen = service.getEmployments();
            Collection<Position> positio = service.getPositions();
            Collection<Sphere> spher = service.getSpheries();
            Collection<Status> statu = service.getStatuses();
            user.setPassword("");

            model.addAttribute("user", use);
            model.addAttribute("citizenship", citizenshi);
            model.addAttribute("country", countr);
            model.addAttribute("education", educatio);
            model.addAttribute("employment", employmen);
            model.addAttribute("position", positio);
            model.addAttribute("sphere", spher);
            model.addAttribute("status", statu);
        }

        return "edit_user";
    }

    @GetMapping("/delete_user/{id}")
    public String DeleteUser(@PathVariable("id") Integer id) {
        User selected_user = service.getUserById(id);
        service.deleteUserById(selected_user);

        return "redirect:/user_list";
    }

    @GetMapping("/delete_work/{id}")
    public String DeleteWork(@PathVariable("id") Integer id) {
        Work curr_work = service.getWorkById(id);
        service.deleteWorkById(curr_work);

        return "redirect:/work_list";
    }
}

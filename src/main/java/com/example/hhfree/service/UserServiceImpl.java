package com.example.hhfree.service;

import com.example.hhfree.entity.*;
import com.example.hhfree.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private WorkRepo workRepo;
    @Autowired
    private CompanyTypeRepo companyTypeRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private SphereRepo sphereRepo;
    @Autowired
    private PositionRepo positionRepo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private EducationRepo educationRepo;
    @Autowired
    private EmploymentRepo employmentRepo;
    @Autowired
    private WorkDetailsRepo workDetailsRepo;
    @Autowired
    private CitizenshipRepo citizenshipRepo;
    @Autowired
    private StatusRepo statusRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);

    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User getUserByLogin(String username) {
        return userRepo.findByLogin(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Collection<Role> getRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Collection<Work> getWorks() {
        return workRepo.findAll();
    }

    @Override
    public Collection<User> getUsers() {
        Collection<User> users = userRepo.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                users.remove(user);
                return users;
            }
        }

        return users;
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepo.save(company);
    }

    @Override
    public CompanyType addCompanyType(CompanyType type) {
        return companyTypeRepo.save(type);
    }

    @Override
    public CompanyType getCompanyType(String type) {
        return companyTypeRepo.findByTittle(type);
    }

    @Override
    public Country getCountry(String name) {
        return countryRepo.findByCountryName(name);
    }

    @Override
    public Education getEducation(String name) {
        return educationRepo.findByEducationName(name);
    }

    @Override
    public Employment getEmployment(String name) {
        return employmentRepo.findByEmploymentName(name);
    }

    @Override
    public Sphere getSphere(String name) {
        return sphereRepo.findBySphereName(name);
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyRepo.findByName(name);
    }

    @Override
    public Collection<CompanyType> getCompanyTypes() {
        return companyTypeRepo.findAll();
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByLogin(username);
        Role role = roleRepo.findByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public Collection<Company> getCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Sphere addSphere(Sphere sphere) {
        return sphereRepo.save(sphere);
    }

    @Override
    public Position addPosition(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepo.save(country);
    }

    @Override
    public Education addEducation(Education education) {
        return educationRepo.save(education);
    }

    @Override
    public Employment addEmployment(Employment employment) {
        return employmentRepo.save(employment);
    }

    @Override
    public Status addStatus(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public Citizenship addCitizenship(Citizenship citizenship) {
        return citizenshipRepo.save(citizenship);
    }

    @Override
    public Collection<Sphere> getSpheries() {
        return sphereRepo.findAll();
    }

    @Override
    public Collection<Position> getPositions() {
        return positionRepo.findAll();
    }

    @Override
    public Collection<Country> getCountries() {
        return countryRepo.findAll();
    }

    @Override
    public Collection<Education> getEducations() {
        return educationRepo.findAll();
    }

    @Override
    public Collection<Employment> getEmployments() {
        return employmentRepo.findAll();
    }

    @Override
    public WorkDetails addWorkDetails(WorkDetails workDetails) {
        return workDetailsRepo.save(workDetails);
    }

    @Override
    public Collection<WorkDetails> getWorksDetails() {
        return workDetailsRepo.findAll();
    }

    @Override
    public Work addWork(Work work) {
        return workRepo.save(work);
    }

    @Override
    public Work getWorkById(int id) {
        return workRepo.findById(id);
    }

    @Override
    public WorkDetails getWorkDetailsByName(String name) {
        return workDetailsRepo.findByInfo(name);
    }

    @Override
    public Citizenship getCitizenshipByName(String name) {
        return citizenshipRepo.findByNameCitizenship(name);
    }

    @Override
    public Status getStatusByName(String name) {
        return statusRepo.findByNameStatus(name);
    }

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public void deleteUserById(User user) {
        //User u = userRepo.findById(user.getId().intValue());
        userRepo.delete(user);
    }

    @Override
    public void deleteWorkById(Work work) {
        //String s = work.getWorkDetailsId().getInfo();
        WorkDetails wd = workDetailsRepo.findById(work.getWorkDetailsId().getId().intValue());
        Company c = companyRepo.findById(work.getCompany_id().getId().intValue());
        //Work w = workRepo.findById(work.getId().intValue());

        workRepo.delete(work);
        workDetailsRepo.delete(wd);
        companyRepo.delete(c);
    }

    @Override
    public Collection<Work> findByWorkName(String name) {
        return workRepo.findByWorkName(name);
    }

    @Override
    public Position getPositionByName(String id) {
        return positionRepo.findByPositionName(id);
    }

    @Override
    public Collection<WorkDetails> getWorksDetailsByIdPosition(Position position) {
        return workDetailsRepo.findByPositionId(position);
    }

    @Override
    public Work getWorkByDetails(WorkDetails workDetails) {
        return workRepo.findByWorkDetailsId(workDetails);
    }

    @Override
    public Collection<Work> getWorksByContainingName(String name) {
        return workRepo.findByWorkNameContaining(name);
    }

    @Override
    public Collection<Work> getWorksByName(String name) {
        return workRepo.findByWorkName(name);
    }

    @Override
    public Collection<User> getUsersBySurname(String surname) {
        return userRepo.findBySurname(surname);
    }

    @Override
    public Collection<Citizenship> getCitizenships() {
        return citizenshipRepo.findAll();
    }


    @Override
    public Collection<Status> getStatuses() {
        return statusRepo.findAll();
    }
}

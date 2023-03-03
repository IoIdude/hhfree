package com.example.hhfree.service;

import com.example.hhfree.entity.*;

import java.util.Collection;

public interface UserService {
    User addUser(User user);
    Role addRole(Role role);
    // User getCitizenShip();
    Collection<Role> getRoles();
    Collection<Work> getWorks();
    Company addCompany(Company company);
    CompanyType addCompanyType(CompanyType type);
    CompanyType getCompanyType(String type);
    Country getCountry(String name);
    Education getEducation(String name);
    Employment getEmployment(String name);
    Sphere getSphere(String name);
    Company getCompanyByName(String name);
    Collection<CompanyType> getCompanyTypes();
    User getUserByLogin(String username);
    void addRoleToUser(String username, String roleName);
    Collection<Company> getCompanies();
    Sphere addSphere(Sphere sphere);
    Position addPosition(Position position);
    Country addCountry(Country country);
    Education addEducation(Education education);
    Employment addEmployment(Employment employment);
    Status addStatus(Status status);
    Citizenship addCitizenship(Citizenship citizenship);
    Collection<Sphere> getSpheries();
    Collection<Position> getPositions();
    Collection<Country> getCountries();
    Collection<Education> getEducations();
    Collection<Employment> getEmployments();
    WorkDetails addWorkDetails(WorkDetails workDetails);
    Collection<WorkDetails> getWorksDetails();
    Work addWork(Work work);
    Work getWorkById(int id);
    WorkDetails getWorkDetailsByName(String name);
    Collection<Citizenship> getCitizenships();
    Collection<Status> getStatuses();
    Collection<User> getUsers();
    Citizenship getCitizenshipByName(String name);
    // Sphere getSphereByName(String name);
    Status getStatusByName(String name);
    User getUserById(int id);
    void deleteUserById(User user);
    void deleteWorkById(Work work);
    Collection<Work> findByWorkName(String name);
    Position getPositionByName(String name);
    Collection<WorkDetails> getWorksDetailsByIdPosition(Position position);
    Work getWorkByDetails(WorkDetails workDetails);
    Collection<Work> getWorksByContainingName(String name);
    Collection<Work> getWorksByName(String name);
    Collection<User> getUsersBySurname(String surname);
}

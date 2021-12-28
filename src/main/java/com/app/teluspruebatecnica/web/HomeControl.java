package com.app.teluspruebatecnica.web;


import com.app.teluspruebatecnica.dao.UserDAO;
import com.app.teluspruebatecnica.model.Donation;
import com.app.teluspruebatecnica.model.Rol;
import com.app.teluspruebatecnica.model.Users;
import com.app.teluspruebatecnica.service.countryservice.CountryService;
import com.app.teluspruebatecnica.service.donationservice.DonationService;
import com.app.teluspruebatecnica.service.institutionservice.InstitutionService;
import com.app.teluspruebatecnica.service.rolservice.RolService;
import com.app.teluspruebatecnica.service.userservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
public class HomeControl {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private RolService rolService;

    @Autowired
    private UserDAO userDAO;


    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user, boolean invalidSession) {

        log.info("user login: "+ user);
        log.info("executing the controller SpringBoot");

        if (invalidSession){
            model.addAttribute("invalidSession", "your session has expired!!");
        }
        return "index";
    }


    //we add to the model the list of benefactors, countries and institutions, and the size for each one them
    @ModelAttribute
    public void addAttributes(Model model, @AuthenticationPrincipal User user) {


        try {
            //get the user that is logged in to select just his/her donations also the amount of donations
            Double totalAmount;
            Users benefactor = new Users();
            benefactor = userDAO.findByUsername(user.getUsername());
            var donationList = donationService.getDonationsByUser(benefactor);
            totalAmount = donationList.stream().mapToDouble(Donation::getDonationAmount).sum();
            model.addAttribute("donationList", donationList);
            model.addAttribute("totalAmount", totalAmount);
        }catch (Exception e) {
            e.printStackTrace();
        }

        var benefactors = userService.getUsers();
        var countryList = countryService.getAllCountries();
        var institutionList = institutionService.getInstitutions();


        model.addAttribute("countryList", countryList);
        model.addAttribute("totalCountries", countryList.size());
        model.addAttribute("institutionList", institutionList);
        model.addAttribute("totalInstitutions", institutionList.size());
        model.addAttribute("benefactors", benefactors.size());

    }

    // inicialized the user object to be used in the form
    @GetMapping("/register")
    public String addNewUser(Users users) {
        return "register";
    }

   //method to save the user that wants to donate
    @PostMapping("/saveNewUser")
    public String saveNewUser(Users users, RedirectAttributes redirect) {

        //check if the user already exists
        Users newUser = userService.getUserByidDocument(users.getIdDocument());
        if(newUser != null) {
           redirect.addFlashAttribute("message", "Your account already exists in our System!");
           return "redirect:/register";
        }

        //before to persist the user, we need to set the password encripter
        users.setPassword(PasswordEncripter.encode(users.getPassword()));
        userService.addNewUser(users);

        //added the role to the user
        Rol newRol = new Rol();
        newRol.setName("ROLE_USER");
        newRol.setUser(users);
        rolService.addNewRole(newRol);

        redirect.addFlashAttribute("message", users.getFirstName()+", Your account has been created successfully!");
        return "redirect:/login";
    }

    //we inicialized the donation object to be used in the form
    @GetMapping("/donate")
    public String donate(Donation donation) {
        return "AddNewDonation";
    }

    //method to save a new donation from the user
    @PostMapping("/saveDonation")
    public String saveDonation(Donation donation,@AuthenticationPrincipal User user, RedirectAttributes redirect) {

        //we need to add the user to the donation
        //we get the user from the repository method which is the one that is logged in
        Users benefactor = userDAO.findByUsername(user.getUsername());
        donation.setUser(benefactor);
        donationService.saveDonation(donation);
        redirect.addFlashAttribute("message", "Your donation has been saved successfully!");
        return "redirect:/";
    }


}

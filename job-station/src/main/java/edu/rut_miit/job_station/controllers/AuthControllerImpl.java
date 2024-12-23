package edu.rut_miit.job_station.controllers;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.job_station_contracts.controllers.AuthController;
import com.example.job_station_contracts.input.RegisterForm;
import com.example.job_station_contracts.viewmodels.NoInputPageViewModel;
import com.example.job_station_contracts.viewmodels.RegisterPageViewModel;

import edu.rut_miit.job_station.dto.user.UserCreateDto;
import edu.rut_miit.job_station.services.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthControllerImpl extends BaseControllerImpl implements AuthController {
    private UserService userService;

    public AuthControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/login")
    public String loginPage(Model model) {
        if (getLoggedUser() != null) {
            return "redirect:/";
        }

        setBasicAttributes(new NoInputPageViewModel(createBaseViewModel("Войти")), getLoggedUser(), model);

        return "pages/auth/login-page";
    }

    @PostMapping("/login_error")
    public String handleLoginError(String username, String password, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/auth/login";
    }

    @Override
    public String registerPage(Model model) {
        if (getLoggedUser() != null) {
            return "redirect:/";
        }

        setBasicAttributes(new RegisterPageViewModel(createBaseViewModel("Регистрация")), getLoggedUser(), model);
        model.addAttribute("form", new RegisterForm("", "", "", "", "", ""));

        return "pages/auth/signup-page";
    }

    @Override
    public String registerUser(@Valid @ModelAttribute("form") RegisterForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            form.setPassword("");
            form.setConfirmPassword("");

            model.addAttribute("model", new RegisterPageViewModel(createBaseViewModel("Регистрация")));
            model.addAttribute("form", form);

            return "pages/auth/signup-page";
        }

        var registerDto = new UserCreateDto(
            form.getFirstName(),
            form.getMiddleName(),
            form.getLastName(),
            form.getLogin(),
            form.getPassword()
        );

        userService.addUser(registerDto);
        return "redirect:/auth/login";
    }
}

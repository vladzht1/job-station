package edu.rut_miit.job_station.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.admin.AdminUserController;
import com.example.job_station_contracts.input.UserForm;
import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.viewmodels.NoInputPageViewModel;
import com.example.job_station_contracts.viewmodels.admin.AdminEntityTableViewModel;

import edu.rut_miit.job_station.controllers.BaseControllerImpl;
import edu.rut_miit.job_station.dto.user.UserCreateDto;
import edu.rut_miit.job_station.dto.user.UserDto;
import edu.rut_miit.job_station.dto.user.UserUpdateDto;
import edu.rut_miit.job_station.services.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class AdminUserControllerImpl extends BaseControllerImpl implements AdminUserController {
    private UserService userService;

    public AdminUserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("")
    public String usersPage(Model model) {
        List<UserViewModel> users = userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList();

        setBasicAttributes(new AdminEntityTableViewModel<>(
            createBaseViewModel("Админ панель: пользователи"),
            users
        ), getLoggedUser(), model);

        return "pages/admin/users-page";
    }

    @Override
    public String createUserPage(Model model) {
        setBasicAttributes(new NoInputPageViewModel(createBaseViewModel("Админ панель: создание пользователя")), getLoggedUser(), model);

        model.addAttribute("form", new UserForm(null, "", "", "", "", "", false));
        model.addAttribute("canSetCommercial", false);
        model.addAttribute("editPassword", true);

        return "pages/admin/user-create-page";
    }

    @Override
    public String createUser(@Valid @ModelAttribute("form") UserForm userForm, BindingResult bindingResult, Model model) {
        if (userForm.getPassword().trim().length() < 8) {
            bindingResult.addError(new ObjectError(null, "Пароль должен содержать как минимум 8 символов"));
        }

        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            userForm.setPassword("");

            setBasicAttributes(new NoInputPageViewModel(createBaseViewModel("Админ панель: создание пользователя")), getLoggedUser(), model);

            model.addAttribute("form", userForm);
            model.addAttribute("canSetCommercial", false);
            model.addAttribute("editPassword", true);

            return "pages/admin/user-create-page";
        }

        var userDto = new UserCreateDto(
            userForm.getFirstName(),
            userForm.getMiddleName(),
            userForm.getLastName(),
            userForm.getLogin(),
            userForm.getPassword()
        );

        userService.addUser(userDto);
        return "redirect:/admin/users";
    }

    @Override
    public String editUserPage(@PathVariable String id, Model model) {
        UserDto user = userService.findUserById(id);

        UserViewModel userViewModel = modelMapper().map(user, UserViewModel.class);

        setBasicAttributes(new NoInputPageViewModel(createBaseViewModel("Админ панель: обновление пользователя")), user, model);

        model.addAttribute("form", new UserForm(
            userViewModel.getId(),
            userViewModel.getFirstName(),
            userViewModel.getMiddleName(),
            userViewModel.getLastName(),
            userViewModel.getUsername(),
            "",
            userViewModel.isCommercialAccount()
        ));
        model.addAttribute("canSetCommercial", true);
        model.addAttribute("editPassword", false);

        return "pages/admin/user-edit-page";
    }

    @Override
    public String editUser(@PathVariable String id, @Valid @ModelAttribute("form") UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            userForm.setPassword("");

            setBasicAttributes(new NoInputPageViewModel(createBaseViewModel("Админ панель: обновление пользователя")), getLoggedUser(), model);

            model.addAttribute("form", userForm);
            model.addAttribute("canSetCommercial", true);
            model.addAttribute("editPassword", false);

            return "pages/admin/user-edit-page";
        }

        var userDto = new UserUpdateDto(
            id,
            userForm.getFirstName(),
            userForm.getMiddleName(),
            userForm.getLastName(),
            userForm.getLogin(),
            userForm.getPassword(),
            userForm.getCommercialAccount()
        );

        userService.updateUser(userDto);
        return "redirect:/admin/users";
    }

    @Override
    @GetMapping("/{id}/block")
    public String blockUser(@PathVariable String id, Model model) {
        userService.block(id);
        return "redirect:/admin/users";
    }

    @Override
    @GetMapping("/{id}/unblock")
    public String unblockUser(@PathVariable String id, Model model) {
        userService.unblock(id);
        return "redirect:/admin/users";
    }
}

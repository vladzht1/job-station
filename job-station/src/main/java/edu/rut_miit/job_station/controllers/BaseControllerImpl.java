package edu.rut_miit.job_station.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.example.job_station_contracts.controllers.BaseController;
import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.viewmodels.BaseViewModel;

import edu.rut_miit.job_station.dto.user.UserDto;
import edu.rut_miit.job_station.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseControllerImpl implements BaseController {
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public void setBasicAttributes(Object mainViewModel, UserDto loggedUser, Model model) {
        UserViewModel user = toLoggedUserViewModel(loggedUser);

        model.addAttribute("model", mainViewModel);
        model.addAttribute("currentUser", user);
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title + " - JobStation");
    }

    public UserViewModel toLoggedUserViewModel(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return modelMapper.map(userDto, UserViewModel.class);
    }

    public UserDto getLoggedUser() {
        var auth = getAuthentication();

        if (auth == null) {
            return null;
        }

        return userService.findUserByUsername(auth.getName());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ModelMapper modelMapper() {
        return modelMapper;
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return authentication;
    }
}

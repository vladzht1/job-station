package edu.rut_miit.job_station.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.viewmodels.NoInputPageViewModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController extends BaseControllerImpl implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        String errorMessage = "500! Неизвестная ошибка!";
        String errorDescription = "Произошла неизвестная ошибка, повторите попытку позже";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMessage = "404! Ресурс не найден!";
                errorDescription = "Ресурс, к которому вы обращаетесь, не существует или был удалён";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorMessage = "403! Доступ запрещён!";
                errorDescription = "У вас нет доступа к данному ресурсу";
            }
        }

        request.setAttribute("model", new NoInputPageViewModel(createBaseViewModel(errorMessage)));
        request.setAttribute("currentUser", toLoggedUserViewModel(getLoggedUser()));
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("errorDescription", errorDescription);

        return "shared/error-page";
    }
}

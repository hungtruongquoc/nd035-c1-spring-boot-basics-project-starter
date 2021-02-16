package com.udacity.jwdnd.course1.cloudstorage.ControllerAdvice;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request,
                                               HttpServletResponse response) {
        ModelMap newModelMap = new ModelMap();
        newModelMap.addAttribute("errorMessage", "File too large!");
        return new ModelAndView("redirect:/home", (Map<String, ?>) newModelMap);
    }
}

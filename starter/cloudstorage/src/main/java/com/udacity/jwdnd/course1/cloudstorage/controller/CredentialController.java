package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Controller
@RequestMapping("/credentials")
@EnableWebSecurity
public class CredentialController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CredentialController.class);
    private final CredentialService mainService;
    private final UserService userService;
    private final EncryptionService encryptor;

    public CredentialController(CredentialService credentialSrv, UserService userService, EncryptionService encryptor) {
        this.mainService = credentialSrv;
        this.mainServiceClass = this.mainService.getClass();
        this.mainModelClass = Credential.class;
        this.userService = userService;
        this.encryptor = encryptor;
    }

    private HashMap<String, Object> createModelViewData(String message, boolean success) {
        return super.createModelViewData("credential", message, success);
    }

    @GetMapping()
    public ModelAndView showList(Model model) {
        model.addAttribute("active", "credentials");
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }

    @GetMapping("/{id}")
    public ModelAndView showDetail(Model model, @PathVariable Integer id) {
        model.addAttribute("active", "credentials");
        model.addAttribute("currentCredential", id);
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }

    @PostMapping()
    public ModelAndView createCredentials(Model model, @ModelAttribute("newCredential") CredentialForm form,
                                          @RequestParam(name = "action", required = false) String action) {
        Integer result;
        HashMap<String, Object> viewData;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUser((String) auth.getPrincipal());
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        form.setKey(Base64.getEncoder().encodeToString(salt));

        if (null != action && action.contains("delete")) {
            if (form.hasId()) {
                if (1 == this.mainService.deleteCredential(form.getCredentialId())) {
                    viewData = this.createModelViewData("The provided credential was deleted successfully.",
                            true);
                } else {
                    logger.error("Failed to delete the selected credential.");
                    viewData = this.createModelViewData("The provided credential cannot be deleted.",
                            false);
                }
            } else {
                logger.error("No credential was selected.");
                viewData = this.createModelViewData("No credential selected.", false);
            }
        } else {
            if (form.isValid()) {
                HashMap<String, Object> methodResult = this.getDataMethod(null != form.getCredentialId());
                form.setUserId(currentUser.getUserId());
                // Determines the target object
                Credential target = new Credential();

                if (null != form.getCredentialId()) {
                    target = this.mainService.getById(form.getCredentialId());
                }
                // Updates data for the target object
                logger.info("Model: {}", form.toString());
                target.importFormValue(form);

                if (this.hasMethod(methodResult)) {
                    Method method = (Method) methodResult.get("method");
                    String methodName = (String) methodResult.get("methodName");

                    try {
                        result = (Integer) method.invoke(this.mainService, target);
                        if (1 == result) {
                            if (this.isMethodAnUpdate(methodResult)) {
                                viewData = this.createModelViewData("Provided credential updated" , true);
                            } else {
                                viewData = this.createModelViewData("New credential created", true);
                            }
                        } else {
                            viewData = this.createModelViewData("Failed to manipulate provided credential", false);
                        }
                    } catch (InvocationTargetException exception) {
                        viewData = this.createModelViewData("Failed to work with provided credential", false);
                        logger.error("Invalid target: {}", target);
                    } catch (IllegalAccessException accessEx) {
                        viewData = this.createModelViewData("Failed to call method to manipulate provided credential", false);
                        logger.error("Cannot access the method: {}", methodName);
                    } catch (Exception exception) {
                        viewData = this.createModelViewData("Failed to manipulate provided credential", false);
                        logger.error("Cannot perform {} method", methodName);
                    }
                } else {
                    viewData = this.createModelViewData("Failed to get provided method", false);
                }
            } else {
                viewData = this.createModelViewData("Input is invalid", false);
            }
        }

        viewData.put("active", "credentials");
        model.addAllAttributes(viewData);

        return new ModelAndView("redirect:/home", (ModelMap) model);
    }


}

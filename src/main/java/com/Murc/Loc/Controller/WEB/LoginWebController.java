package com.Murc.Loc.Controller.WEB;

import com.Murc.Loc.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginWebController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(LoginWebController.class);

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletResponse response) {
        try {
            logger.debug("Attempting to authenticate user {}", username);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String token = jwtService.generateToken(userDetails);

                Cookie jwtCookie = new Cookie("jwt", token);
                jwtCookie.setHttpOnly(true);
                jwtCookie.setPath("/");
                response.addCookie(jwtCookie);

                logger.info("User {} authenticated successfully", username);
                return "redirect:/users";
            } else {
                model.addAttribute("error", "Неверное имя пользователя или пароль");
                logger.warn("Authentication failed for user {}", username);
                return "login";
            }
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Неверное имя пользователя или пароль");
            logger.error("Authentication failed for user {}. Error: {}", username, e.getMessage());
            return "login";
        }
    }
}

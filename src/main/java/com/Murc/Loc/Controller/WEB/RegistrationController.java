package com.Murc.Loc.Controller.WEB;

import com.Murc.Loc.Auth.RegisterRequest;
import com.Murc.Loc.Model.Role;
import com.Murc.Loc.Model.User;
import com.Murc.Loc.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegisterRequest registerRequest, Model model) {
        String fileName = null;

        if (!registerRequest.getImage().isEmpty()) {
            fileName = saveFile(registerRequest.getImage());
        }

        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .image(fileName)
                .role(Role.USER)
                .build();

        userService.saveUser(user);

        return "redirect:/login";
    }

    private String saveFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        try {
            Path path = Paths.get("src/main/resources/static/uploads/" + fileName);
            // Убедимся, что директории существуют
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            // Сохраним файл
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при сохранении файла", e);
        }
        return fileName;
    }
}

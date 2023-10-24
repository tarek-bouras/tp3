package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController

public class UserController {

    // Créez une liste pour stocker les utilisateurs (remplacez ArrayList par List si vous le souhaitez).
    private List<User> users = new ArrayList<>();

    // Affiche la liste des utilisateurs.
   
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", users); // Assurez-vous que "users" contient une liste d'utilisateurs
        return "userList"; // Vérifiez que "userList" est le nom de la vue Thymeleaf
    }

    // Affiche le formulaire d'ajout d'utilisateur.
    @GetMapping("/add")
    public String addUserForm(User user) {
        return "userForm";
    }

    // Ajoute un utilisateur à la liste.
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        user.setId((long) (users.size() + 1)); // Génère un nouvel identifiant
        users.add(user);
        return "redirect:/users"; // Redirige vers la liste des utilisateurs
    }

    // Supprime un utilisateur de la liste.
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        return "redirect:/users"; // Redirige vers la liste des utilisateurs
    }
}

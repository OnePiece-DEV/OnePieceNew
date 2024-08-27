package br.dev.onepiece.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.dev.onepiece.Service.UserService;



@Controller

public class LoginController {

	@Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Nome do template Thymeleaf para a página de login
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
    	
        // Verificar se o usuário existe
    	
        if (userService.findUserByUsername(username).isPresent()) {
        	
            // Redirecionar para a página home se o usuário existir
        	
            return new ModelAndView("home"); // Nome do template Thymeleaf para a página inicial
            
        } else {
            // Redirecionar para a página de cadastro se o usuário não existir
        	
            return new ModelAndView("registerUser"); // Nome do template Thymeleaf para a página de cadastro
            
            
        }
    }
    }
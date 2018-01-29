package com.jpaboard.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpaboard.domain.User;
import com.jpaboard.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<User>();
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/loginForm")
    public String loginForm() {
    	return "/user/login";
    }    
    
    @GetMapping("/form")
    public String form() {
    	return "/user/form";
    }

    @PostMapping("/login")
    public String login(String userID, String userPWD, HttpSession session) {
    	User user = userRepository.findByUserID(userID);
    	
    	if (user == null) {
    		System.out.println("Login Fail!");
    		return "redirect:/users/loginForm1";
    	}
    	
    	if (!userPWD.equals(user.getPassword())) {
    		System.out.println("Login Fail!");
    		return "redirect:/users/loginForm2";
    	}
    	
    	System.out.println("Login Success!");
    	session.setAttribute("user", user);
    	
    	return "redirect:/";
    }
    
    @PostMapping("")
    public String create(User user) {
        System.out.println("userID : " + user);
        users.add(user);        
        userRepository.save(user);
        return "redirect:/users";
    }
    
    @GetMapping("")
    public String list(Model model) {
        // template에서 사용자목록을 사용 할 수 있도록 함. 
        //model.addAttribute("users", users);
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }
    
    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model) {
    	User user = userRepository.findOne(id);
    	model.addAttribute("user", user);
    	return "/user/updateForm";
    }
    
    //@PostMapping("/{id}")
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, User newUser) {
    	User user = userRepository.findOne(id);
    	user.update(newUser);
    	userRepository.save(user);
        return "redirect:/users";
    }
    
}

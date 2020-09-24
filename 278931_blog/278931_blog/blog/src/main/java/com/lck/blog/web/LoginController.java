package com.lck.blog.web;

import com.lck.blog.po.User;
import com.lck.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            attributes.addFlashAttribute("message1","成功登入!5秒後自動返回首頁");
            System.out.println(attributes.getFlashAttributes());
            return "ok";
        }else {
            attributes.addFlashAttribute("message","帳號或密碼錯誤");
            return "redirect:/login";
        }
    }
    /*@GetMapping("templates/logout");
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";

    }*/
}

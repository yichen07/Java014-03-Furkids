package com.lck.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/211")
    public String indexPage(){
        return "comment";
    }
    @GetMapping("/article")
    public String indexPage2(){
        return "article";
    }
    @GetMapping
    public String indexPage3() {
        return "index";
    }
    @GetMapping("/213")
    public String indexPage4(){
        return "types-input";
    }
    @GetMapping("/214")
    public String indexPage5(){
        return "types2";
    }
    @GetMapping("/215")
    public String indexPage6(){
        return "index_template";
    }

}

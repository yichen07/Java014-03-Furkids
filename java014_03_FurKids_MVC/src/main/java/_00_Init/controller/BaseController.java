package _00_Init.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
// 回首頁
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}

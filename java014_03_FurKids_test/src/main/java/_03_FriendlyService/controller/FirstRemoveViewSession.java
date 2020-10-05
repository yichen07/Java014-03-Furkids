package _03_FriendlyService.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"nowPage","AllViewConvenience","item","TotalPages"})
@RequestMapping("/_03_FriendlySystem")
public class FirstRemoveViewSession {
	@GetMapping("/ViewSessionStatus_setComplete")
	public String SessionStatus_setComplete(Model model,SessionStatus status) {
		status.setComplete();
		return "redirect:/_03_FriendlySystem/convenience";
		
	}
	
}

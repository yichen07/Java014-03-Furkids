package _05_HealthControl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"loginBean"})
public class PetHealthController {

	@GetMapping("/_05_HealthControl/CalorieCalculation")
	public String CalorieCalculation(Model model) {
		return "_05_HealthControl/CalorieCalculation";
	}

}

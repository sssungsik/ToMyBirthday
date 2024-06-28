package birthday;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "최성식입니다.... " +
                "Devtools란? = ";
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/user/login";
    }
}

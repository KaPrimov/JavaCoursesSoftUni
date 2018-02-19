import org.softuni.summer.api.Controller;
import org.softuni.summer.api.GetMapping;

@Controller
public class HomeController {
    @GetMapping(route = "/")
    public String index() {
        return "template:index";
    }
}

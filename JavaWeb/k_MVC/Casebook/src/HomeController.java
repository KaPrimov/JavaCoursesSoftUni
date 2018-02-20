import org.softuni.summer.api.*;

@Controller
public class HomeController {
    @GetMapping(route = "/")
    public String index() {
        return "template:index";
    }

    @PostMapping(route = "/create")
    public String create() {
        return "text/plain";
    }

    // /delete/1, /delete/2184-asd-241t-vva

    @GetMapping(route = "/delete/{id}/asd/{postId}")
    public String delete(@PathVariable int id, @PathVariable int postId) {
        System.out.println("DELETE ID: " + id);
        System.out.println("POST ID: " + postId);

        return "template:delete";
    }
}

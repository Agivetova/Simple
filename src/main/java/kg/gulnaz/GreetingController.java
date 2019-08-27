package kg.gulnaz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class GreetingController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
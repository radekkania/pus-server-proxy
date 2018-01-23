package lodz.uni.math.proxy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final String INDEX_PAGE = "index";

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return INDEX_PAGE;
    }
}

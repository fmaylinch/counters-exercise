package tech.bts.counters;

import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CounterController {

    private CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewCounters() throws IOException {

        Collection<Counter> counters = counterService.getAllCounters();

        Map<String, Object> values = new HashMap<>();
        values.put("counters", counters);

        Template template = HandlebarsUtil.compile("counters");

        return template.apply(values);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public void addCounter(HttpServletResponse response) throws IOException {

        counterService.addCounter();
        response.sendRedirect("/");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/increment")
    public void increment(HttpServletResponse response, @PathVariable int id) throws IOException {

        counterService.increment(id, 1);
        response.sendRedirect("/");
    }
}

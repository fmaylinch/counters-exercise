package tech.bts.counters;

import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public String viewCounters() throws IOException {

        Collection<Counter> counters = counterService.getAllCounters();

        Map<String, Object> values = new HashMap<>();
        values.put("counters", counters);

        Template template = HandlebarsUtil.compile("counters");

        return template.apply(values);
    }

    @GetMapping(path = "/add")
    public void addCounter(HttpServletResponse response) throws IOException {

        counterService.addCounter();
        response.sendRedirect("/");
    }

    @GetMapping(path = "/{id}/increment")
    public void increment(HttpServletResponse response, @PathVariable int id) throws IOException {

        counterService.increment(id, 1);
        response.sendRedirect("/");
    }


    // API end-point example

    @GetMapping(path = "/api/counters")
    public Collection<Counter> getCounters() {

        return counterService.getAllCounters();
    }
}

package tech.bts.counters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CounterController {

    private CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String viewCounters() {

        Collection<Counter> counters = counterService.getAllCounters();

        return "Number of counters: <strong>" + counters.size() + "</strong>";
    }
}

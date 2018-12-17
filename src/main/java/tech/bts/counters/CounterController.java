package tech.bts.counters;

import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String viewCounters() throws IOException {

        Collection<Counter> counters = counterService.getAllCounters();

        Map<String, Object> values = new HashMap<>();
        values.put("value", counters.size());

        Template template = HandlebarsUtil.compile("counters");

        return template.apply(values);
    }
}

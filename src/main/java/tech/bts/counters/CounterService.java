package tech.bts.counters;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CounterService {

    private Map<Integer, Counter> counterMap;
    private int nextId;

    public CounterService() {
        counterMap = new HashMap<>();
        nextId = 0;
    }

    public void addCounter() {
        counterMap.put(nextId, new Counter(nextId));
        nextId++;
    }

    public Collection<Counter> getAllCounters() {
        return counterMap.values();
    }

    public void increment(int id, int amount) {

        Counter counter = counterMap.get(id);
        counter.incrementValue( amount );
    }
}

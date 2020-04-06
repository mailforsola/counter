package com.novak.counter;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CounterService {

    private ConcurrentHashMap<String, AtomicLong> counters;

    public CounterService() {
        counters = new ConcurrentHashMap<>();
    }

    public String create() {
        String name = "";
        AtomicLong value = new AtomicLong(0);
        do {
            name = RandomStringUtils.randomAlphanumeric(5, 8);
        } while (counters.putIfAbsent(name, value) != null);
        return name;
    }

    public Long increment(String counter) {
        if (counters.containsKey(counter)) {
            return counters.get(counter).incrementAndGet();
        }
        return null;
    }

    public Long getValue(String counter) {
        if (counters.containsKey(counter)) {
            return counters.get(counter).get();
        }
        return null;
    }

    public Long delete(String counter) {
        return counters.remove(counter).get();
    }

    public Long sum() {
        return counters.values().stream().mapToLong(AtomicLong::longValue).sum();
    }

    public List<String> list() {
        return new ArrayList<String>(counters.keySet());
    }
}

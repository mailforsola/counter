package com.novak.counter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "counters", produces = "application/json")
public class CounterController {

    private final Counter service;

    public CounterController(Counter service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create() {
        return service.create();
    }

    @PutMapping("{counterName}")
    public ResponseEntity<Long> increment(@PathVariable String counterName) {
        Long v = service.increment(counterName);
        return new ResponseEntity<>(v, (v == null) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("{counterName}")
    public ResponseEntity<Long> getValue(@PathVariable String counterName) {
        Long v = service.getValue(counterName);
        return new ResponseEntity<>(v, (v == null) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @DeleteMapping("{counterName}")
    public ResponseEntity<Long> delete(@PathVariable String counterName) {
        Long v = service.delete(counterName);
        return new ResponseEntity<>(v, (v == null) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping
    public List<String> list() {
        return service.list();
    }

    @GetMapping ("/sum")
    public Long sum() {
        return service.sum();
    }

}

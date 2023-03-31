package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.ReturnRequest;
import edu.iu.c322.orderservice.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository repository;

    public OrderController (OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public List<Order> findByCustomerId(@PathVariable int id) {
        return repository.findByCustomerId(id);
    }

    @PostMapping
    public int create(@Valid @RequestBody Order order) {
        return repository.create(order);
    }

    @PutMapping("/return")
    public void update (@RequestBody ReturnRequest returnRequest) {
        repository.update(returnRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.delete(id);
    }
}

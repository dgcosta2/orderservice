package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.model.Item;
import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.ReturnRequest;
import edu.iu.c322.orderservice.repository.AddressRepository;
import edu.iu.c322.orderservice.repository.ItemRepository;
import edu.iu.c322.orderservice.repository.OrderRepository;
import edu.iu.c322.orderservice.repository.ReturnRequestRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    /*
     * Needs a table for Orders, Items, Payment, Address, Return Requests
     */

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private AddressRepository addressRepository;
    private ReturnRequestRepository returnRequestRepository;

    public OrderController(OrderRepository orderRepository,
                           ItemRepository itemRepository,
                           AddressRepository addressRepository,
                           ReturnRequestRepository returnRequestRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.addressRepository = addressRepository;
        this.returnRequestRepository = returnRequestRepository;
    }

    @GetMapping("/{id}")
    public List<Order> findByCustomerId(@PathVariable int id) {
        return orderRepository.findByCustomerId(id);
    }

    @GetMapping("/order/{id}")
    public Order findByOrderId(@PathVariable int id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    public int create(@Valid @RequestBody Order order) {
        for (int i = 0; i < order.getItems().size(); ++i) {
            order.getItems().get(i).setOrder(order);
        }
        order.setOrderPlaced(LocalDate.now());
        Order o = orderRepository.save(order);
        return o.getId();
    }

    @PutMapping("/return")
    public void update (@RequestBody ReturnRequest returnRequest) {
        returnRequestRepository.save(returnRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(value -> value.setStatus("cancelled"));
        orderRepository.save(order.get());
    }

    @DeleteMapping
    public void deleteAll() {
        orderRepository.deleteAll();
    }
}

package edu.iu.c322.orderservice.model;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private int id;
    private int customerId;
    private double total;
    @Valid
    private Address shippingAddress;
    @Valid
    private List<Item> items;
    private Payment payment;
    private List<ReturnRequest> returnRequests;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<ReturnRequest> getReturnRequests() {
        return returnRequests;
    }

    public void setReturnRequests(List<ReturnRequest> returnRequests) {
        this.returnRequests = returnRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && customerId == order.customerId && Double.compare(order.total, total) == 0 && shippingAddress.equals(order.shippingAddress) && items.equals(order.items) && payment.equals(order.payment) && returnRequests.equals(order.returnRequests) && status.equals(order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, total, shippingAddress, items, payment, returnRequests, status);
    }
}

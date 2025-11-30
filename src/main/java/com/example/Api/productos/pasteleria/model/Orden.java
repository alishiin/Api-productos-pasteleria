package com.example.Api.productos.pasteleria.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;

    private Double total;


    @ElementCollection
    @CollectionTable(name = "orden_items", joinColumns = @JoinColumn(name = "orden_id"))
    @Column(name = "item")
    private List<String> items;

    public Orden() {}

    public Orden(String username, Double total, List<String> items) {
        this.username = username;
        this.total = total;
        this.items = items;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public Double getTotal() { return total; }
    public List<String> getItems() { return items; }

    public void setUsername(String username) { this.username = username; }
    public void setTotal(Double total) { this.total = total; }
    public void setItems(List<String> items) { this.items = items; }
}

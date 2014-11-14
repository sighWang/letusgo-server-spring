package com.thoughtworks.letusgo.domain;

public class CartItem {

    private int id;
    private Item item;
    private double number;
    public CartItem(Item item, double number) {
        this.item = item;
        this.number = number;
    }

    public CartItem() {

    }

    public Item getItem() {
        return item;
    }

    public double getNumber() {
        return number;
    }

    public int findItemId() {
        return getItem().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public void setNumber(double number) {
        this.number = number;
    }
}

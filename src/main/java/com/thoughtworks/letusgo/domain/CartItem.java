package com.thoughtworks.letusgo.domain;

public class CartItem {

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

    public int getItemId() {
        return getItem().getId();
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setNumber(double number) {
        this.number = number;
    }
}

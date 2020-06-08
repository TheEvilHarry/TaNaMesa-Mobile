package com.example.tanamesaapp.models;

import java.util.List;

public class Table {
    private int id;
    private List<Product> orders;
    private float totalTable=0;
    private boolean waitingOrder=false;
    private boolean available=true;
    private boolean needingWaiter=false;

    public int getId() {
        return id;
    }

    public List<Product> getOrders() {
        return orders;
    }

    public float getTotalTable() {
        return totalTable;
    }

    public boolean isWaitingOrder() {
        return waitingOrder;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isNeedingWaiter() {
        return needingWaiter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrders(List<Product> orders) {
        this.orders = orders;
    }

    public void setTotalTable(float totalTable) {
        this.totalTable = totalTable;
    }

    public void setWaitingOrder(boolean waitingOrder) {
        this.waitingOrder = waitingOrder;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNeedingWaiter(boolean needingWaiter) {
        this.needingWaiter = needingWaiter;
    }
}

package com.example.tanamesaapp.models;

import java.util.List;

public class Table {
    private long id;
    private List<Order> tableOrders;
    private float totalTable=0;
    private boolean available=true;
    private boolean needingWaiter=false;

    public long getId() {
        return id;
    }

    public List<Order> getTableOrders() {
        return tableOrders;
    }

    public float getTotalTable() {
        this.calculateTableTotal();
        return totalTable;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isNeedingWaiter() {
        return needingWaiter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTableOrders(List<Order> tableOrders) {
        this.tableOrders = tableOrders;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNeedingWaiter(boolean needingWaiter) {
        this.needingWaiter = needingWaiter;
    }

    public void calculateTableTotal(){
        float totalOrder=0;
        float total=0;

        if(tableOrders.isEmpty()){
            totalTable=0;
        }
        else{
            for(Order order:tableOrders){
                order.calculateOrderTotal();
                totalOrder = order.getOrderTotal();
                total+=totalOrder;
            }
            totalTable=total;
        }
    }

    public void addOrderToTable(Order order){
        List<Order> tableOrders = getTableOrders();
        tableOrders.add(order);
        setTableOrders(tableOrders);
        this.calculateTableTotal();
    }
}

package com.fpt.entity;


import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Long,CartItem> items = new HashMap<>();

    public HashMap<Long, CartItem> getItems() {
        return items;
    }

    public void setItems(HashMap<Long, CartItem> items) {
        this.items = items;
    }

<<<<<<< HEAD
    public void setItems(long id, CartItem item) { this.items.put(id,item);
    }
=======
>>>>>>> 7099d85cc537bd94fb2b5f2347474f2cb7ab7055
}

package com.company.oop;

import java.util.ArrayList;

public class Board {
    private int totalItems = 0;
    private final ArrayList<BoardItem> items;

    Board() {
        items = new ArrayList<>();
    }

    public int totalItems(){
        return totalItems;
    }

    public void addItem(BoardItem boardItem){
        if (items.contains(boardItem)){
            throw new IllegalArgumentException("Item already in the list");
        }
        items.add(boardItem);
        totalItems++;
    }
}

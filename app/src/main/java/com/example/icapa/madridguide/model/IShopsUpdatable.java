package com.example.icapa.madridguide.model;


public interface IShopsUpdatable {
    void add(Shop shop);
    void delete(Shop shop);
    void edit(Shop newShop,long index);


}

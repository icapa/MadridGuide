package com.example.icapa.madridguide.model;

import java.util.List;


public interface IShopsIterable {
    long size();
    Shop get(long index);
    List<Shop> allShops();
}

package com.example.icapa.madridguide.views;




public interface OnElementClick<T> {
    public abstract void clickedOn(final T shop, final int position);
}

package com.example.icapa.madridguide.model;


import java.io.Serializable;

public  class AnyTopic implements Serializable{
    private long id;
    private String name;
    private String imageUrl;
    private String logoImgUrl;
    private String address;
    private String url;
    private String description;
    private float latitude;
    private float longitude;
    private String type;


    public AnyTopic(long id, String name) {
        this.id = id;
        this.name = name;
    }
    // Esto se hace para obligar a que el constructor tenga que tener parámetro

    public AnyTopic() {
        this.id=-1;
        this.name = null;
    }


    public long getId() {
        return id;
    }

    public AnyTopic setId(long id) {

        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AnyTopic setName(String name) {

        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AnyTopic setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public AnyTopic setLogoImgUrl(String logoImgUrl) {

        this.logoImgUrl = logoImgUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AnyTopic setAddress(String address) {

        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public AnyTopic setUrl(String url) {

        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AnyTopic setDescription(String description) {

        this.description = description;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public AnyTopic setLatitude(float latitude) {

        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public AnyTopic setLongitude(float longitude) {

        this.longitude = longitude;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.example.icapa.madridguide.manager.net;


import com.google.gson.annotations.SerializedName;

public class ShopEntity {

    @SerializedName("id") private Long id;
    @SerializedName("name") private String name;
    @SerializedName("img") private String img;
    @SerializedName("logo_img") private String logoImg;
    @SerializedName("address") private String address;
    @SerializedName("url") private String url;
    @SerializedName("description_es") private String descriptionEs;
    @SerializedName("description_en") private String descriptionEn;
    @SerializedName("latitude") private long latitude;
    @SerializedName("longitude") private long longitude;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getDescriptionEs() {
        return descriptionEs;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }


    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}

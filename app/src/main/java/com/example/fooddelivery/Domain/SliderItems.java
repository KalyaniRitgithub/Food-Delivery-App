package com.example.fooddelivery.Domain;

public class SliderItems {
    private String image;

    // No-argument constructor required by Firebase
    public SliderItems() {
        // Default constructor for Firebase
    }

    // Constructor with parameters (optional)
    public SliderItems(String image) {
        this.image = image;
    }

    // Getter and Setter methods
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

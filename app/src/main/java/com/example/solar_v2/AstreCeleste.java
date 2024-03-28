package com.example.solar_v2;

public class AstreCeleste {
    private int id;
    private String name;
    private int size;
    private String color;
    private int state;
    private String imageName;
    private int x;
    private int y;
    private boolean tocado = false;
    public AstreCeleste() {
    }

    public AstreCeleste(int id, String name, int size, String color, int state, String imageName, int x, int y) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.state = state;
        this.imageName = imageName;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTocado() {
        return tocado;
    }

    public void setTocado(boolean tocado) {
        this.tocado = tocado;
    }

}

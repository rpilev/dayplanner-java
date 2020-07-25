package com.petproject.custom.pagination;

public class CustomPageable {
    int size;
    int number;
    Long totalElements;

    public CustomPageable(int number, int size, Long totalElements) {
        this.size = size;
        this.number = number;
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}

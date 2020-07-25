package com.petproject.custom.pagination;

public class CustomPageable {
    int size;
    int page;
    Long totalElements;

    public CustomPageable(int number, int size, Long totalElements) {
        this.size = size;
        this.page = number;
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}

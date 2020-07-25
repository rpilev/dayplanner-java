package com.petproject.custom.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CustomPage<T> {
    List<T> content;
    CustomPageable pageable;
    Sort sort;

    public CustomPage(Page<T> pages) {
        this.content = pages.getContent();
        this.sort = pages.getSort();
        this.pageable = new CustomPageable(
            pages.getNumber(),
            pages.getSize(),
            pages.getTotalElements()
        );
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public CustomPageable getPageable() {
        return pageable;
    }

    public void setPageable(CustomPageable pageable) {
        this.pageable = pageable;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}

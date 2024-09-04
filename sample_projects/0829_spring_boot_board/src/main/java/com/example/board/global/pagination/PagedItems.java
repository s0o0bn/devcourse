package com.example.board.global.pagination;

import lombok.Getter;

import java.util.List;

import static com.example.board.global.pagination.Page.PAGE_COUNT;
import static com.example.board.global.pagination.Page.PAGE_SIZE;

@Getter
public class PagedItems<T> {
    private final List<T> items;
    private final int startPage;
    private final int lastPage;
    private final int totalPage;

    public PagedItems(List<T> items, int count, int page) {
        this.items = items;
        this.totalPage = (count + PAGE_SIZE - 1) / PAGE_SIZE;
        this.startPage = (page - 1) / PAGE_SIZE * PAGE_SIZE + 1;
        this.lastPage = Math.min(totalPage, startPage + (PAGE_COUNT - 1));
    }
}

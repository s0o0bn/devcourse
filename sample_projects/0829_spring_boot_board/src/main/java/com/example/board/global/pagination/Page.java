package com.example.board.global.pagination;

public record Page(int page) {
    public static final int PAGE_SIZE = 10;
    public static final int PAGE_COUNT = 10;

    public int getOffset() {
        return (page - 1) * PAGE_SIZE / PAGE_SIZE;
    }
}

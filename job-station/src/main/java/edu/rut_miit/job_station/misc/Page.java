package edu.rut_miit.job_station.misc;

import java.util.List;

public class Page<T> {
    private List<T> payload;
    private int currentPage;
    private int perPage;
    private int totalPages;

    public static <T> Page<T> of(List<T> payload, Pageable pageable, int totalSize) {
        return new Page<>(
            payload,
            pageable.getPage(),
            pageable.getLimit(),
            PaginationUtils.calculateTotalPages(totalSize, pageable.getLimit())
        );
    }

    public Page(List<T> payload, int currentPage, int perPage, int totalPages) {
        this.payload = payload;
        this.currentPage = currentPage;
        this.perPage = perPage;
        this.totalPages = totalPages;
    }

    public List<T> getPayload() {
        return payload;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

package edu.rut_miit.job_station.misc;

public class Pageable {
    private int page;
    private int limit;

    public static Pageable createDefault() {
        return new Pageable(PaginationUtils.DEFAULT_PAGE, PaginationUtils.DEFAULT_LIMIT);
    }

    public Pageable(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

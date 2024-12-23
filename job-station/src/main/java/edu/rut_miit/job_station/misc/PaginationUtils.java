package edu.rut_miit.job_station.misc;

public class PaginationUtils {
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_LIMIT = 20;

    public static Pageable parse(String page, String limit) {
        Integer parsedPage = page != null ? Integer.parseInt(page) : DEFAULT_PAGE;
        Integer parsedLimit = limit != null ? Integer.parseInt(limit) : DEFAULT_LIMIT;

        int normalizedPage = DEFAULT_PAGE;
        int normalizedLimit = DEFAULT_LIMIT;

        if (parsedPage != null && parsedPage > 0) {
            normalizedPage = parsedPage;
        }

        if (parsedLimit != null && parsedLimit > 0) {
            normalizedLimit = parsedLimit;
        }

        return new Pageable(normalizedPage, normalizedLimit);
    }

    public static int calculateTotalPages(int dataSize, int perPage) {
        return (int)Math.ceil((double)dataSize / (double)perPage);
    }
}

package com.example.team16project.util;

public class PaginationUtil {
    public static int PageSize = 12;
    private static int pageCriteria = 5;

    public static int calculateStartIndex(int currentPage) {

        if(currentPage % pageCriteria == 0) {

            return currentPage - pageCriteria + 1;
        }

        return (currentPage/pageCriteria) * pageCriteria + 1;
    }

    public static int calculateEndIndex(int currentPage, int totalPages) {

        int endIdx = (currentPage/pageCriteria) * pageCriteria + pageCriteria;

        if(currentPage % pageCriteria == 0) {

            return currentPage;
        }

        return (endIdx > totalPages) ? totalPages : endIdx;
    }
}

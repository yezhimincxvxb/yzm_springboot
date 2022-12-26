package com.yzm.mybatis.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<E> implements Serializable {
    private static final long serialVersionUID = -8093992033805880632L;
    private int startIndex = 0;
    private int pageSize = 2;
    private int currentPage;
    private int totalPage;
    private int totalCount;
    private List<E> result = Collections.emptyList();

    public Page(int startIndex, int pageSize) {
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        startIndex = (getCurrentPage() - 1) * getPageSize();
        if (startIndex < 0) {
            startIndex = 0;
        }
        return startIndex;
    }

    public int getTotalPage() {
        return (int) Math.ceil(totalCount * 1.0 / pageSize);
    }

    public int getCurrentPage() {
        if (currentPage <= 0)
            currentPage = 1;
        if (currentPage > getTotalPage())
            currentPage = getTotalPage();
        return currentPage;
    }

}
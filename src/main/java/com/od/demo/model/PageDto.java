package com.od.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDto<T> {
    private List<T> content;

    private Long totalItems;

    private Integer totalPages;

    private Integer pageSize;

    private Integer pageNumber;

    private Boolean isFirstPage;

    private Boolean isLastPage;
}

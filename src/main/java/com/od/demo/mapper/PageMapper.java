package com.od.demo.mapper;

import com.od.demo.model.PageDto;

import java.util.List;
import org.springframework.data.domain.Page;
public class PageMapper {
    public static <T, U> PageDto<T> map(List<T> content, Page<U> page) {
        return PageDto.<T>builder()
                .content(content)
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageSize(page.getNumberOfElements())
                .pageNumber(page.getNumber())
                .build();
    }
}

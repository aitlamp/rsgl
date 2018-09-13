package com.atlp.rsgl.common.data;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * 分页模版
 *
 * @author ctc
 * @date 2018年8月24日 21:26:59
 */
@Data
public class PageModel {
    private int offset; // 当前页偏移数
    private int limit; // 每页记录数
    private int page; // 当前页数

    public int getPage() {
        return offset / limit;
    }
}

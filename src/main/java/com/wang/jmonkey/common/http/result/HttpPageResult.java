package com.wang.jmonkey.common.http.result;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 列表返回结果对象
 * @Auther: HeJiawang
 * @Date: 2018/6/29
 */
@Data
@Accessors(chain = true)
public class HttpPageResult<T> {

    private int code;
    private String message;
    private Boolean isSuccess = true;

    private List<T> rows;
    private int pageIndex;
    private int pageSize;
    private long total;

    public HttpPageResult(Page<T> page) {
        this.code = 0;

        this.rows = page.getRecords();
        this.total = page.getTotal();
        this.pageIndex = page.getCurrent();
        this.pageSize = page.getSize();
    }
}

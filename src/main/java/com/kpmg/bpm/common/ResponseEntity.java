package com.kpmg.bpm.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author lucasliang
 * @version 0.0.1-SNAPSHOT
 * @description: 后端响应实体类
 * @date 08/06/2018 10:11 下午
 */
public class ResponseEntity implements Serializable {
    private static final long serialVersionUID = 209294660529916793L;
    //z总页数
    private long total;
    //返回记录数
    private List rows;
    //总记录数
    private long records;

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

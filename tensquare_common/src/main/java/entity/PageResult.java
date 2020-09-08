package entity;

import java.util.List;

/**
 * @Author: LvDuanMing
 * @Date: 2020-05-30 14:31
 * @Description: 用于返回分页结果
 */
public class PageResult<T> {
    //总数
    private Long total;

    //行数
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

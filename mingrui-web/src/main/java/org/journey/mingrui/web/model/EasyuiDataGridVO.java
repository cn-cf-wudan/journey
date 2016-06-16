package org.journey.mingrui.web.model;

/**
 * @author wudan-mac
 * @ClassName: EasyuiDataGridVO
 * @ClassNameExplain: easyUI 数据表格基础返回参数对象
 * @Description:
 * @date 16/6/3 下午6:24
 */
public class EasyuiDataGridVO {

    //总记录数
    private Long total;

    //数据对象
    private Object rows;

    @Override
    public String toString() {
        return "EasyuiDataGridVO{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}

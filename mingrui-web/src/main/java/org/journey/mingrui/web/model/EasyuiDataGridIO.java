package org.journey.mingrui.web.model;

/**
 * @author wudan-mac
 * @ClassName: EasyuiDataGridIO
 * @ClassNameExplain: easyUI 数据表格基础入参对象
 * @Description:
 * @date 16/6/3 下午6:24
 */
public class EasyuiDataGridIO {

    private Integer page = 1;

    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyuiDataGridIO{" +
                "page=" + page +
                ", rows=" + rows +
                '}';
    }
}

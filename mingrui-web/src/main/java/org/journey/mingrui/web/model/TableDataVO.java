package org.journey.mingrui.web.model;

/**
 * @author wudan-mac
 * @ClassName: TableDataVO
 * @ClassNameExplain:
 * @Description:
 * @date 16/6/13 下午3:02
 */
public class TableDataVO {

    private Integer draw = 0;

    private Integer recordsTotal = 0;

    private Integer recordsFiltered = 0;

    private Object data = new Object();

    private String error;

    @Override
    public String toString() {
        return "TableDataVO{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

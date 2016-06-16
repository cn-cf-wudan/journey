package org.journey.mingrui.web.model;

/**
 * @author wudan-mac
 * @ClassName: TableDataIO
 * @ClassNameExplain:
 * @Description:
 * @date 16/6/13 下午3:01
 */
public class TableDataIO {

    private Integer draw;

    private Integer start;

    private Integer length;

    private String searchStr;

    private String orderCloumn;

    private String orderDir = "asc";

    @Override
    public String toString() {
        return "TableDataIO{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", searchStr='" + searchStr + '\'' +
                ", orderCloumn='" + orderCloumn + '\'' +
                ", orderDir='" + orderDir + '\'' +
                '}';
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getOrderCloumn() {
        return orderCloumn;
    }

    public void setOrderCloumn(String orderCloumn) {
        this.orderCloumn = orderCloumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }
}

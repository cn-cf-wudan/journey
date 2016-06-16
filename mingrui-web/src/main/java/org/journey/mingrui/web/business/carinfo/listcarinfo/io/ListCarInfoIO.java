package org.journey.mingrui.web.business.carinfo.listcarinfo.io;

import org.journey.mingrui.web.model.EasyuiDataGridIO;

public class ListCarInfoIO extends EasyuiDataGridIO{

    private String model;

    private String engine;

    @Override
    public String toString() {
        return "ListCarInfoIO{" +
                "model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

}
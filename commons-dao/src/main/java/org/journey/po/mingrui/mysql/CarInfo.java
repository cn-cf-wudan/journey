package org.journey.po.mingrui.mysql;

public class CarInfo {
    private Integer id;

    private String model;

    private String modelName;

    private String modelShort;

    private String carFactoty;

    private String carVin;

    private String engine;

    private String displacement;

    private String power;

    private String batch;

    private String engineFactory;

    private String punchMachine;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelShort() {
        return modelShort;
    }

    public void setModelShort(String modelShort) {
        this.modelShort = modelShort == null ? null : modelShort.trim();
    }

    public String getCarFactoty() {
        return carFactoty;
    }

    public void setCarFactoty(String carFactoty) {
        this.carFactoty = carFactoty == null ? null : carFactoty.trim();
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin == null ? null : carVin.trim();
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine == null ? null : engine.trim();
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement == null ? null : displacement.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getEngineFactory() {
        return engineFactory;
    }

    public void setEngineFactory(String engineFactory) {
        this.engineFactory = engineFactory == null ? null : engineFactory.trim();
    }

    public String getPunchMachine() {
        return punchMachine;
    }

    public void setPunchMachine(String punchMachine) {
        this.punchMachine = punchMachine == null ? null : punchMachine.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
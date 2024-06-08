package dk.vv.automobile.dtos;

import java.math.BigDecimal;

public class PredictionDTO {
    private String make;

    private String model;

    private int modelYear;

    private int Reg;

    private String gearType;
    private int kilometers;

    private String fuelType;

    private String horsePower;

    private BigDecimal result;

    private String numberplate;

    public PredictionDTO() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getReg() {
        return Reg;
    }

    public void setReg(int reg) {
        Reg = reg;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    @Override
    public String toString() {
        return "PredictionDTO{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", modelYear=" + modelYear +
                ", Reg=" + Reg +
                ", gearType='" + gearType + '\'' +
                ", kilometers=" + kilometers +
                ", fuelType='" + fuelType + '\'' +
                ", horsePower='" + horsePower + '\'' +
                ", result=" + result +
                '}';
    }
}

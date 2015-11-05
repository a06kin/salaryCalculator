package me.aaa.salaryCalculator.model;

import java.io.Serializable;

public class TaxDetails implements Serializable {

    double overall;

    double net;

    double personalIncomeTax;

    double employeeSIC;

    double employerSIC;

    double SIC;

    double totalTaxes;

    public double getOverall() {
        return overall;
    }

    public void setOverall(double overall) {
        this.overall = overall;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }

    public double getPersonalIncomeTax() {
        return personalIncomeTax;
    }

    public void setPersonalIncomeTax(double personalIncomeTax) {
        this.personalIncomeTax = personalIncomeTax;
    }

    public double getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(double totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public double getEmployeeSIC() {
        return employeeSIC;
    }

    public void setEmployeeSIC(double employeeSIC) {
        this.employeeSIC = employeeSIC;
    }

    public double getEmployerSIC() {
        return employerSIC;
    }

    public void setEmployerSIC(double employerSIC) {
        this.employerSIC = employerSIC;
    }

    public double getSIC() {
        return SIC;
    }

    public void setSIC(double SIC) {
        this.SIC = SIC;
    }
}

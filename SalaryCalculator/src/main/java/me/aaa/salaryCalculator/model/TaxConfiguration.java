package me.aaa.salaryCalculator.model;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:TaxConfiguration.properties")
public interface TaxConfiguration extends Config {
    //ER - employer
    //EE - employee

    @Key("Personal.Income.Tax.Rate")
    double PIT_R();

    @Key("Employer.Social.Insurance.Contribution.Rate")
    double EE_SIC_R();

    @Key("Employee.Social.Insurance.Contribution.Rate")
    double ER_SIC_R();

    @Key("Untaxed.Minimum.EUR")
    double UM();

    @Key("Relief.For.Dependents.EUR")
    double RFD();

    @Key("Business.Risk.Fee.EUR")
    double BRF();

}

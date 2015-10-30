package me.aaa.salaryCalculator;

import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.*;

public class Main implements SparkApplication {
    public static double PIT = 0.23; //Personal Income Tax
    public static double FMN = 0.1005; //Fucking Magic number
    public static double FMN2 = 75; //Fucking Magic number 2
    public static double RFD = 165; //Relief for dependents

    @Override
    public void init() {
        get("/", (req, res) -> new ModelAndView(null, "index.mustache"), new MustacheTemplateEngine());

        post("/overall/:overall/dependents/:dependents", (request, response) ->
                calculateNet(request.params(":overall"), request.params(":dependents")));
    }

    public double calculateNet(String overallStr, String dependentsStr){
        try {
            double overall = Double.parseDouble(overallStr);
            int dependents = Integer.parseInt(dependentsStr);
            double social = overall * FMN;
            double IIN = (overall - social - FMN2) * PIT;
            //TODO: dependents * RFD
            return overall - social - IIN;
        }catch (NumberFormatException e){
            return 0f;
        }
    }
}

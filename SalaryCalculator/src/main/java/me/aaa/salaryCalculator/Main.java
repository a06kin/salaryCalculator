package me.aaa.salaryCalculator;

import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.*;

public class Main implements SparkApplication {
    @Override
    public void init() {
        get("/", (req, res) -> new ModelAndView(null, "index.mustache"), new MustacheTemplateEngine());

        post("/overall/:overall", (request, response) ->
                calculateNet(request.params(":overall")));
    }

    public double calculateNet(String overallStr){
        try {
            double overall = Double.parseDouble(overallStr);
            double social = overall * 0.1005;
            double IIN = (overall - social - 75) * 0.23;
            return overall - social - IIN;
        }catch (NumberFormatException e){
            return 0f;
        }
    }
}

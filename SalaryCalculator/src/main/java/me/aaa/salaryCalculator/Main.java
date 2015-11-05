package me.aaa.salaryCalculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.aaa.salaryCalculator.model.TaxConfiguration;
import me.aaa.salaryCalculator.model.TaxDetails;
import org.aeonbits.owner.ConfigFactory;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

public class Main implements SparkApplication {
    private ObjectMapper JSON = new ObjectMapper();

    @Override
    public void init() {
        TaxConfiguration taxConfiguration = ConfigFactory.create(TaxConfiguration.class);

        get("/", (req, res) -> new ModelAndView(null, "index.mustache"), new MustacheTemplateEngine());

        post("/overall/:overall/dependents/:dependents",
                (request, response) ->
                        JSON.writeValueAsString(calculate(request.params(":overall"),
                                                          request.params(":dependents"),
                                                          taxConfiguration))
        );

        exception(NumberFormatException.class, (e, request, response) -> {
            response.status(400);
            response.body("Bad Request");
        });
    }

    public TaxDetails calculate(String overallStr, String dependentsStr, TaxConfiguration taxConfiguration) {
        try {
            TaxDetails result = new TaxDetails();

            double overall = Double.parseDouble(overallStr);
            result.setOverall(overall);
            int dependents = Integer.parseInt(dependentsStr);
            double personalIncomeTax = taxConfiguration.PIT_R()
                    * (overall - taxConfiguration.ER_SIC_R() * overall
                    - (taxConfiguration.UM() + taxConfiguration.RFD() * dependents));

            if(personalIncomeTax > 0){
                result.setPersonalIncomeTax(personalIncomeTax);
            }

            double net = overall - (taxConfiguration.ER_SIC_R() * overall) - personalIncomeTax;
            result.setNet(net);

            double employeeSIC = overall * taxConfiguration.EE_SIC_R();
            result.setEmployeeSIC(employeeSIC);

            double employerSIC = overall * taxConfiguration.ER_SIC_R();
            result.setEmployerSIC(employerSIC);

            double SIC = employeeSIC + employerSIC;
            result.setSIC(SIC);

            double totalTax = SIC + personalIncomeTax + taxConfiguration.BRF();
            result.setTotalTaxes(totalTax);

            return result;
        } catch (NumberFormatException e) {
            return new TaxDetails();
        }
    }
}

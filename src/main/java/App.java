import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/form", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/result", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      ChangeMachine changeMachine = new ChangeMachine();
      Float total = Float.parseFloat(request.queryParams("total"));
      changeMachine.setQuarters(total);
      int quarters = changeMachine.getQuarters();
      System.out.println(quarters);
      int dimes = changeMachine.getDimes();
      int nickels = changeMachine.getNickels();
      int pennies = changeMachine.getPennies();

      model.put("total", total);
      model.put("quarters", quarters);
      model.put("dimes", dimes);
      model.put("nickels", nickels);
      model.put("pennies", pennies);

      model.put("template", "templates/result.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}

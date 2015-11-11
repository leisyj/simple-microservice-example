package credera.jeremyleisy.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import com.codahale.metrics.annotation.Timed;

@Path("/math-engine")
@Produces(MediaType.APPLICATION_JSON)

public class MathSolverResource
{
   private final Random random = new Random();

   @POST
   @Timed
   @Path("/add")
   public String addNumbers(List<Double> numbers)
   {
      return String.valueOf(numbers.stream().mapToDouble(Double::doubleValue).sum());
   }

   @POST
   @Timed
   @Path("/curve-fit")
   public String fitCurve(List<Double> numbers)
   {
      random.nextDouble();
      final WeightedObservedPoints obs = new WeightedObservedPoints();
      numbers.stream().forEach(pt -> obs.add(random.nextDouble(), pt));

      // Instantiate a third-degree polynomial fitter.
      final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(6);

      // Retrieve fitted parameters (coefficients of the polynomial function).
      final double[] coeff = fitter.fit(obs.toList());

      return Arrays.toString(coeff);
   }
}

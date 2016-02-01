package credera.jeremyleisy.resources;

import java.util.Arrays;
import java.util.stream.IntStream;

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
   @POST
   @Timed
   @Path("/add")
   public String addNumbers(double numbers[])
   {
      return String.valueOf(Arrays.stream(numbers).sum());
   }

   @POST
   @Timed
   @Path("/curve-fit")
   public double[] fitCurve(double numbers[])
   {
      final WeightedObservedPoints obs = new WeightedObservedPoints();
      IntStream.range(0, numbers.length).forEach(idx -> obs.add(idx, numbers[idx]));

      // Instantiate a nth-degree polynomial fitter.
      final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(10);

      // Retrieve fitted parameters (coefficients of the polynomial function).
      return fitter.fit(obs.toList());
   }
}

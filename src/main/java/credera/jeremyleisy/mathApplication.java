package credera.jeremyleisy;

import credera.jeremyleisy.resources.MathSolverResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class mathApplication extends Application<mathConfiguration>
{

   public static void main(final String[] args) throws Exception
   {
      new mathApplication().run(args);
   }

   @Override
   public String getName()
   {
      return "math";
   }

   @Override
   public void initialize(final Bootstrap<mathConfiguration> bootstrap)
   {
      // TODO: application initialization
   }

   @Override
   public void run(final mathConfiguration configuration,
         final Environment environment)
   {
      final MathSolverResource resource = new MathSolverResource();
      environment.jersey().register(resource);
   }
}

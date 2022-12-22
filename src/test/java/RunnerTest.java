import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "cucumber_step_defs",  // Можно указать несколько пакетов, например, так: glue = {«com.serge.test», «com.serge.hooks»}

// Uncomment for local run
//        tags = "@Smoke",           //указав тег, можем тут же запускать сьют ЛОКАЛЬНО через RunnerTest
//        features = "src/test/resources/features" // Можно указать несколько папок, например: features = {«src/test/features», «src/test/feat»}

// Uncomment for CI/CD (Jenkins) run
        features = "classpath:features",
        plugin = {"pretty", "html:target/cucumber-reports/html_reports"}
)

public class RunnerTest {    //обязательно название класа заканчивается на Test

}
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@Smoke",    //указав тег, можем тут же запускать через TestRunner
        features = "src/test/resources", // Можно указать несколько папок, например: features = {«src/test/features», «src/test/feat»}
        glue = "cucumber_step_defs",  // Можно указать несколько пакетов, например, так: glue = {«ru.savkk.test», «ru.savkk.hooks»}
        plugin = {"pretty", "html:target/cucumber-reports/html_reports"}
)

public class RunnerTest {    //обязательно название класа заканчивается на Test

}
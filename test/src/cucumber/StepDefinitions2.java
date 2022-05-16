package cucumber;

import java.io.File;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;

import org.openrdf.model.Model;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefinitions2 {
    private Model aGraph;
    private RDFMapper aMapper;
    private ClassWithPrimitives aResult;
    private boolean result;

    @Given("IIgraph and a result")
    public void graph_and_a_result() throws Exception {
        aGraph = ModelIO.read(new File(getClass().getResource("/data/primitives.nt").toURI()).toPath());

        aMapper = RDFMapper.create();
    }

    @And("IIresult made from graph")
    public void result_made_from_graph() {
        aResult = aMapper.readValue(aGraph, ClassWithPrimitives.class);
    }

    @When("III ask if Expected should equal to result")
    public void i_ask_if_expected_should_equal_to_result() {
        ClassWithPrimitives aExpected = new ClassWithPrimitives();
        aExpected.setString("str value");
        aExpected.setInt(8);
        aExpected.setURI(java.net.URI.create("urn:any"));
        aExpected.setFloat(4.5f);
        aExpected.setDouble(20.22);
        aExpected.setChar('o');

    }

    @Then("III should get {string}")
    public void expected_should_equal_result(String expectedResult) throws AssertionError {
        assertEquals(result, Boolean.parseBoolean(expectedResult));
    }
}

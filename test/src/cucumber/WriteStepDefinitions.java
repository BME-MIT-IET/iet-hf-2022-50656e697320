package cucumber;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;

import org.openrdf.model.Model;
import org.openrdf.model.impl.SimpleValueFactory;
import org.openrdf.rio.RDFParseException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class WriteStepDefinitions {

    RDFMapper aMapper;
    ClassWithPrimitives aObj;
    Model aGraph;
    Model aExpected;
    private boolean result;

    @Given("An object with primitive values.")
    public void an_object_with_primitive_values_with_an_RDF_mapper() throws Exception {
        aMapper = RDFMapper.create();

        aObj = new ClassWithPrimitives();
        aObj.setString("str value");
        aObj.setInt(8);
        aObj.setURI(java.net.URI.create("urn:any"));
        aObj.setFloat(4.5f);
        aObj.setDouble(20.22);
        aObj.setChar('o');
        aObj.id(SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:3d1c9ece37c3f9ee6068440cf9a383cc"));
    }

    @And("I make a result graph by wirte the object to it.")
    public void i_make_a_result_graph_by_wirte_the_object_to_it() {
        aGraph = aMapper.writeValue(aObj);
    }

    @When("I ask if the expected is equal to the result graph.")
    public void i_ask_if_the_expected_is_equal_to_the_result_graph()
            throws RDFParseException, IOException, URISyntaxException {
        Model aExpected = ModelIO.read(new File(getClass().getResource("/data/primitives.nt").toURI()).toPath());
        result = this.aGraph.equals(aExpected);
    }

    @Then("I should get {string}")
    public void expected_should_equal_result(String expectedResult) throws AssertionError {
        assertEquals(result, Boolean.parseBoolean(expectedResult));
    }
}

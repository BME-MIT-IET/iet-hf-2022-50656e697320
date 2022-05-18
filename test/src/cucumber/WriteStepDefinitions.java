package cucumber;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;
import com.complexible.pinto.RDFMapperTests.ClassWithMixed;

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
    ClassWithPrimitives aObjPrimitive;
    Model aGraph;
    Model aExpected;
    private boolean result;

    //ClassWithPrimitives aChild;
    ClassWithMixed aObjMixed;

    //1.
    @Given("An object with primitive values.")
    public void an_object_with_primitive_values_with_an_RDF_mapper(List <ClassWithPrimitives> expectedResultList) throws Exception {
        aObjPrimitive = expectedResultList.get(0);
        aObjPrimitive.id(SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:3d1c9ece37c3f9ee6068440cf9a383cc"));

    }
//2.
    @Given("An object instance of ClassWithPrimitives and an ClassWithMixed.")
    public void an_object_instance_of_classWithPrimitives_and_an_classWithMixed(List <ClassWithMixed> expectedResultList) throws Exception {
		
        aObjPrimitive=expectedResultList.get(0).getChild();

        aObjMixed = expectedResultList.get(0);
        aObjMixed.id(SimpleValueFactory.getInstance().createIRI("tag:complexible:pinto:45ad04336c95c0be6bba90e4b663da4d"));

    }
    
//1.
    @And("I make a result graph by wirte the object to it.")
    public void i_make_a_result_graph_by_wirte_the_object_to_it() {
        aGraph = RDFMapper.create().writeValue(aObjPrimitive);
    }


//2.
    @And("I make a result graph by wirte the instance of ClassWithMixed to it.")
    public void i_make_a_result_graph_by_wirte_the_instance_of_classWithMixed_to_it() {
         aGraph = RDFMapper.create().writeValue(aObjMixed);
    }


    @When("halo {string}")
    public void i_ask_if_the_expected_is_equal_to_the_result_read_graph_from(String url) throws RDFParseException, IOException, URISyntaxException {
            Model aExpected = ModelIO.read(new File(getClass().getResource(url).toURI()).toPath());
            result = this.aGraph.equals(aExpected);
    }


    @Then("I should get {string}")
    public void expected_primitive_should_equal_result(String expectedResult) throws AssertionError {
        assertEquals(result, Boolean.parseBoolean(expectedResult));
    }

    // @Then("I should get back {string}")
    // public void expected_mixed_should_be_equal_result(String expectedResult) throws AssertionError {
    //     assertEquals(result, Boolean.parseBoolean(expectedResult));
    // }
}

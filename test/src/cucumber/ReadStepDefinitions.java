package cucumber;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests.ClassWithMixed;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;

import org.openrdf.model.Model;
import org.openrdf.model.impl.SimpleValueFactory;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class ReadStepDefinitions {
    private Model graph;
    private RDFMapper mapper;
    private ClassWithPrimitives child;
    private ClassWithPrimitives resultClassWithPrimitives;
    private ClassWithMixed resultClassWithMixed;

    @DataTableType
    public ClassWithPrimitives classWithPrimitives(Map<String, String> entry) {
        ClassWithPrimitives classWithPrimitives = new ClassWithPrimitives();
        classWithPrimitives.setString(entry.get("string"));
        classWithPrimitives.setInt(Integer.parseInt(entry.get("int")));
        classWithPrimitives.setURI(URI.create(entry.get("uri")));
        classWithPrimitives.setFloat(Float.parseFloat(entry.get("float")));
        classWithPrimitives.setDouble(Double.parseDouble(entry.get("double")));
        classWithPrimitives.setChar(entry.get("char").charAt(0));
        return classWithPrimitives;
    }

    @DataTableType
    public ClassWithMixed classWithMixed(Map<String, String> entry) {
        ClassWithMixed classWithMixed = new ClassWithMixed();
        classWithMixed.setChild(child);
        classWithMixed.setString(entry.get("string"));
        return classWithMixed;
    }

    @Given("I have a graph from {string}")
    public void i_have_a_graph_from(String file) throws Exception {
        graph = ModelIO.read(new File(getClass().getResource(file).toURI()).toPath());
    }

    @Given("I have a mapper")
    public void i_have_a_mapper() throws Exception {
        mapper = RDFMapper.create();
    }

    @Given("there is a child")
    public void there_is_a_child(List<ClassWithPrimitives> expectedResultList) {
        assertEquals(1, expectedResultList.size());
        child = expectedResultList.get(0);
    }

    @When("I read an instance of ClassWithPrimitives from the graph")
    public void i_read_an_instance_of_ClassWithPrimitives_from_the_graph() {
        resultClassWithPrimitives = mapper.readValue(graph, ClassWithPrimitives.class);
    }

    @When("I read an instance of ClassWithMixed from the graph")
    public void i_read_an_instance_of_ClassWithMixed_from_the_graph() {
        resultClassWithMixed = mapper.readValue(
            graph,
            ClassWithMixed.class,
            SimpleValueFactory.getInstance()
                .createIRI("tag:complexible:pinto:45ad04336c95c0be6bba90e4b663da4d")
        );
    }

    @Then("I should get an instance of ClassWithPrimitives")
    public void i_should_get_an_instance_of_ClassWithPrimitives(List<ClassWithPrimitives> expectedResultList) {
        assertEquals(1, expectedResultList.size());
        ClassWithPrimitives expectedResult = expectedResultList.get(0);

        assertEquals(expectedResult, resultClassWithPrimitives);
    }

    @Then("I should get an instance of ClassWithMixed")
    public void i_should_get_an_instance_of_ClassWithMixed(List<ClassWithMixed> expectedResultList) {
        assertEquals(1, expectedResultList.size());
        ClassWithMixed expectedResult = expectedResultList.get(0);

        assertEquals(expectedResult, resultClassWithMixed);
    }
}

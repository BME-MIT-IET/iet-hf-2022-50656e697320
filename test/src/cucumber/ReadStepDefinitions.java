package cucumber;

import java.io.File;
import java.util.List;
import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests.ClassWithMixed;
import com.complexible.pinto.RDFMapperTests.ClassWithObjectList;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;

import org.openrdf.model.Model;
import org.openrdf.model.impl.SimpleValueFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class ReadStepDefinitions {
    private Model graph;
    private RDFMapper mapper;
    private ClassWithPrimitives resultClassWithPrimitives;
    private ClassWithMixed resultClassWithMixed;
    private ClassWithObjectList classWithObjectList1;
    private ClassWithObjectList classWithObjectList2;

    @Given("I have a graph from {string}")
    public void i_have_a_graph_from(String file) throws Exception {
        graph = ModelIO.read(new File(getClass().getResource(file).toURI()).toPath());
    }

    @Given("I have a mapper")
    public void i_have_a_mapper() throws Exception {
        mapper = RDFMapper.create();
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
                        .createIRI("tag:complexible:pinto:45ad04336c95c0be6bba90e4b663da4d"));
    }

    @When("I read an instance of ClassWithObjectList from the graph")
    public void i_read_an_instance_of_ClassWithObjectList_from_the_graph() {
        classWithObjectList1 = mapper.readValue(
                graph, ClassWithObjectList.class,
                SimpleValueFactory.getInstance()
                        .createIRI("tag:complexible:pinto:881b2f11232944aeda9ba543e030dcfc"));
    }

    @When("I read an other instance of ClassWithObjectList from the graph")
    public void i_read_an_other_instance_of_ClassWithObjectList_from_the_graph() {
        classWithObjectList2 = mapper.readValue(
                graph, ClassWithObjectList.class,
                SimpleValueFactory.getInstance()
                        .createIRI("tag:complexible:pinto:881b2f11232944aeda9ba543e030dcfc"));
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

    @Then("I should get two equal instance of ClassWithObjectList")
    public void i_should_get_two_equal_instance_of_ClassWithObjectList() {
        assertEquals(classWithObjectList1, classWithObjectList2);
    }
}

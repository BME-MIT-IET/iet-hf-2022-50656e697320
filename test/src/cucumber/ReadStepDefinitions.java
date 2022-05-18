package cucumber;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.RDFMapper;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;

import org.openrdf.model.Model;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class ReadStepDefinitions {
    private Model graph;
    private RDFMapper mapper;
    private ClassWithPrimitives result;

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

    @Given("I have a graph")
    public void i_have_a_graph() throws Exception {
        graph = ModelIO.read(new File(getClass().getResource("/data/primitives.nt").toURI()).toPath());
    }

    @Given("I have a mapper")
    public void i_have_a_mapper() throws Exception {
        mapper = RDFMapper.create();
    }

    @And("I read from the graph")
    public void result_made_from_graph() {
        result = mapper.readValue(graph, ClassWithPrimitives.class);
    }

    @Then("I should get")
    public void i_should_get(List<ClassWithPrimitives> expectedResultList) {
        assertEquals(1, expectedResultList.size());
        ClassWithPrimitives expectedResult = expectedResultList.get(0);

        assertEquals(expectedResult, result);
    }
}

package cucumber;

import java.net.URI;
import java.util.Map;

import com.complexible.pinto.RDFMapperTests.ClassWithMixed;
import com.complexible.pinto.RDFMapperTests.ClassWithPrimitives;

import io.cucumber.java.DataTableType;

public class ParameterTypes {
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
        classWithMixed.setString(entry.get("string"));

        ClassWithPrimitives classWithPrimitives = new ClassWithPrimitives();
        classWithPrimitives.setString(entry.get("child/string"));
        classWithPrimitives.setInt(Integer.parseInt(entry.get("child/int")));
        classWithPrimitives.setURI(URI.create(entry.get("child/uri")));
        classWithPrimitives.setFloat(Float.parseFloat(entry.get("child/float")));
        classWithPrimitives.setDouble(Double.parseDouble(entry.get("child/double")));
        classWithPrimitives.setChar(entry.get("child/char").charAt(0));

        classWithMixed.setChild(classWithPrimitives);

        return classWithMixed;
    }
}
package lc.add2numbers;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.util.Arrays;

public class ListNodeParser implements ArgumentConverter {
    @Override
    public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
        String[] nodes = ((String) o).split("->");

        int[] values = Arrays.stream(nodes).mapToInt(Integer::parseInt).toArray();

        return SolutionTestUtils.buildLinkedList(values);
    }


}

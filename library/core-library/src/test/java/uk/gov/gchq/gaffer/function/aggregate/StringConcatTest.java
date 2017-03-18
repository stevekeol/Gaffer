package uk.gov.gchq.gaffer.function.aggregate;

import org.junit.Before;
import org.junit.Test;
import uk.gov.gchq.gaffer.commonutil.JsonUtil;
import uk.gov.gchq.gaffer.exception.SerialisationException;
import uk.gov.gchq.gaffer.jsonserialisation.JSONSerialiser;
import uk.gov.gchq.koryphe.binaryoperator.BinaryOperatorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StringConcatTest extends BinaryOperatorTest {
    private String state;

    @Before
    public void before() {
        state = null;
    }

    @Test
    public void shouldConcatStringsTogether() {
        // Given
        final StringConcat function = new StringConcat();
        function.setSeparator(";");

        // When
        state = function.apply("1", state);
        state = function.apply("2", state);
        function.apply(null, state);

        // Then
        assertEquals("1;2", state);
    }

    @Test
    public void shouldJsonSerialiseAndDeserialise() throws SerialisationException {
        // Given
        final StringConcat function = new StringConcat();

        // When 1
        final String json = new String(new JSONSerialiser().serialise(function, true));

        // Then 1
        JsonUtil.assertEquals(String.format("{%n" +
                "  \"class\" : \"uk.gov.gchq.gaffer.function.aggregate.StringConcat\",%n" +
                "  \"separator\" : \",\"%n" +
                "}"), json);

        // When 2
        final StringConcat deserialisedAggregator = new JSONSerialiser().deserialise(json.getBytes(), getFunctionClass());

        // Then 2
        assertNotNull(deserialisedAggregator);
    }

    @Override
    protected StringConcat getInstance() {
        return new StringConcat();
    }

    @Override
    protected Class<StringConcat> getFunctionClass() {
        return StringConcat.class;
    }
}

package hr.cc;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void processSingleLine() {

        List<String> lines =
                Solution.process(List.of( new Operation(
                        Action.Split, Type.Class, "FooBar")));

        assertEquals(1, lines.size());
        assertEquals("foo bar",lines.get(0));

    }

    @Test
    void processThreeSplits() {

        List<String> lines =
                Solution.process(List.of(
                        new Operation(Action.Split, Type.Class, "FooBar"),
                        new Operation(Action.Split, Type.Method, "fooBar"),
                        new Operation(Action.Split, Type.Variable, "firstName")
                ));

        assertEquals(3, lines.size());
        assertEquals("foo bar",lines.get(0));
        assertEquals("foo bar",lines.get(1));
        assertEquals("first name",lines.get(2));

    }

    @Test
    void processThreeSplitsWithASingleWordEach() {

        List<String> lines =
                Solution.process(List.of(
                        new Operation(Action.Split, Type.Class, "Person"),
                        new Operation(Action.Split, Type.Method, "execute"),
                        new Operation(Action.Split, Type.Variable, "name")
                ));

        assertEquals(3, lines.size());
        assertEquals("person",lines.get(0));
        assertEquals("execute",lines.get(1));
        assertEquals("name",lines.get(2));

    }

    @Test
    void processThreeCombineWithASingleWordEach() {

        List<String> lines =
                Solution.process(List.of(
                        new Operation(Action.Combine, Type.Class, "Person"),
                        new Operation(Action.Combine, Type.Method, "Execute"),
                        new Operation(Action.Combine, Type.Variable, "Name")
                ));

        assertEquals(3, lines.size());
        assertEquals("Person",lines.get(0));
        assertEquals("execute()",lines.get(1));
        assertEquals("name",lines.get(2));

    }

    @Test
    void processThreeCombines() {

        List<String> lines =
                Solution.process(List.of(
                        new Operation(Action.Combine, Type.Class, "foo Bar"),
                        new Operation(Action.Combine, Type.Method, "Foo Bar"),
                        new Operation(Action.Combine, Type.Variable, "First Name")
                ));

        assertEquals(3, lines.size());
        assertEquals("FooBar",lines.get(0));
        assertEquals("fooBar()",lines.get(1));
        assertEquals("firstName",lines.get(2));

    }

   }
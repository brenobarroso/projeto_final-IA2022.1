package aima.test.unit.search.informed;

import aima.core.environment.map2d.*;
import aima.core.environment.support.ProblemFactory;
import aima.core.search.api.Node;
import aima.core.search.api.Problem;
import aima.core.search.api.SearchForActionsFunction;
import aima.core.search.basic.informed.AStarSearch;
import aima.core.search.basic.informed.RecursiveBestFirstSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.ToDoubleFunction;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InformedSearchTest {

    private static final String A_STAR = "AStarSearch";
    private static final String RBFS = "RecursiveBestFirstSearch";
    @Parameter
    public String searchFunctionName;

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> implementations() {
        return Arrays.asList(new Object[][]{{RBFS}, {A_STAR}});
    }

    public <A, S> List<A> searchForActions(Problem<A, S> problem, ToDoubleFunction<Node<A, S>> hf) {

        SearchForActionsFunction<A, S> searchForActionsFunction;
        if (A_STAR.equals(searchFunctionName)) {
            searchForActionsFunction = new AStarSearch<>(hf);
        } else if (RBFS.equals(searchFunctionName)) {
            searchForActionsFunction = new RecursiveBestFirstSearch<>(hf);
        } else {
            throw new UnsupportedOperationException();
        }
        return searchForActionsFunction.apply(problem);
    }

    @Test
    public void testSimplifiedRoadMapOfPartOfRomania() {
        Map2D map = new SimplifiedRoadMapOfPartOfRomania();
        String initialLocation = SimplifiedRoadMapOfPartOfRomania.ARAD;
        String goal = initialLocation;

        Problem<GoAction, InState> problem = ProblemFactory.getSimplifiedRoadMapOfPartOfRomaniaProblem(initialLocation,
                goal);
        if (A_STAR.equals(searchFunctionName))
            assertEquals(new ArrayList<>(), searchForActions(problem, new Map2DFunctionFactory.StraightLineDistanceHeuristic(map, goal)));
        else
            assertEquals(Arrays.asList((String) null), searchForActions(problem, new Map2DFunctionFactory.StraightLineDistanceHeuristic(map, goal)));

        goal = SimplifiedRoadMapOfPartOfRomania.BUCHAREST;
        problem = ProblemFactory.getSimplifiedRoadMapOfPartOfRomaniaProblem(initialLocation, goal);
        assertEquals(
                Arrays.asList(new GoAction(SimplifiedRoadMapOfPartOfRomania.SIBIU),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.RIMNICU_VILCEA),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.PITESTI),
                        new GoAction(SimplifiedRoadMapOfPartOfRomania.BUCHAREST)),
                searchForActions(problem, new Map2DFunctionFactory.StraightLineDistanceHeuristic(map, goal)));
    }
}

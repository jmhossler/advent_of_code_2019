package advent_of_code;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class DayTwo2019Test {
    private DayTwo2019 classUnderTest;

    @Mock
    private DayTwoInput mockInput;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        classUnderTest = DayTwo2019.builder().input(mockInput).build();
    }

    @Test
    public void testSimpleMachine() throws Exception {
        when(mockInput.getInput()).thenReturn("1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50");
        List<Integer> expected = ImmutableList.of(3500,9,10,70,
                2,3,11,0,
                99,
                30,40,50);

        assertEquals(expected, classUnderTest.runMachine());
    }

    @Test
    public void testReplaceValueWithDoubleItself() throws Exception {
        when(mockInput.getInput()).thenReturn("1, 0, 0, 0, 99");
        List<Integer> expected = ImmutableList.of(2, 0, 0, 0, 99);

        assertEquals(expected, classUnderTest.runMachine());
    }

    @Test
    public void testReplaceLastValueWithMultiplication() throws Exception {
        when(mockInput.getInput()).thenReturn("2, 3, 0, 3, 99");
        List<Integer> expected = ImmutableList.of(2,3,0,6,99);

        assertEquals(expected, classUnderTest.runMachine());
    }

    @Test
    public void testReplaceLastValueWithBigMultiplication() throws Exception {
        when(mockInput.getInput()).thenReturn("2,4,4,5,99,0");
        List<Integer> expected = ImmutableList.of(2,4,4,5,99,9801);

        assertEquals(expected, classUnderTest.runMachine());
    }

    @Test
    public void testMultipleCommandsReplaceFirstValue() throws Exception {
        when(mockInput.getInput()).thenReturn("1,1,1,4,99,5,6,0,99");
        Integer expected = 30;

        assertEquals(expected, classUnderTest.partOne());
    }
}
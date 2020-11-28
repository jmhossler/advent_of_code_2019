package advent_of_code;

public class PuzzleRunner {

    public static void main(String[] args) {
        DayOne2019 dayOne = new DayOne2019();
        DayTwo2019 dayTwo = DayTwo2019.builder().input(new DayTwoInput()).build();

        displaySolution(1, 1, dayOne.partOne());
        displaySolution(1, 2, dayOne.partTwo());

        displaySolution(2, 1, dayTwo.partOne());
        displaySolution(2, 2, dayTwo.partTwo());
    }

    public static void displaySolution(Integer day, Integer part, Integer solution) {
        System.out.println("Day " + day + " part " + part + " solution: " + solution);
    }
}

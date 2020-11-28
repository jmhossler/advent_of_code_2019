package advent_of_code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DayOne2019 {
    // Solution to https://adventofcode.com/2019/day/1
    public Integer partOne() {
        // Part one
        final List<Integer> moduleMasses = getModuleMasses();
        final List<Integer> fuelRequirements = getFuelRequirements(moduleMasses);
        return sumFuelRequirements(fuelRequirements);
    }

    public Integer partTwo() {
        // Part two
        final List<Integer> moduleMasses = getModuleMasses();
        final List<Integer> fuelRequirements = getFuelRequirements(moduleMasses);

        Integer totalFuelRequirement = sumFuelRequirements(fuelRequirements);
        totalFuelRequirement += calculateFuelFuelRequirements(fuelRequirements);
        return totalFuelRequirement;
    }

    private static Integer calculateFuelFuelRequirements(List<Integer> fuelMass) {
        if(fuelMass.isEmpty()) {
            return 0;
        }
        final List<Integer> newFuelMass = getFuelRequirements(fuelMass).stream().filter(fuelRequirement -> fuelRequirement > 0).collect(Collectors.toList());
        final Optional<Integer> fuelFuelRequirements = newFuelMass.stream().reduce(Integer::sum);
        return fuelFuelRequirements.orElse(0) + calculateFuelFuelRequirements(newFuelMass);
    }

    private static List<Integer> getFuelRequirements(List<Integer> moduleMasses) {
        return moduleMasses.stream().map(moduleMass -> (moduleMass / 3) - 2).collect(Collectors.toList());
    }

    private static Integer sumFuelRequirements(List<Integer> fuelRequirements) {
        final Optional<Integer> totalFuelRequirement = fuelRequirements.stream().reduce(Integer::sum);
        return totalFuelRequirement.orElse(0);
    }

    private static List<Integer> getModuleMasses() {
        try {
            return Files.lines(Path.of("inputs\\day_one_input")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

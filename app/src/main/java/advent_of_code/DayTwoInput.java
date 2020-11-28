package advent_of_code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DayTwoInput {
    public String getInput() throws IOException {
        return Files.readString(Path.of("inputs\\day_two_input"));
    }
}

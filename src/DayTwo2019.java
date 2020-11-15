import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DayTwo2019 {
    private static List<Integer> opcodes = getOpcodes();

    static public void main(String[] args) throws Exception {
        Integer position = 0;
        while (opcodes.get(position) != 99) {
            Integer command = opcodes.get(position);
            if (command == 1) {
                position = add(position);
            } else if (command == 2) {
                position = multiply(position);
            } else {
                throw new Exception("Unexpected opcode " + opcodes.get(position) + " at " + position);
            }
        }
        System.out.println("Value at position 0: " + opcodes.get(0));
    }

    private static Integer add(Integer position) {
        opcodes.set(opcodes.get(position + 3), opcodes.get(opcodes.get(position + 1)) + opcodes.get(opcodes.get(position + 2)));
        return position + 4;
    }

    private static Integer multiply(Integer position) {
        opcodes.set(opcodes.get(position + 3), opcodes.get(opcodes.get(position + 1)) * opcodes.get(opcodes.get(position + 2)));
        return position + 4;
    }

    private static List<Integer> getOpcodes() {
        try {
            return Arrays.stream(Files.readString(Path.of("inputs\\day_two_input")).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Integer>();
        }
    }
}

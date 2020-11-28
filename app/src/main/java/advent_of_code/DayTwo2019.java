package advent_of_code;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
public class DayTwo2019 implements SolutionInterface {
    DayTwoInput input;

    @Getter
    @Builder
    static private class VirtualMachine {
       private final List<Integer> memory;
       private final Integer position;

       public Integer getCommand() {
           return memory.get(position);
       }

       public Integer getValue(Integer position) {
           return memory.get(memory.get(position));
       }

       public void setValue(Integer position, Integer value) {
           memory.set(memory.get(position), value);
       }

       public void setNoun(Integer value) { memory.set(1, value); }

       public void setVerb(Integer value) { memory.set(2, value); }
    }

    @Override
    public Integer partOne()  {
        return runMachine().get(0);
    }

    public List<Integer> runMachine() {
        VirtualMachine machine = VirtualMachine.builder().memory(getOpcodes()).position(0).build();
        VirtualMachine finalMachine = run(machine);
        return finalMachine.getMemory();
    }

    @Override
    public Integer partTwo() {
        Integer expectedOutput = 19690720;
        List<List<Integer>> potentialInputs = Lists.cartesianProduct(
                IntStream.range(0, 100).boxed().collect(Collectors.toList()),
                IntStream.range(0, 100).boxed().collect(Collectors.toList()));
        Optional<List<Integer>> solution = potentialInputs.stream().filter(input -> inputsMeetExpectations(input, expectedOutput)).findFirst();
        if (solution.isPresent()) {
            List<Integer> values = solution.get();
            return 100 * values.get(0) + values.get(1);
        }
        throw new IllegalStateException("No input pairs found to equal " + expectedOutput);
    }

    private boolean inputsMeetExpectations(List<Integer> input, Integer expectedOutput) {
        VirtualMachine machine = VirtualMachine.builder().memory(getOpcodes()).position(0).build();
        machine.setNoun(input.get(0));
        machine.setVerb(input.get(1));
        VirtualMachine newMachine = run(machine);
        Integer result = newMachine.getMemory().get(0);
        return result.equals(expectedOutput);
    }

    public VirtualMachine run(VirtualMachine machine) throws IllegalStateException {
        while (machine.getCommand() != 99) {
            Integer command = machine.getCommand();
            if (command == 1) {
                machine = add(machine);
            } else if (command == 2) {
                machine = multiply(machine);
            } else {
                throw new IllegalStateException("Unexpected opcode " + machine.getCommand() + " at " + machine.getPosition());
            }
        }
        return machine;
    }

    private VirtualMachine add(VirtualMachine machine) {
        List<Integer> new_memory = machine.getMemory();
        Integer position = machine.getPosition();

        VirtualMachine newMachine = VirtualMachine.builder().memory(new_memory).position(position + 4).build();
        newMachine.setValue(position + 3, machine.getValue(position+1) + machine.getValue(position+2));
        return newMachine;
    }

    private VirtualMachine multiply(VirtualMachine machine) {
        List<Integer> new_memory = machine.getMemory();
        Integer position = machine.getPosition();


        VirtualMachine newMachine = VirtualMachine.builder().memory(new_memory).position(position + 4).build();
        newMachine.setValue(position + 3, machine.getValue(position+1) * machine.getValue(position+2));
        return newMachine;
    }

    protected List<Integer> getOpcodes() {
        try {
            return Arrays.stream(input.getInput().split(",")).map(String::strip).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

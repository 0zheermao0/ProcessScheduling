package StorageManagement;

import java.util.LinkedList;

/**
 * @Classname Test
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/16 11:22
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        LinkedList<Instruction> instructions = new LinkedList<>();
        instructions.add(new Instruction(0, "070"));
        instructions.add(new Instruction(1, "050"));
        instructions.add(new Instruction(2, "015"));
        instructions.add(new Instruction(3, "021"));
        instructions.add(new Instruction(0, "056"));
        instructions.add(new Instruction(6, "040"));
        instructions.add(new Instruction(4, "053"));
        instructions.add(new Instruction(5, "023"));
        instructions.add(new Instruction(1, "037"));
        instructions.add(new Instruction(2, "078"));
        instructions.add(new Instruction(4, "001"));
        instructions.add(new Instruction(6, "084"));

        PageTable.getPageTable().addPage(new Page(0, 5, "011", true));
        PageTable.getPageTable().addPage(new Page(0, 5, "011", true));
        PageTable.getPageTable().addPage(new Page(0, 5, "011", true));
        PageTable.getPageTable().addPage(new Page(0, 5, "011", true));
        PageTable.getPageTable().addPage(new Page(0, 5, "011", true));
        PageTable.getPageTable().addPage(new Page(0, 5, "011", true));
        PageTable.getPageTable().addPage(new Page(0, 5, "011", true));
    }
}

package storageManagement.FIFO;

import java.util.LinkedList;

/**
 * @Classname Test
 * @Description TODO 测试类
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

        Page page0 = new Page(0, 5, "011", true);
        Page page1 = new Page(1, 8, "012", true);
        Page page2 = new Page(2, 9, "013", true);
        Page page3 = new Page(3, 1, "021", true);
        Page page4 = new Page(4, -1, "022", false);
        Page page5 = new Page(5, -1, "023", false);
        Page page6 = new Page(6, -1, "121", false);

        PageTable.getPageTable().addPage(page0);
        PageTable.getPageTable().addPage(page1);
        PageTable.getPageTable().addPage(page2);
        PageTable.getPageTable().addPage(page3);
        PageTable.getPageTable().addPage(page4);
        PageTable.getPageTable().addPage(page5);
        PageTable.getPageTable().addPage(page6);

//        Memory.getMemory().addPage(page0);
//        Memory.getMemory().addPage(page1);
//        Memory.getMemory().addPage(page2);
//        Memory.getMemory().addPage(page3);

        for(Instruction ins:instructions){
//            int debug = ins.getPageIndex();
            Memory.getMemory().addPage(PageTable.getPageTable().getPage(ins.getPageIndex()));
        }
    }
}

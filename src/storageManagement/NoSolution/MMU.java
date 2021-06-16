package storageManagement.NoSolution;

/**
 * @Classname MMU
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/16 10:47
 * @Version 1.0
 **/
public class MMU {
    public static String translate(Instruction instruction){
        Page page = PageTable.getPageTable().getPage(instruction.getPageIndex());
        if(Memory.getMemory().findPage(page) != null){
            return (page.getMemoryIndex()*Memory.BLOCK_LENGTH)+instruction.getUnitIndex();
        }
        return "*"+page.getPageIndex();
    }
}

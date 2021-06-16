package StorageManagement;

/**
 * @Classname MMU
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/16 10:47
 * @Version 1.0
 **/
public class MMU {
    public String translate(Instruction instruction){
        Page page = PageTable.getPageTable().getPage(instruction.getPageIndex());
        if(Memory.getMemory().findPage(page.getPageIndex()) != null){
            return (page.getMemoryIndex()*Memory.BLOCK_LENGTH)+instruction.getUnitIndex();
        }
        return "*"+page.getPageIndex();
    }
}

package storageManagement.NoSolution;

/**
 * @Classname MMU
 * @Description TODO MMU翻译绝对地址
 * @Author Joey
 * @Date 2021/6/16 10:47
 * @Version 1.0
 **/
public class MMU {
    /*
     * @Description //TODO 如果内存中没有这页，则打印中断信息
     * @Date 15:29 2021/6/16
     * @Param [instruction]
     * @return java.lang.String
     **/
    public static String translate(Instruction instruction){
        Page page = PageTable.getPageTable().getPage(instruction.getPageIndex());
        if(Memory.getMemory().findPage(page) != null){
            return (page.getMemoryIndex()*Memory.BLOCK_LENGTH)+instruction.getUnitIndex();
        }
        return "*"+page.getPageIndex();
    }
}

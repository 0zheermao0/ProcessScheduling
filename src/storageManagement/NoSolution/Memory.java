package storageManagement.NoSolution;

import java.util.LinkedList;

/**
 * @Classname Memory
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/16 11:09
 * @Version 1.0
 **/
public class Memory {
    public static Memory memory = null;
    public static Integer BLOCK_LENGTH = 128;
    private static final Integer DEFAULT_TABLE_SIZE = 4;
    private LinkedList<Page> pages = new LinkedList<>();

    public void addPage(Page page){
        if(pages.size() < DEFAULT_TABLE_SIZE){
            page.setFlag(true);
            pages.add(page);
        }
    }

    public Page findPage(Page page){
        for(Page p : pages){
            if(p.equals(page)){
                return p;
            }
        }
        return null;
    }

    public static Memory getMemory(){
        if (memory == null){
            memory = new Memory();
        }
        return memory;
    }
}

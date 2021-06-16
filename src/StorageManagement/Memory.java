package StorageManagement;

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
            pages.add(page);
        }
    }

    public Page findPage(Integer pageIndex){
        return pages.get(pageIndex);
    }

    public static Memory getMemory(){
        if (memory == null){
            memory = new Memory();
        }
        return memory;
    }
}

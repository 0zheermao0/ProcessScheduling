package storageManagement.FIFO;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

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
    private AtomicInteger k = new AtomicInteger(0);

    public void addPage(Page page){
        for(Page p : pages){
            if(p.equals(page)){
                System.out.println("[Memory]: page "+p.getPageIndex()+" is already in the memory, no need to load. ");
                System.out.println("[Memory]: current memory state: "+pages.toString());
                return;
            }
        }
        if (pages.size() < DEFAULT_TABLE_SIZE) {
            page.setChangeFlag(true);
            page.setFlag(true);
            pages.add(page);
        }else {
            System.out.println("[Memory]: memory is full! ");
            Page removePage = pages.get(k.get());
//            System.out.println("debug: "+k.get());
            removePage.setChangeFlag(true);
            removePage.setFlag(false);
            if(removePage.getChangeFlag()){
                System.out.println("[Memory]: call out page "+ removePage.getPageIndex());
            }else {
                System.out.println("[Memory]: cover page "+ removePage.getPageIndex());
            }
            page.setFlag(true);
            pages.set(k.get(), page);
            k.set((k.get() + 1) % DEFAULT_TABLE_SIZE);
//            System.out.println("debug: "+k.get());
        }
        System.out.println("[Memory]: load page " + page.getPageIndex());
        System.out.println("[Memory]: current memory state: "+pages.toString());
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

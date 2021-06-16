package storageManagement.FIFO;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname Memory
 * @Description TODO 内存类
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

    /*
     * @Description //TODO 将页载入内存中
     * @Date 15:14 2021/6/16
     * @Param [page]
     * @return void
     **/
    public void addPage(Page page){
        //如果载入的页已经在内存中，则不需要载入
        for(Page p : pages){
            if(p.equals(page)){
                System.out.println("[Memory]: page "+p.getPageIndex()+" is already in the memory, no need to load. ");
                System.out.println("[Memory]: current memory state: "+pages.toString());
                return;
            }
        }
        //如果当前内存未满，则直接载入
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
            //如果该页被修改过，则覆盖
            if(removePage.getChangeFlag()){
                System.out.println("[Memory]: call out page "+ removePage.getPageIndex());
            }else {
                //若未被修改，则调出
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

    /*
     * @Description //TODO 查找内存中是否有这页
     * @Date 15:16 2021/6/16
     * @Param [page]
     * @return storageManagement.FIFO.Page
     **/
    public Page findPage(Page page){
        for(Page p : pages){
            if(p.equals(page)){
                return p;
            }
        }
        return null;
    }

    /*
     * @Description //TODO 单例模式
     * @Date 15:23 2021/6/16
     * @Param []
     * @return storageManagement.FIFO.Memory
     **/
    public static Memory getMemory(){
        if (memory == null){
            memory = new Memory();
        }
        return memory;
    }
}

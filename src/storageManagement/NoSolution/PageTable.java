package storageManagement.NoSolution;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname PageTable
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/16 10:38
 * @Version 1.0
 **/
public class PageTable {
    private static PageTable pageTable = null;
    private static Map<Integer, Page> pageMap = new HashMap<Integer, Page>();

    public void addPage(Page page){
        pageMap.put(page.getPageIndex(), page);
    }

    public Page removePage(Page page){
        return pageMap.remove(page.getPageIndex());
    }

    public Page getPage(Integer pageIndex){
        return pageMap.get(pageIndex);
    }

    public static PageTable getPageTable(){
        if(pageTable == null){
            pageTable = new PageTable();
        }
        return pageTable;
    }
}

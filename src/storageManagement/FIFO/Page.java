package storageManagement.FIFO;

/**
 * @Classname Page
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/16 10:35
 * @Version 1.0
 **/
public class Page {
    private Integer pageIndex;
    private Integer memoryIndex;
    private String location;
    private Boolean flag = false;
    private Boolean changeFlag = false;

    public Boolean getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Boolean changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Page(Integer pageIndex, Integer memoryIndex, String location, Boolean flag) {
        this.pageIndex = pageIndex;
        this.memoryIndex = memoryIndex;
        this.location = location;
        this.flag = flag;
    }

    public Page() {
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getMemoryIndex() {
        return memoryIndex;
    }

    public void setMemoryIndex(Integer memoryIndex) {
        this.memoryIndex = memoryIndex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}

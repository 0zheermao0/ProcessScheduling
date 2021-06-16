package storageManagement.FIFO;

/**
 * @Classname Instruction
 * @Description TODO 指令类
 * @Author Joey
 * @Date 2021/6/16 11:05
 * @Version 1.0
 **/
public class Instruction {
    private Integer pageIndex;
    private String unitIndex;

    public Instruction(Integer pageIndex, String unitIndex) {
        this.pageIndex = pageIndex;
        this.unitIndex = unitIndex;
    }

    public Instruction() {
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(String unitIndex) {
        this.unitIndex = unitIndex;
    }
}

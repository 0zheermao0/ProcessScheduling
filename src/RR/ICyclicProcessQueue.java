package RR;

/*
 * @Description //TODO 循环队列接口层
 * @Date 16:12 2021/6/11
 * @Param 
 * @return 
 **/
public interface ICyclicProcessQueue<Process> {
    void enQueue(RR.Process.PCB process);
    RR.Process.PCB deQueue();
    RR.Process.PCB getFront();
    int getSize();
    boolean isEmpty();
    void remove(RR.Process.PCB pcb);
}

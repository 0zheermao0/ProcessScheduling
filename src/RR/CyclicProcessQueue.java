package RR;

/**
 * @Classname CylicProcessQueue
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 22:07
 * @Version 1.0
 **/
public class CyclicProcessQueue implements ICyclicProcessQueue<Process>{
    private Process head, end;

    public CyclicProcessQueue(){
        head = end = null;
    }

    @Override
    public void enQueue(Process process) {
        if(end == null){
            head = end = process;
        }else{
            end.getMyPCB().setNext(process.getMyPCB());
            end = process;
        }
    }

    @Override
    public Process deQueue() {
        if (head == null) {
            return null;
        }
        Process process = head;
        if (head.getMyPCB().getNext() == null) {
            head = end = null;
        }
        return process;
    }

    @Override
    public Process getFront() {
        return this.head == null ? null : this.head;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null && end == null;
    }
}

package RR;

import java.util.Iterator;

/**
 * @Classname CylicProcessQueue
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 22:07
 * @Version 1.0
 **/
public class CyclicProcessQueue<Process> implements ICyclicProcessQueue<Process>{
    private RR.Process.PCB head, end;
    private int length = 0;

    public CyclicProcessQueue(){
        head = end = null;
    }

    @Override
    public void enQueue(RR.Process.PCB process) {
        if(end == null){
            head = end = process;
        }else{
            end.setNext(process);
            end = process;
            process.setNext(head);
        }
        length++;
    }

    @Override
    public RR.Process.PCB deQueue() {
        if (head == null) {
            return null;
        }
        RR.Process.PCB process = head;
        if (head.getNext() == null) {
            head = end = null;
        }
        head = process.getNext();
        return process;
    }

    @Override
    public RR.Process.PCB getFront() {
        return this.head == null ? null : this.head;
    }

    @Override
    public int getSize() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    @Override
    public void remove(RR.Process.PCB process) {
        if(this.getSize() == 2){
            head = end = process.getNext();
            process.getNext().getNext().setNext(null);
        }else if(this.getSize() == 1){
            head = end = null;
        }else {
            RR.Process.PCB pcb = head;
            while (!pcb.getNext().equals(process)){
                pcb = pcb.getNext();
            }
            head = pcb.getNext().getNext();
            end = head;
            pcb.setNext(pcb.getNext().getNext());
        }
        length--;
    }
}

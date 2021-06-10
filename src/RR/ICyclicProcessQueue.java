package RR;

public interface ICyclicProcessQueue<Process> {
    void enQueue(RR.Process.PCB process);
    RR.Process.PCB deQueue();
    RR.Process.PCB getFront();
    int getSize();
    boolean isEmpty();
    void remove(RR.Process.PCB pcb);
}

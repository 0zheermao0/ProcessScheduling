package RR;

public interface ICyclicProcessQueue<Process> {
    void enQueue(RR.Process process);
    Process deQueue();
    Process getFront();
    int getSize();
    boolean isEmpty();
}

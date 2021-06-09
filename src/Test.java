import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Classname Test
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 14:04
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Comparator<Process> comparator = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return ((o1.getMyPCB().getPriority())-(o2.getMyPCB().getPriority()));
            }
        };

        PriorityQueue<Process> processQueue = new PriorityQueue<>(comparator);
        Process process1 = new Process();
        Process process2 = new Process();
        Process process3 = new Process();
        Process process4 = new Process();
        Process process5 = new Process();

        //@Param [processName, time, priority, state, next]
        process1.setMyPCB(new Process.PCB("P1", 2, 1, "R", process2.getMyPCB()));
        process2.setMyPCB(new Process.PCB("P2", 3, 5, "R", process2.getMyPCB()));
        process3.setMyPCB(new Process.PCB("P3", 1, 3, "R", process2.getMyPCB()));
        process4.setMyPCB(new Process.PCB("P4", 2, 4, "R", process2.getMyPCB()));
        process5.setMyPCB(new Process.PCB("P5", 4, 2, "R", process2.getMyPCB()));

        processQueue.add(process1);
        processQueue.add(process2);
        processQueue.add(process3);
        processQueue.add(process4);
        processQueue.add(process5);

        while (!processQueue.isEmpty()){
            Process p = processQueue.poll();
            System.out.println(p);
        }
    }
}

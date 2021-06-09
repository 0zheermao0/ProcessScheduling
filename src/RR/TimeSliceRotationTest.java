package RR;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Classname RR.TimeSliceRotationTest
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 17:16
 * @Version 1.0
 **/
public class TimeSliceRotationTest {
//    Comparator<PSA.Process> comparator = new Comparator<PSA.Process>() {
//        @Override
//        public int compare(PSA.Process o1, PSA.Process o2) {
//            int result = (o2.getMyPCB().getPriority()) - (o1.getMyPCB().getPriority());
//            if (result == 0) {
//                result = (o2.getMyPCB().getArriveTime() - o1.getMyPCB().getArriveTime());
//            }
//            return result;
//        }
//    };

    public static void main(String[] args) {
        Process process1 = new Process();
        Process process2 = new Process();
        Process process3 = new Process();
        Process process4 = new Process();
        Process process5 = new Process();

        process1.setMyPCB(new Process.PCB("P1", 1, "R"));
        process2.setMyPCB(new Process.PCB("P2", 5, "R"));
        process3.setMyPCB(new Process.PCB("P3", 3, "R"));
        process4.setMyPCB(new Process.PCB("P4", 4, "R"));
        process5.setMyPCB(new Process.PCB("P5", 2, "R"));

        List<Process> list = new LinkedList<Process>();
        list.add(process1);
        list.add(process2);
        list.add(process3);
        list.add(process4);
        list.add(process5);
    }

    public static void runProcess(){

    }

}

package RR;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Classname RR.TimeSliceRotationTest
 * @Description TODO 时间轮转测试类
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
        int CPU_TIME = 0;

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

        CyclicProcessQueue<Process> processQueue = new CyclicProcessQueue<>();
        processQueue.enQueue(process1.getMyPCB());
        processQueue.enQueue(process2.getMyPCB());
        processQueue.enQueue(process3.getMyPCB());
        processQueue.enQueue(process4.getMyPCB());
        processQueue.enQueue(process5.getMyPCB());

        /*
         * @Description //TODO 打印初始信息
         * @Date 16:13 2021/6/11
         * @Param [args]
         * @return void
         **/
        System.out.println("INPUT NAME,NEEDTIME");
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i).getMyPCB().getProcessName() + "    " +
                    list.get(i).getMyPCB().getTime());
        }

        /*
         * @Description //TODO 打印未运行的第一次阶段结果
         * @Date 16:14 2021/6/11
         * @Param [args]
         * @return void
         **/
        TimeSliceRotationTest.print(list, CPU_TIME);

        /*
         * @Description //TODO 若循环进程队列不为空，则运行进程并打印阶段结果
         * @Date 16:15 2021/6/11
         * @Param [args]
         * @return void
         **/
        while (!processQueue.isEmpty()) {
            CPU_TIME++;
            TimeSliceRotationTest.runProcess(processQueue, list, CPU_TIME);
        }

        System.out.println("Result: ");
        TimeSliceRotationTest.printResult(list);
    }

    /*
     * @Description //TODO 进程运行函数，运行后根据实际情况，将W状态置为E或回到R
     * @Date 16:15 2021/6/11
     * @Param [processQueue, processes, CPU_TIME]
     * @return void
     **/
    public static void runProcess(CyclicProcessQueue<Process> processQueue, List<Process> processes, int CPU_TIME) {
        Process.PCB pcb = processQueue.deQueue();

        pcb.run();
        TimeSliceRotationTest.print(processes, CPU_TIME);

        if (pcb.getRunTime() != 0 && pcb.getRunTime().equals(pcb.getTime())) {
            pcb.setState("E");
            pcb.setFinishTime(CPU_TIME);
            processQueue.remove(pcb);
        }

        if(!pcb.getState().equals("E")){
            pcb.setState("R");
        }
    }

    /*
     * @Description //TODO 打印函数，打印阶段结果
     * @Date 16:16 2021/6/11
     * @Param [list, CPU_TIME]
     * @return void
     **/
    public static void print(List<Process> list, int CPU_TIME) {
        System.out.println("CPUTIME: " + CPU_TIME);
        System.out.println("NAME    " + "NEEDTIME    " + "RUNTIME    " + "STATE    ");

        for (Process process : list) {
            String state = "";
            int cpuTime = process.getMyPCB().getCpuTime();
            switch (process.getMyPCB().getState()) {
                case "R":
                    state = "ready";
                    break;
                case "E":
                    state = "finish";
                    break;
                case "W":
                    state = "working";
                    break;
                default:
                    break;
            }
            if (cpuTime != 0 && process.getMyPCB().getState().equals("R")) {
                cpuTime++;
                process.getMyPCB().setCpuTime(cpuTime);
            } else if (cpuTime == 0) {
                process.getMyPCB().setArriveTime(CPU_TIME);
            }

            if (process.getMyPCB().getState().equals("R")) {
                int waitingTime = process.getMyPCB().getWaitingTime();
                waitingTime++;
                process.getMyPCB().setWaitingTime(waitingTime);
            }

            System.out.println(process.getMyPCB().getProcessName() + "         " + process.getMyPCB().getTime() + "        " +
                    process.getMyPCB().getRunTime() + "        " + state);
        }
    }

    /*
     * @Description //TODO 最终结果打印函数
     * @Date 16:16 2021/6/11
     * @Param [list]
     * @return void
     **/
    public static void printResult(List<Process> list) {
        System.out.println("NAME    RoundTime    WaitingTime");
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i).getMyPCB().getProcessName() + "         " +
                    list.get(i).getMyPCB().getFinishTime() + "            " + list.get(i).getMyPCB().getWaitingTime());
        }
    }

}

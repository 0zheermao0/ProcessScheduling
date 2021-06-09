import sun.plugin2.gluegen.runtime.CPU;

import java.util.*;

/**
 * @Classname Test
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 14:04
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        int CPU_TIME = 0;
        ArrayList<Process> list = new ArrayList<>();

        Comparator<Process> comparator = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return ((o2.getMyPCB().getPriority()) - (o1.getMyPCB().getPriority()));
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

        list.add(process1);
        list.add(process2);
        list.add(process3);
        list.add(process4);
        list.add(process5);

        System.out.println("INPUT NAME,NEEDTIME AND PRIORITY");
        for(int i=0;i<5;i++){
            System.out.println(list.get(i).getMyPCB().getProcessName() +"    "+
                    list.get(i).getMyPCB().getTime()+"    "+list.get(i).getMyPCB().getPriority());
        }

        Test.print(list, CPU_TIME);
        CPU_TIME++;

        while (!processQueue.isEmpty()) {
            Process p = processQueue.poll();
            assert p != null;
            p.run();
            Test.print(list, CPU_TIME);
            if (p.getMyPCB().getTime() != 0) {
                //如果还需要运行，先设为R，然后重排队
                p.getMyPCB().setState("R");
                processQueue.add(p);
            }
            CPU_TIME++;
        }

        Test.printResult(list, (CPU_TIME-1));
    }

    public static void print(List<Process> list, int CPU_TIME) {
        System.out.println("CPUTIME: " + CPU_TIME);
        System.out.println("NAME    " + "CPUTIME    " + "NEEDTIME    " + "PRIORITY    " + "STATE    ");

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
            if(cpuTime != 0 && process.getMyPCB().getState().equals("R")){
                cpuTime++;
                process.getMyPCB().setCpuTime(cpuTime);
            }else if(cpuTime == 0){
                process.getMyPCB().setArriveTime(CPU_TIME);
            }
            System.out.println(process.getMyPCB().getProcessName() + "         " + process.getMyPCB().getCpuTime() + "        " + process.getMyPCB().getTime() + "        " +
                    process.getMyPCB().getPriority() + "        " + state);
        }
    }

    public static void printResult(List<Process> list, int CPU_TIME){
        System.out.println("NAME    RoundTime    WaitingTime");
        for(int i=0;i<5;i++){
            System.out.println(list.get(i).getMyPCB().getProcessName() +"         "+
                    (CPU_TIME-list.get(i).getMyPCB().getArriveTime()) +"            "+ list.get(i).getMyPCB().getWaitingTime());
        }
    }
}

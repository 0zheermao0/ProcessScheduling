package PSA;

import java.util.*;

/**
 * @Classname PSA.Test
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 14:04
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        int CPU_TIME = 0;
        ArrayList<Process> list = new ArrayList<>();

        //重写优先队列的比较器，进程优先级越大越优先，如果同优先级则FCFS
        Comparator<Process> comparator = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                int result = (o2.getMyPCB().getPriority()) - (o1.getMyPCB().getPriority());
                if (result == 0) {
                    result = (o2.getMyPCB().getArriveTime() - o1.getMyPCB().getArriveTime());
                }
                return result;
            }
        };

        //新建五个进程
        PriorityQueue<Process> processQueue = new PriorityQueue<>(comparator);
        Process process1 = new Process();
        Process process2 = new Process();
        Process process3 = new Process();
        Process process4 = new Process();
        Process process5 = new Process();

        //@Param [processName, time, priority, state, next]
        //设置五个进程的PCB
        process1.setMyPCB(new Process.PCB("P1", 2, 1, "R", process2.getMyPCB()));
        process2.setMyPCB(new Process.PCB("P2", 3, 5, "R", process3.getMyPCB()));
        process3.setMyPCB(new Process.PCB("P3", 1, 3, "R", process4.getMyPCB()));
        process4.setMyPCB(new Process.PCB("P4", 2, 4, "R", process5.getMyPCB()));
        process5.setMyPCB(new Process.PCB("P5", 4, 2, "R", null));

        //进程进入优先队列
        processQueue.add(process1);
        processQueue.add(process2);
        processQueue.add(process3);
        processQueue.add(process4);
        processQueue.add(process5);

        //入列表方便打印结果
        list.add(process1);
        list.add(process2);
        list.add(process3);
        list.add(process4);
        list.add(process5);

        System.out.println("INPUT NAME,NEEDTIME AND PRIORITY");
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i).getMyPCB().getProcessName() + "    " +
                    list.get(i).getMyPCB().getTime() + "    " + list.get(i).getMyPCB().getPriority());
        }

        Test.print(list, CPU_TIME);
        CPU_TIME++;

        //优先队列不为空就持续运行进程
        while (!processQueue.isEmpty()) {
            int waitingTime = 0;

            Process p = processQueue.poll();
            assert p != null;
            p.run();
            if (p.getMyPCB().getState().equals("E")) {
                p.getMyPCB().setFinishTime(CPU_TIME);
            }
            Test.print(list, CPU_TIME);
            if (p.getMyPCB().getTime() != 0) {
                //如果还需要运行，先设为R，然后重排队
                p.getMyPCB().setState("R");
                processQueue.add(p);
            }
            CPU_TIME++;
        }

        Test.printResult(list, (CPU_TIME - 1));
    }

    /*
     * @Description //TODO 输出每一次运行的阶段结果
     * @Date 16:08 2021/6/11
     * @Param [list, CPU_TIME]
     * @return void
     **/
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
            if (cpuTime != 0 && process.getMyPCB().getState().equals("R")) {
                cpuTime++;
                process.getMyPCB().setCpuTime(cpuTime);
            } else if (cpuTime == 0) {
                process.getMyPCB().setArriveTime(CPU_TIME);
            }

            if(process.getMyPCB().getState().equals("R")){
                int waitingTime = process.getMyPCB().getWaitingTime();
                waitingTime++;
                process.getMyPCB().setWaitingTime(waitingTime);
            }

            System.out.println(process.getMyPCB().getProcessName() + "         " + process.getMyPCB().getCpuTime() + "        " + process.getMyPCB().getTime() + "        " +
                    process.getMyPCB().getPriority() + "        " + state);
        }
    }

    /*
     * @Description //TODO 打印最终结果
     * @Date 16:09 2021/6/11
     * @Param [list, CPU_TIME]
     * @return void
     **/
    public static void printResult(List<Process> list, int CPU_TIME) {
        System.out.println("NAME    RoundTime    WaitingTime");
        for (int i = 0; i < 5; i++) {
//            System.out.println(list.get(i).getMyPCB().getFinishTime());
            System.out.println(list.get(i).getMyPCB().getProcessName() + "         " +
                    list.get(i).getMyPCB().getFinishTime() + "            " + list.get(i).getMyPCB().getWaitingTime());
        }
    }
}

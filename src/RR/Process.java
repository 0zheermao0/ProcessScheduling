package RR;

/**
 * @Classname process
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 13:57
 * @Version 1.0
 **/
public class Process implements Runnable{

    private PCB myPCB;

    public Process(PCB myPCB) {
        this.myPCB = myPCB;
    }

    public Process() {
    }

    @Override
    public void run() {
        if(this.getMyPCB().getState().equals("R")){
            int waitingTime = this.getMyPCB().getWaitingTime();
            waitingTime++;
            this.getMyPCB().setWaitingTime(waitingTime);

            this.getMyPCB().setState("W");
        }else if(this.getMyPCB().getState().equals("E")){
            return;
        }
        Integer runTime = this.myPCB.getRunTime();
        Integer time = this.myPCB.getTime();
        Integer cpuTime = this.myPCB.getCpuTime();
        time--;
        if(cpuTime == 0 || this.getMyPCB().getState().equals("W")){
            cpuTime++;
            this.myPCB.setCpuTime(cpuTime);
        }

        if(time == 0){
            this.getMyPCB().setState("E");
        }

        this.myPCB.setRunTime(runTime);
        this.myPCB.setTime(time);
    }

    public PCB getMyPCB() {
        return myPCB;
    }

    public void setMyPCB(PCB myPCB) {
        this.myPCB = myPCB;
    }

    static class PCB{
        private String processName;
        private Integer time;
        private Integer runTime;
        private Integer cpuTime = 0;
        private String state;
        private PCB next = null;


        private Integer arriveTime;
        private Integer finishTime = -1;
        private Integer waitingTime = 0;

        public PCB(String processName, Integer time, Integer runTime, Integer cpuTime, String state, PCB next, Integer arriveTime, Integer finishTime, Integer waitingTime) {
            this.processName = processName;
            this.time = time;
            this.runTime = runTime;
            this.cpuTime = cpuTime;
            this.state = state;
            this.next = next;
            this.arriveTime = arriveTime;
            this.finishTime = finishTime;
            this.waitingTime = waitingTime;
        }

        public PCB() {
        }

        public String getProcessName() {
            return processName;
        }

        public void setProcessName(String processName) {
            this.processName = processName;
        }

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public PCB getNext() {
            return next;
        }

        public void setNext(PCB next) {
            this.next = next;
        }

        public Integer getCpuTime() {
            return cpuTime;
        }

        public void setCpuTime(Integer cpuTime) {
            this.cpuTime = cpuTime;
        }

        public Integer getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(Integer arriveTime) {
            this.arriveTime = arriveTime;
        }

        public Integer getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(Integer finishTime) {
            this.finishTime = finishTime;
        }

        public Integer getWaitingTime() {
            return waitingTime;
        }

        public void setWaitingTime(Integer waitingTime) {
            this.waitingTime = waitingTime;
        }

        public Integer getRunTime() {
            return runTime;
        }

        public void setRunTime(Integer runTime) {
            this.runTime = runTime;
        }
    }

    @Override
    public String toString() {
        return this.getMyPCB().getProcessName();
    }
}

package PSA;

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
        Integer priority = this.myPCB.getPriority();
        Integer time = this.myPCB.getTime();
        Integer cpuTime = this.myPCB.getCpuTime();
        priority--;
        time--;
        if(cpuTime == 0 || this.getMyPCB().getState().equals("W")){
            cpuTime++;
            this.myPCB.setCpuTime(cpuTime);
        }

        if(time == 0){
            this.getMyPCB().setState("E");
        }

        this.myPCB.setPriority(priority);
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
        private Integer priority;
        private Integer cpuTime = 0;
        private String state;
        private PCB next = null;
        private Integer arriveTime;
        private Integer finishTime = -1;
        private Integer waitingTime = 0;

        /*
         * @Description //TODO 
         * @Date 14:20 2021/6/9
         * @Param [processName, time, priority, state, next]
         * @return 
         **/
        public PCB(String processName, Integer time, Integer priority, String state, PCB next) {
            this.processName = processName;
            this.time = time;
            this.priority = priority;
            this.state = state;
            this.next = next;
        }

        public PCB(String processName, Integer time, Integer priority, Integer cpuTime, String state, PCB next) {
            this.processName = processName;
            this.time = time;
            this.priority = priority;
            this.cpuTime = cpuTime;
            this.state = state;
            this.next = next;
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

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
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
    }

    @Override
    public String toString() {
        return this.getMyPCB().getProcessName();
    }
}

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

    }

    public PCB getMyPCB() {
        return myPCB;
    }

    public void setMyPCB(PCB myPCB) {
        this.myPCB = myPCB;
    }

    static class PCB implements Runnable{
        private String processName;
        private Integer time;
        private Integer runTime = 0;
        private Integer cpuTime = 0;
        private String state;
        private PCB next = null;

        private Integer arriveTime;
        private Integer finishTime = 0;
        private Integer waitingTime = 0;

        public PCB(String processName, Integer time, String state) {
            this.processName = processName;
            this.time = time;
            this.state = state;
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

        public Integer getRunTime() {
            return runTime;
        }

        public void setRunTime(Integer runTime) {
            this.runTime = runTime;
        }

        public Integer getCpuTime() {
            return cpuTime;
        }

        public void setCpuTime(Integer cpuTime) {
            this.cpuTime = cpuTime;
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

        @Override
        public String toString() {
            return "PCB{" +
                    "processName='" + processName + '\'' +
                    '}';
        }

        @Override
        public void run() {
            if(this.getState().equals("R")){
                this.setState("W");
            }

            int runTime = this.getRunTime();
            runTime++;
            this.setRunTime(runTime);

            if(this.getTime().equals(this.getRunTime())){
                this.setState("E");
            }
        }
    }
        @Override
    public String toString() {
        return this.getMyPCB().getProcessName();
    }
}

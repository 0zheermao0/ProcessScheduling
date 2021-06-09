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
            this.getMyPCB().setState("W");
        }else if(this.getMyPCB().getState().equals("E")){
            return;
        }
        Integer priority = this.myPCB.getPriority();
        Integer time = this.myPCB.getTime();
        priority--;
        time--;
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
        private String state;
        private PCB next = null;

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
    }

    @Override
    public String toString() {
        return this.getMyPCB().getProcessName();
    }
}

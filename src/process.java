/**
 * @Classname process
 * @Description TODO
 * @Author Joey
 * @Date 2021/6/9 13:57
 * @Version 1.0
 **/
public class process implements Runnable{

    PCB myPCB;

    public process(PCB myPCB) {
        this.myPCB = myPCB;
    }

    @Override
    public void run() {

    }

    private class PCB{
        String processName;
        Integer time;
        Integer priority;
        Character state;
        PCB next;

        public PCB(String processName, Integer time, Integer priority, Character state, PCB next) {
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

        public Character getState() {
            return state;
        }

        public void setState(Character state) {
            this.state = state;
        }

        public PCB getNext() {
            return next;
        }

        public void setNext(PCB next) {
            this.next = next;
        }
    }
}

package com.cj.design.desiginmodel.行为型.状态模式;

/**
 *
 */
public class StatePattern {

    // Context
    static class Robot {
        private State state;

        public Robot() {
            this.state = new StandbyState();
        }

        public void setState(State state) {
            this.state = state;
        }

        public void action() {
            state.handle(this);
        }
    }

    // State
    interface State {
        void handle(Robot robot);
    }

    // ConcreteState
    static class StandbyState implements State {
        @Override
        public void handle(Robot robot) {
            System.out.println("待机状态，等待命令...");
            robot.setState(new WorkState());
        }
    }

    static class WorkState implements State {
        @Override
        public void handle(Robot robot) {
            System.out.println("工作状态，正在执行任务...");
            robot.setState(new SleepState());
        }
    }

    static class SleepState implements State {
        @Override
        public void handle(Robot robot) {
            System.out.println("休眠状态，暂停任务...");
            robot.setState(new StandbyState());
        }
    }

    // 测试
    public static void main(String[] args) {
        Robot robot = new Robot();
        robot.action(); // 待机状态
        robot.action(); // 工作状态
        robot.action(); // 休眠状态
    }

}

package com.cj.design.desiginmodel.行为型.状态模式;


import java.util.HashMap;
import java.util.Map;

/**
 * 查表法：
 * 通过二维数组表示状态机。
 * 在这个二维表中，第一维表示当前状态，第二维表示事件，值表示当前状态经过事件之后，转移到的新状态及其执行的动作
 * 局限性：
 * 如果纬度过多，就无法通过二维数组表示
 */
public class 查表法 {
    // Context
    static class Robot {
        enum State {
            STANDBY, WORK, SLEEP
        }

        private State state;
        private Map<State, Runnable> actions;

        public Robot() {
            this.state = State.STANDBY;
            this.actions = new HashMap<>();
            actions.put(State.STANDBY, () -> {
                System.out.println("待机状态，等待命令...");
                setState(State.WORK);
            });
            actions.put(State.WORK, () -> {
                System.out.println("工作状态，正在执行任务...");
                setState(State.SLEEP);
            });
            actions.put(State.SLEEP, () -> {
                System.out.println("休眠状态，暂停任务...");
                setState(State.STANDBY);
            });
        }

        public void setState(State state) {
            this.state = state;
        }

        public void action() {
            actions.get(state).run();
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

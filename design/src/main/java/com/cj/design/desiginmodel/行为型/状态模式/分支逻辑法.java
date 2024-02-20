package com.cj.design.desiginmodel.行为型.状态模式;

/**
 * @author : 
 * @creation : 2022/10/05
 * @description :
 * 概念：
 * 通过分支逻辑或者查表法实现状态的管理
 * 一般用来实现状态机，而状态机常用在游戏、工作流引擎等系统开发中
 * 示例：
 * 马里奥形态的转变就是一个状态机。其中，马里奥的不同形态就是状态机中的“状态”，
 * 游戏情节（比如吃了蘑菇）就是状态机中的“事件”，
 * 加减积分就是状态机中的“动作”。比如，吃蘑菇这个事件，
 * 会触发状态的转移：从小马里奥转移到超级马里奥，以及触发动作的执行（增加100积分）。
 * 1.分支逻辑法：
 * 分支逻辑法。利用if-else或者switch-case分支逻辑，参照状态转移图，
 * 将每一个状态转移原模原样地直译成代码。对于简单的状态机来说，这种实现方式最简单、最直接，是首选。
 * 2.查表法
 * 对于状态很多、状态转移比较复杂的状态机来说，查表法比较合适。通过二维数组来表示状态转移图，
 * 能极大地提高代码的可读性和可维护性。
 * 3.状态模式
 * 对于状态并不多、状态转移也比较简单，但事件触发执行的动作包含的业务逻辑可能比较复杂的状态机来说，我们首选这种实现方式。
 */
public class 分支逻辑法 {

    public enum State {
        SMALL(0),
        SUPER(1),
        FIRE(2),
        CAPE(3);

        private int value;

        private State(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    static class MarioStateMachine {
        private int score;
        private State currentState;

        public MarioStateMachine() {
            this.score = 0;
            this.currentState = State.SMALL;
        }

        public void obtainMushRoom() {
            if (currentState.equals(State.SMALL)) {
                this.currentState = State.SUPER;
                this.score += 100;
            }
        }

        public void obtainCape() {
            if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER) ) {
                this.currentState = State.CAPE;
                this.score += 200;
            }
        }

        public void obtainFireFlower() {
            if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER) ) {
                this.currentState = State.FIRE;
                this.score += 300;
            }
        }

        //遇到怪物
        public void meetMonster() {
            if (currentState.equals(State.SUPER)) {
                this.currentState = State.SMALL;
                this.score -= 100;
                return;
            }

            if (currentState.equals(State.CAPE)) {
                this.currentState = State.SMALL;
                this.score -= 200;
                return;
            }

            if (currentState.equals(State.FIRE)) {
                this.currentState = State.SMALL;
                this.score -= 300;
                return;
            }
        }

        public int getScore() {
            return this.score;
        }

        public State getCurrentState() {
            return this.currentState;
        }
    }

    public static void main(String[] args) {
        MarioStateMachine stateMachine = new MarioStateMachine();
    }

}

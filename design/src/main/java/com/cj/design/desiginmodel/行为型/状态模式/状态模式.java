package com.cj.design.desiginmodel.行为型.状态模式;

/**
 * @author : 
 * @creation : 2022/10/05
 * @description :
 */
public class 状态模式 {

    public enum State {
        SMALL(0),
        SUPER(1),
        FIRE(2);

        private int value;

        private State(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public interface IMario { //所有状态类的接口
        State getName();

        //以下是定义的事件
        void obtainMushRoom();

        void obtainFireFlower();

        void meetMonster();
    }

    static class SmallMario implements IMario {
        private MarioStateMachine stateMachine;

        public SmallMario(MarioStateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public State getName() {
            return State.SMALL;
        }

        @Override
        public void obtainMushRoom() {
            stateMachine.setCurrentState(new SuperMario(stateMachine));
            stateMachine.setScore(stateMachine.getScore() + 100);
        }

        @Override
        public void obtainFireFlower() {
            stateMachine.setCurrentState(new FireMario(stateMachine));
            stateMachine.setScore(stateMachine.getScore() + 300);
        }

        @Override
        public void meetMonster() {
            // do nothing...
        }
    }

    static class FireMario implements IMario {
        private MarioStateMachine stateMachine;

        public FireMario(MarioStateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public State getName() {
            return State.FIRE;
        }

        @Override
        public void obtainMushRoom() {
            stateMachine.setCurrentState(new SuperMario(stateMachine));
            stateMachine.setScore(stateMachine.getScore() + 100);
        }

        @Override
        public void obtainFireFlower() {
            stateMachine.setCurrentState(new FireMario(stateMachine));
            stateMachine.setScore(stateMachine.getScore() + 300);
        }

        @Override
        public void meetMonster() {
            // do nothing...
        }
    }

    static class SuperMario implements IMario {
        private MarioStateMachine stateMachine;

        public SuperMario(MarioStateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public State getName() {
            return State.SUPER;
        }

        @Override
        public void obtainMushRoom() {
            // do nothing...
        }

        @Override
        public void obtainFireFlower() {
            stateMachine.setCurrentState(new FireMario(stateMachine));
            stateMachine.setScore(stateMachine.getScore() + 300);
        }

        @Override
        public void meetMonster() {
            stateMachine.setCurrentState(new SmallMario(stateMachine));
            stateMachine.setScore(stateMachine.getScore() - 100);
        }
    }


    public class MarioStateMachine {
        private int score;
        private IMario currentState; // 不再使用枚举来表示状态

        public MarioStateMachine() {
            this.score = 0;
            this.currentState = new SmallMario(this);
        }

        public void obtainMushRoom() {
            this.currentState.obtainMushRoom();
        }

        public void obtainFireFlower() {
            this.currentState.obtainFireFlower();
        }

        public void meetMonster() {
            this.currentState.meetMonster();
        }

        public int getScore() {
            return this.score;
        }

        public State getCurrentState() {
            return this.currentState.getName();
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setCurrentState(IMario currentState) {
            this.currentState = currentState;
        }
    }

}

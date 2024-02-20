package com.cj.design.desiginmodel.行为型.状态模式;

import static com.cj.design.desiginmodel.行为型.状态模式.查表法.State.CAPE;
import static com.cj.design.desiginmodel.行为型.状态模式.查表法.State.FIRE;
import static com.cj.design.desiginmodel.行为型.状态模式.查表法.State.SMALL;
import static com.cj.design.desiginmodel.行为型.状态模式.查表法.State.SUPER;

/**
 * @author : 
 * @creation : 2022/10/05
 * @description :
 * 查表法：
 * 通过二维数组表示状态机。
 * 在这个二维表中，第一维表示当前状态，第二维表示事件，值表示当前状态经过事件之后，转移到的新状态及其执行的动作
 * 局限性：
 * 如果纬度过多，就无法通过二维数组表示
 */
public class 查表法 {
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

    public enum Event {
        GOT_MUSHROOM(0),
        GOT_CAPE(1),
        GOT_FIRE(2),
        MET_MONSTER(3);

        private int value;

        private Event(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    static class MarioStateMachine {
        private int score;
        private State currentState;

        static final State[][] transitionTable = {
          {SUPER, CAPE, FIRE, SMALL},
          {SUPER, CAPE, FIRE, SMALL},
          {CAPE, CAPE, CAPE, SMALL},
          {FIRE, FIRE, FIRE, SMALL}
        };

        private static final int[][] actionTable = {
          {+100, +200, +300, +0},
          {+0, +200, +300, -100},
          {+0, +0, +0, -200},
          {+0, +0, +0, -300}
        };

        public MarioStateMachine() {
            this.score = 0;
            this.currentState = SMALL;
        }

        public void obtainMushRoom() {
            executeEvent(Event.GOT_MUSHROOM);
        }

        public void obtainCape() {
            executeEvent(Event.GOT_CAPE);
        }

        public void obtainFireFlower() {
            executeEvent(Event.GOT_FIRE);
        }

        public void meetMonster() {
            executeEvent(Event.MET_MONSTER);
        }

        private void executeEvent(Event event) {
            int stateValue = currentState.getValue();
            int eventValue = event.getValue();
            this.currentState = transitionTable[stateValue][eventValue];
            this.score += actionTable[stateValue][eventValue];
        }

        public int getScore() {
            return this.score;
        }

        public State getCurrentState() {
            return this.currentState;
        }

    }
}

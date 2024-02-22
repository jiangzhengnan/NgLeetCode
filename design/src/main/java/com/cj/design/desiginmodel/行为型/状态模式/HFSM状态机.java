package com.cj.design.desiginmodel.行为型.状态模式;

/**
 * 以下是一个简单的HFSM（层次有限状态机）状态机的demo实现，使用Java语言实现。
 */
public class HFSM状态机 {

  /**
   * 状态机接口（StateMachine），它的抽象方法包括进入状态、退出状态、更新状态和绘制状态。
   */
  public interface StateMachine {
    void enterState();

    void exitState();

    void updateState();

    void drawState();
  }

  /**
   * 基本状态（BaseState）类，它实现了状态机接口，提供了默认的状态进入、退出、更新和绘制方法。
   */
  static abstract class BaseState implements StateMachine {

    @Override
    public void enterState() {
      System.out.println("Enter state: " + this.getClass().getSimpleName());
    }

    @Override
    public void exitState() {
      System.out.println("Exit state: " + this.getClass().getSimpleName());
    }

    @Override
    public void updateState() {
      System.out.println("Update state: " + this.getClass().getSimpleName());
    }

    @Override
    public void drawState() {
      System.out.println("Draw state: " + this.getClass().getSimpleName());
    }
  }

  /**
   * 状态机（StateMachineContext）类，它是一个有限状态机的管理类，包含了每一个状态和状态之间的转移。
   * 通过调用状态机对象的enterState方法，状态机可以进入一个特定的初始状态，并根据条件转移到其它状态。
   */
  static class StateMachineContext {
    private StateMachine currentState;

    public void setCurrentState(StateMachine currentState) {
      this.currentState = currentState;
    }

    public void enterState() {
      currentState.enterState();
    }

    public void exitState() {
      currentState.exitState();
    }

    public void updateState() {
      currentState.updateState();
    }

    public void drawState() {
      currentState.drawState();
    }
  }

  static class IdleState extends BaseState {
    @Override
    public void updateState() {
      super.updateState();
      System.out.println("Update Idle state: do nothing");
    }

    @Override
    public void drawState() {
      super.drawState();
      System.out.println("Draw Idle state: show idle screen");
    }
  }

  static class WalkState extends BaseState {

    @Override
    public void enterState() {
      super.enterState();
      System.out.println("Enter Walk state: start walking");
    }

    @Override
    public void updateState() {
      super.updateState();
      System.out.println("Update Walk state: keep walking");
    }

    @Override
    public void drawState() {
      super.drawState();
      System.out.println("Draw Walk state: show walking animation");
    }

    @Override
    public void exitState() {
      super.exitState();
      System.out.println("Exit Walk state: stop walking");
    }
  }

  public static void main(String[] args) {
    StateMachineContext context = new StateMachineContext();
    StateMachine idleState = new IdleState();
    StateMachine walkState = new WalkState();

    context.setCurrentState(idleState);
    context.enterState();
    context.updateState();
    context.drawState();
    context.exitState();

    context.setCurrentState(walkState);
    context.enterState();
    context.updateState();
    context.drawState();
    context.exitState();
  }

}

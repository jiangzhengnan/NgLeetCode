package com.cj.design.desiginmodel.行为型.命令模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 概念：
 * 命令模式将请求（命令）封装为一个对象，这样可以使用不同的请求参数化其他对象（将不同请求依赖注入到其他对象），
 * 并且能够支持请求（命令）的排队执行、记录日志、撤销等（附加控制）功能。
 * 示例：
 * 手游后端服务器轮询获取客户端发来的请求，获取到请求之后，借助命令模式，
 * 把请求包含的数据和处理逻辑封装为命令对象，并存储在内存队列中。然后，再从队列中取出一定数量的命令来执行。
 * 执行完成之后，再重新开始新的一轮轮询
 * <p>
 * <p>
 * 主要角色有四个：
 * Command: 抽象命令
 * ConcreteCommand：具体命令
 * Receiver：命令接收者
 * Invoker：命令请求者
 */
public class CommandPattern {

    /**
     * 抽象命令接口 Command
     */
    interface Command {
        void execute();

        void undo();
    }

    /**
     * 定义具体命令 ConcreteCommand
     */
    static class TVOnCommand implements Command {
        private final TV tv;

        public TVOnCommand(TV tv) {
            this.tv = tv;
        }

        @Override
        public void execute() {
            tv.on();
        }

        @Override
        public void undo() {
            tv.off();
        }
    }

    /**
     * 命令接收者：Receiver
     */
    static class TV {
        public void on() {
            System.out.println("TV is turned on.");
        }

        public void off() {
            System.out.println("TV is turned off.");
        }
    }

    /**
     * 命令请求者，遥控器Invoker
     */
    static class RemoteControl {
        private final List<Command> history = new ArrayList<>();
        private Command undoCommand;

        public void execute(Command command) {
            command.execute();
            history.add(command);
            undoCommand = command;
        }

        public void undo() {
            if (!history.isEmpty()) {
                Command lastCommand = history.remove(history.size() - 1);
                lastCommand.undo();
                undoCommand = lastCommand;
            } else {
                System.out.println("The command history is empty.");
            }
        }

        public void undoAll() {
            for (int i = history.size() - 1; i >= 0; i--) {
                history.get(i).undo();
            }
            history.clear();
            System.out.println("All commands have been undone.");
        }
    }

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        TV tv = new TV();
        Command tvOnCommand = new TVOnCommand(tv);

        remoteControl.execute(tvOnCommand);   // TV is turned on.

        remoteControl.undo(); // TV is is turned on.

        remoteControl.undoAll(); // All commands have been undone.
    }
}

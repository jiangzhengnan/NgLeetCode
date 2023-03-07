package com.ng.code.设计模式.行为型.命令模式;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : 
 * @creation : 2022/10/06
 * @description :
 * 概念：
 * 命令模式将请求（命令）封装为一个对象，这样可以使用不同的请求参数化其他对象（将不同请求依赖注入到其他对象），
 * 并且能够支持请求（命令）的排队执行、记录日志、撤销等（附加控制）功能。
 * 示例：
 * 手游后端服务器轮询获取客户端发来的请求，获取到请求之后，借助命令模式，
 * 把请求包含的数据和处理逻辑封装为命令对象，并存储在内存队列中。然后，再从队列中取出一定数量的命令来执行。
 * 执行完成之后，再重新开始新的一轮轮询
 */
public class 命令模式 {

    enum Event {
        GOT_STAR, HIT_OBSTACLE, ARCHIVE
    }

    static class Request {
        Event mEvent;

        public Event getEvent() {
            return mEvent;
        }
    }

    public interface Command {
        void execute();
    }

    static class HitObstacleCommand implements Command {
        public HitObstacleCommand(/*数据*/) {
        }

        @Override
        public void execute() {
        }
    }

    static class ArchiveCommand implements Command {
        public ArchiveCommand(/*数据*/) {
        }

        @Override
        public void execute() {
        }
    }

    static class GotStartCommand implements Command {
        public GotStartCommand(/*数据*/) {
        }

        @Override
        public void execute() {
        }
    }

    private static final int MAX_HANDLED_REQ_COUNT_PER_LOOP = 100;
    private static Queue<Command> queue = new LinkedList<>();

    static void mainloop() {
        while (true) {
            List<Request> requests = new ArrayList<>();
            //省略从epoll或者select中获取数据，并封装成Request的逻辑，
            //注意设置超时时间，如果很长时间没有接收到请求，就继续下面的逻辑处理。
            for (Request request : requests) {
                Event event = request.getEvent();
                Command command = null;
                if (event.equals(Event.GOT_STAR)) {
                    command = new GotStartCommand(/*数据*/);
                } else if (event.equals(Event.HIT_OBSTACLE)) {
                    command = new HitObstacleCommand(/*数据*/);
                } else if (event.equals(Event.ARCHIVE)) {
                    command = new ArchiveCommand(/*数据*/);
                } // ...一堆else if...
                queue.add(command);
            }
            int handledCount = 0;
            while (handledCount < MAX_HANDLED_REQ_COUNT_PER_LOOP) {
                if (queue.isEmpty()) {
                    break;
                }
                Command command = queue.poll();
                command.execute();
            }
        }
    }

    public static void main(String[] args) {
    }

}

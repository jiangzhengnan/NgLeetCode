package com.ng.code.设计模式.行为型.备忘录模式;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/10/06
 * @description :
 * 概念：
 * 不违背封装原则的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便之后恢复对象为先前的状态。
 * 示例：
 * 希望你编写一个小程序，可以接收命令行的输入。用户输入文本时，程序将其追加存储在内存文本中；用户输入“:list”，
 * 程序在命令行中输出内存文本的内容；用户输入“:undo”，程序会撤销上一次输入的文本，也就是从内存文本中将上次输入的文本删除掉。
 * 解决方案：
 * 定义一个独立的类（Snapshot类）来表示快照(自身不可变)，而不是复用InputText类。这个类只暴露get()方法，没有set()等任何修改内部状态的方法。
 * 其二，在InputText类中，我们把setText()方法重命名为restoreSnapshot()方法，用意更加明确，只用来恢复对象。
 */
public class 备忘录模式 {

    static class InputText {
        private StringBuilder text = new StringBuilder();

        public String getText() {
            return text.toString();
        }

        public void append(String input) {
            text.append(input);
        }

        public Snapshot createSnapshot() {
            return new Snapshot(text.toString());
        }

        //储存快照
        public void restoreSnapshot(Snapshot snapshot) {
            this.text.replace(0, this.text.length(), snapshot.getText());
        }
    }

    static class Snapshot {
        private String text;

        public Snapshot(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    static class SnapshotHolder {
        private Stack<Snapshot> snapshots = new Stack<>();

        public Snapshot popSnapshot() {
            return snapshots.pop();
        }

        public void pushSnapshot(Snapshot snapshot) {
            snapshots.push(snapshot);
        }
    }


    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotsHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals(":list")) {
                System.out.println(inputText.toString());
            } else if (input.equals(":undo")) {
                Snapshot snapshot = snapshotsHolder.popSnapshot();
                inputText.restoreSnapshot(snapshot);
            } else {
                snapshotsHolder.pushSnapshot(inputText.createSnapshot());
                inputText.append(input);
            }
        }
    }
}

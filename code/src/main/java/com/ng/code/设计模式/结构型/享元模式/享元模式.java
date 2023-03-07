package com.ng.code.设计模式.结构型.享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 
 * @creation : 2022/10/04
 * @description :
 * 概念:
 * 当一个系统中存在大量重复对象的时候，如果这些重复的对象是不可变对象，
 * 我们就可以利用享元模式将对象设计成享元，在内存中只保留一份实例，供多处代码引用
 * 示例：
 * 直播间需要实现弹幕表情功能，每一个弹幕的id和表情是固定的，但是x，y坐标不同。
 */
public class 享元模式 {

    // 享元类,弹幕
    static class BarrageUnit {
        public int id;
        private String face;

        public BarrageUnit(final int id, final String face) {
            this.id = id;
            this.face = face;
        }

        @Override
        public String toString() {
            return "BarrageUnit{" +
                   "id=" + id +
                   ", face='" + face + '\'' +
                   '}';
        }
    }

    //弹幕类
    static class Barrage {
        public BarrageUnit mBarrageUnit;
        public float x, y;

        public Barrage(final BarrageUnit barrageUnit, final float x, final float y) {
            mBarrageUnit = barrageUnit;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Barrage{" +
                   "mBarrageUnit=" + mBarrageUnit +
                   ", x=" + x +
                   ", y=" + y +
                   '}';
        }
    }


    static class ChessPieceUnitFactory {
        private static final Map<Integer, BarrageUnit> barrages = new HashMap<>();

        static {
            barrages.put(1, new BarrageUnit(1, "微笑"));
            barrages.put(2, new BarrageUnit(2, "愤怒"));
            barrages.put(3, new BarrageUnit(3, "狗头"));
            //...省略初始化其他弹幕的代码...
        }

        public static BarrageUnit getChessPiece(int barrageId) {
            return barrages.get(barrageId);
        }
    }

    static class LiveRoom {
        private final Map<Integer, Barrage> barrages = new HashMap<>();

        //展示表情弹幕
        public void showFace(int barrageId, int toPositionX, int toPositionY) {
            barrages.put(barrageId, new Barrage(
              ChessPieceUnitFactory.getChessPiece(barrageId), toPositionX, toPositionX));
        }

        public void draw() {
            System.out.println("绘制弹幕:" + barrages.toString());
        }
    }

    public static void main(String[] args) {
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.showFace(1,100,100);
        liveRoom.showFace(2,200,200);
        liveRoom.showFace(3,300,300);
        liveRoom.draw();

    }
}

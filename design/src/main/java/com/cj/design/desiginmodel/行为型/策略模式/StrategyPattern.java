package com.cj.design.desiginmodel.行为型.策略模式;


/**
 * 概念：
 * 定义一族算法类，将每个算法分别封装起来，让它们可以互相替换。
 * 使算法的变化独立于使用算法的代码。
 */
public class StrategyPattern {


    //攻击方式的接口：
    public interface AttackStrategy {
        void attack();
    }

    //具体攻击方式
    static class MeleeAttack implements AttackStrategy {
        @Override
        public void attack() {
            System.out.println("使用近战攻击");
        }
    }

    static class RangedAttack implements AttackStrategy {
        @Override
        public void attack() {
            System.out.println("使用远程攻击");
        }
    }

    //游戏角色，可以选择攻击方式
    static class GameCharacter {
        private AttackStrategy attackStrategy;

        public GameCharacter(AttackStrategy attackStrategy) {
            this.attackStrategy = attackStrategy;
        }

        public void attack() {
            attackStrategy.attack();
        }
    }

    //最后，我们可以创建不同的游戏角色，并在不同的场景中使用不同的攻击方式：
    public static void main(String[] args) {
        AttackStrategy melee = new MeleeAttack();
        AttackStrategy ranged = new RangedAttack();

        GameCharacter player1 = new GameCharacter(melee);
        player1.attack();  // 输出: 使用近战攻击

        GameCharacter player2 = new GameCharacter(ranged);
        player2.attack();  // 输出: 使用远程攻击

    }
}

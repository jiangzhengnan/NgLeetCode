package com.ng.code.designmodel.创建型.工厂模式;

/**
 * @author : 
 * @creation : 2022/08/14
 * @description :
 * 抽象工厂模式:
 * 可以让一个工厂负责创建多个不同类型的对象（IRuleConfigParser、ISystemConfigParser等），
 * 而不是只创建一种parser对象。这样就可以有效地减少工厂类的个数
 */
public class AbstractFactory {

    public static class RuleConfig {

    }

    public interface IRuleConfigParser {
        RuleConfig parse(String s);
    }

    public interface ISystemConfigParser {
        RuleConfig parse(String s);
    }

    public interface IConfigParserFactory {
        IRuleConfigParser createRuleParser();

        ISystemConfigParser createSystemParser();
        //此处可以扩展新的parser类型，比如IBizConfigParser
    }

    public static class JsonRuleConfigParser implements IRuleConfigParser {
        @Override
        public RuleConfig parse(final String s) {
            return null;
        }
    }

    public static class XmlRuleConfigParser implements IRuleConfigParser {
        @Override
        public RuleConfig parse(final String s) {
            return null;
        }
    }

    public static class JsonSystemConfigParser implements ISystemConfigParser {
        @Override
        public RuleConfig parse(final String s) {
            return null;
        }
    }

    public static class XmlSystemConfigParser implements ISystemConfigParser {
        @Override
        public RuleConfig parse(final String s) {
            return null;
        }
    }

    public class JsonConfigParserFactory implements IConfigParserFactory {
        @Override
        public IRuleConfigParser createRuleParser() {
            return new JsonRuleConfigParser();
        }

        @Override
        public ISystemConfigParser createSystemParser() {
            return new JsonSystemConfigParser();
        }
    }

    public class XmlConfigParserFactory implements IConfigParserFactory {
        @Override
        public IRuleConfigParser createRuleParser() {
            return new XmlRuleConfigParser();
        }

        @Override
        public ISystemConfigParser createSystemParser() {
            return new XmlSystemConfigParser();
        }
    }

}

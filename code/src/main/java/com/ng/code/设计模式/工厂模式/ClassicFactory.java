package com.ng.code.设计模式.工厂模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/08/14
 * @description :
 * 经典工厂模式
 */
public class ClassicFactory {

    public static class RuleConfig {

    }

    public interface IRuleConfigParser {
        RuleConfig parse(String s);
    }

    public interface IRuleConfigParserFactory {
        IRuleConfigParser createParser();
    }

    public static class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {
        @Override
        public IRuleConfigParser createParser() {
            return new JsonRuleConfigParser();
        }
    }

    public static class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
        @Override
        public IRuleConfigParser createParser() {
            return new XmlRuleConfigParser();
        }
    }

    public static class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
        @Override
        public IRuleConfigParser createParser() {
            return new YamlRuleConfigParser();
        }
    }

    public static class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
        @Override
        public IRuleConfigParser createParser() {
            return new PropertiesRuleConfigParser();
        }
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

    public static class YamlRuleConfigParser implements IRuleConfigParser {
        @Override
        public RuleConfig parse(final String s) {
            return null;
        }
    }

    public static class PropertiesRuleConfigParser implements IRuleConfigParser {
        @Override
        public RuleConfig parse(final String s) {
            return null;
        }
    }

    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
            //throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        IRuleConfigParser parser = parserFactory.createParser();

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    //因为工厂类只包含方法，不包含成员变量，完全可以复用，
    //不需要每次都创建新的工厂类对象，所以，简单工厂模式的第二种实现思路更加合适。
    public static class RuleConfigParserFactoryMap { //工厂的工厂
        public static final Map<String, IRuleConfigParserFactory> cachedFactories = new HashMap<>();

        static {
            cachedFactories.put("json", new JsonRuleConfigParserFactory());
            cachedFactories.put("xml", new XmlRuleConfigParserFactory());
            cachedFactories.put("yaml", new YamlRuleConfigParserFactory());
            cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
        }

        public static IRuleConfigParserFactory getParserFactory(String type) {
            if (type == null || type.isEmpty()) {
                return null;
            }
            IRuleConfigParserFactory parserFactory = cachedFactories.get(type.toLowerCase());
            return parserFactory;
        }

    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}

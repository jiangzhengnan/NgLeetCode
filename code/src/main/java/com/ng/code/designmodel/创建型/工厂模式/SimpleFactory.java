package com.ng.code.designmodel.创建型.工厂模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 
 * @creation : 2022/08/14
 * @description :
 * 简单工厂模式
 */
public class SimpleFactory {

    public interface IRuleConfigParser {
    }

    public static class JsonRuleConfigParser implements IRuleConfigParser{
    }

    public static class XmlRuleConfigParser implements IRuleConfigParser{
    }

    public static class YamlRuleConfigParser implements IRuleConfigParser{
    }

    public static class PropertiesRuleConfigParser implements IRuleConfigParser{
    }

    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("yaml", new YamlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算
        }
        IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
        return parser;
    }
}

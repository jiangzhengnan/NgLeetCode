package com.ng.code.设计模式.行为型.责任链模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/10/05
 * @description :
 * 概念：
 * 多个处理器依次处理同一个请求。
 * 一个请求先经过A处理器处理，然后再把请求传递给B处理器，B处理器处理完后再传递给C处理器，
 * 以此类推，形成一个链条。链条上的每个处理器各自承担各自的处理职责，所以叫作责任链模式。
 * 示例：
 * 敏感词过滤器，包括广告过滤，色情过滤
 */
public class 责任链模式 {

    //过滤器
    public interface SensitiveWordFilter {
        boolean doFilter(String content);
    }

    //色情过滤
    static class SexyWordFilter implements SensitiveWordFilter {
        @Override
        public boolean doFilter(String content) {
            boolean legal = true;
            //...
            return legal;
        }
    }

    //广告过滤
    static class AdsWordFilter implements SensitiveWordFilter {

        @Override
        public boolean doFilter(final String content) {
            boolean legal = true;
            //...
            return legal;
        }
    }

    /**
     * 过滤器责任链
     */
    static class SensitiveWordFilterChain {
        private List<SensitiveWordFilter> filters = new ArrayList<>();

        public void addFilter(SensitiveWordFilter filter) {
            this.filters.add(filter);
        }

        // return true if content doesn't contain sensitive words.
        public boolean filter(String content) {
            for (SensitiveWordFilter filter : filters) {
                if (!filter.doFilter(content)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        SensitiveWordFilterChain filterChain = new SensitiveWordFilterChain();
        filterChain.addFilter(new AdsWordFilter());
        filterChain.addFilter(new SexyWordFilter());

        boolean legal = filterChain.filter("一条评论");
        if (!legal) {
            // 不发表
            System.out.println("过滤");
        } else {
            // 发表
            System.out.println("合格");
        }
    }

}

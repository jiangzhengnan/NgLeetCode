package com.cj.design.uc.temp;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/05/18
 * @description :
 * 创建一个ClassLoader的子类，将PathClassLoader中classTable字段的值复制过来，就能通过findLoadedClass方法间接访问PathClassLoader的classTable，从而实现查询自定义类的加载情况的目的。
 *     	//创建ClassLoader的子类
 *        class ClassLoadChecker extends ClassLoader;
 *        //将PathClassLoader的classTable字段值复制过来
 *        ClassLoadChecker.classTable= PathClassLoader.classTable;
 *        //通过完整类名(包名+类名，如com.amap.model.user)查询类是否已加载
 *        boolean isLoaded=ClassLoadChecker.findLoadedClass(className);
 * 绕开黑灰名单
 * Android P以后，官方将classTable成员变量加入了黑灰名单，想要通过反射访问，需要绕开SDK限制。我们的策略是采用采用元反射结合设置豁免的方式来达到目的，具体的实现可以参考github上的开源项目FreeReflection，想要了解更多可自行Google之。
 */
public class XerathTodo {
}

/*
说明文档(unicode 格式)
\u0068\u006f\u006f\u006b\u0020\u7ebf\u7a0b\u521b\u5efa\u7684\u65b9\u5f0f\u000d\u000a\u56e0\u4e3a\u7ebf\u7a0b\u7684\u521b\u5efa\uff0c\u90fd\u662f\u5728\u540c\u4e00\u4e2a\u5730\u65b9\uff0c\u53ef\u4ee5\u0020\u0068\u006f\u006f\u006b\u0020\u7cfb\u7edf\u0020\u0063\u0072\u0065\u0061\u0074\u0065\u005f\u0074\u0068\u0072\u0065\u0061\u0064\u0020\u5bf9\u5e94\u7684\u65b9\u6cd5\uff0c\u8fdb\u884c\u5806\u6808\u6253\u5370\u3002\u8fd9\u79cd\u65b9\u6848\u4e0d\u9700\u8981\u4f9d\u8d56\u4ee3\u7801\uff0c\u5e76\u4e14\u0020\u006e\u0061\u0074\u0069\u0076\u0065\u0020\u521b\u5efa\u7684\u7ebf\u7a0b\u4e5f\u53ef\u4ee5\u6536\u96c6\uff0c\u76ee\u524d\u5fae\u4fe1\u7684\u004d\u0061\u0074\u0072\u0069\u0078\u0020\u91cc\u9762\u7684\u7684\u0020\u0070\u0074\u0068\u0072\u0065\u0061\u0064\u0020\u6a21\u5757\u5df2\u7ecf\u63d0\u4f9b\u4e86\u76f8\u5173\u80fd\u529b\uff0c\u7ecf\u8fc7\u5206\u6790\u7b26\u5408\u6211\u4eec\u7684\u8bc9\u6c42\uff0c\u56e0\u6b64\u6211\u4eec\u4f7f\u7528\u7684\u662f\u8fd9\u79cd\u65b9\u5f0f\u3002\u000d\u000a\u901a\u8fc7\u0020\u0070\u0074\u0068\u0072\u0065\u0061\u0064\u6536\u96c6\u5230\u7684\u7ebf\u7a0b\u5bf9\u5e94\u7684\u5806\u6808\u683c\u5f0f\u5982\u4e0b\uff1a\u000d\u000a\u0020\u0020\u007b\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0068\u0061\u0073\u0068\u0026\u0071\u0075\u006f\u0074\u003b\u003a\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0033\u0030\u0033\u0035\u0038\u0039\u0035\u0037\u0039\u0030\u0032\u0034\u0039\u0032\u0037\u0035\u0036\u0032\u0031\u0026\u0071\u0075\u006f\u0074\u003b\u002c\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u006e\u0061\u0074\u0069\u0076\u0065\u0026\u0071\u0075\u006f\u0074\u003b\u003a\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0023\u0070\u0063\u0020\u0034\u0031\u0066\u0039\u0034\u0020\u0028\u006e\u0075\u006c\u006c\u0029\u0020\u0028\u002f\u0064\u0061\u0074\u0061\u002f\u0061\u0070\u0070\u002f\u007e\u007e\u0059\u0045\u0033\u0031\u0048\u0053\u0075\u0038\u0050\u0052\u0056\u0054\u006c\u0038\u002d\u0052\u0042\u0053\u006e\u0043\u0074\u0077\u003d\u003d\u002f\u0063\u006f\u006d\u002e\u0055\u0043\u004d\u006f\u0062\u0069\u006c\u0065\u002d\u0031\u0032\u0068\u0051\u004f\u0068\u0034\u0067\u0063\u006c\u0075\u0071\u0076\u004e\u0063\u0075\u0036\u006f\u0075\u0052\u0073\u0077\u003d\u003d\u002f\u006c\u0069\u0062\u002f\u0061\u0072\u006d\u002f\u006c\u0069\u0062\u0077\u0065\u0063\u0068\u0061\u0074\u0062\u0061\u0063\u006b\u0074\u0072\u0061\u0063\u0065\u002e\u0073\u006f\u0029\u003b\u0023\u0070\u0063\u0020\u0032\u0061\u0030\u0033\u0020\u0028\u006e\u0075\u006c\u006c\u0029\u0020\u0028\u002f\u0064\u0061\u0074\u0061\u002f\u0061\u0070\u0070\u002f\u007e\u007e\u0059\u0045\u0033\u0031\u0048\u0053\u0075\u0038\u0050\u0052\u0056\u0054\u006c\u0038\u002d\u0052\u0042\u0053\u006e\u0043\u0074\u0077\u003d\u003d\u002f\u0063\u006f\u006d\u002e\u0055\u0043\u004d\u006f\u0062\u0069\u006c\u0065\u002d\u0031\u0032\u0068\u0051\u004f\u0068\u0034\u0067\u0063\u006c\u0075\u0071\u0076\u004e\u0063\u0075\u0036\u006f\u0075\u0052\u0073\u0077\u003d\u003d\u002f\u006c\u0069\u0062\u002f\u0061\u0072\u006d\u002f\u006c\u0069\u0062\u006d\u0061\u0074\u0072\u0069\u0078\u002d\u0070\u0074\u0068\u0072\u0065\u0061\u0064\u0068\u006f\u006f\u006b\u002e\u0073\u006f\u0029\u003b\u0023\u0070\u0063\u0020\u0032\u0038\u0061\u0035\u0020\u0028\u006e\u0075\u006c\u006c\u0029\u0020\u0028\u002f\u0064\u0061\u0074\u0061\u002f\u0061\u0070\u0070\u002f\u007e\u007e\u0059\u0045\u0033\u0031\u0048\u0053\u0075\u0038\u0050\u0052\u0056\u0054\u006c\u0038\u002d\u0052\u0042\u0053\u006e\u0043\u0074\u0077\u003d\u003d\u002f\u0063\u006f\u006d\u002e\u0055\u0043\u004d\u006f\u0062\u0069\u006c\u0065\u002d\u0031\u0032\u0068\u0051\u004f\u0068\u0034\u0067\u0063\u006c\u0075\u0071\u0076\u004e\u0063\u0075\u0036\u006f\u0075\u0052\u0073\u0077\u003d\u003d\u002f\u006c\u0069\u0062\u002f\u0061\u0072\u006d\u002f\u006c\u0069\u0062\u006d\u0061\u0074\u0072\u0069\u0078\u002d\u0070\u0074\u0068\u0072\u0065\u0061\u0064\u0068\u006f\u006f\u006b\u002e\u0073\u006f\u0029\u003b\u0023\u0070\u0063\u0020\u0035\u0035\u0061\u0031\u0020\u0028\u006e\u0075\u006c\u006c\u0029\u0020\u0028\u002f\u0064\u0061\u0074\u0061\u002f\u0061\u0070\u0070\u002f\u007e\u007e\u0059\u0045\u0033\u0031\u0048\u0053\u0075\u0038\u0050\u0052\u0056\u0054\u006c\u0038\u002d\u0052\u0042\u0053\u006e\u0043\u0074\u0077\u003d\u003d\u002f\u0063\u006f\u006d\u002e\u0055\u0043\u004d\u006f\u0062\u0069\u006c\u0065\u002d\u0031\u0032\u0068\u0051\u004f\u0068\u0034\u0067\u0063\u006c\u0075\u0071\u0076\u004e\u0063\u0075\u0036\u006f\u0075\u0052\u0073\u0077\u003d\u003d\u002f\u006c\u0069\u0062\u002f\u0061\u0072\u006d\u002f\u006c\u0069\u0062\u006d\u0061\u0074\u0072\u0069\u0078\u002d\u0070\u0074\u0068\u0072\u0065\u0061\u0064\u0068\u006f\u006f\u006b\u002e\u0073\u006f\u0029\u003b\u0023\u0070\u0063\u0020\u0065\u0035\u0030\u0036\u0030\u0036\u0031\u0030\u0020\u0028\u006e\u0075\u006c\u006c\u0029\u0020\u0028\u006e\u0075\u006c\u006c\u0029\u003b\u0026\u0071\u0075\u006f\u0074\u003b\u002c\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u006a\u0061\u0076\u0061\u0026\u0071\u0075\u006f\u0074\u003b\u003a\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0063\u006f\u006d\u002e\u0071\u0071\u002e\u0065\u002e\u0063\u006f\u006d\u006d\u002e\u006e\u0065\u0074\u002e\u004e\u0065\u0074\u0077\u006f\u0072\u006b\u0043\u006c\u0069\u0065\u006e\u0074\u0049\u006d\u0070\u006c\u002e\u0073\u0075\u0062\u006d\u0069\u0074\u0028\u0055\u006e\u006b\u006e\u006f\u0077\u006e\u0020\u0053\u006f\u0075\u0072\u0063\u0065\u003a\u0031\u0035\u0029\u003b\u0063\u006f\u006d\u002e\u0071\u0071\u002e\u0065\u002e\u0063\u006f\u006d\u006d\u002e\u006e\u0065\u0074\u002e\u004e\u0065\u0074\u0077\u006f\u0072\u006b\u0043\u006c\u0069\u0065\u006e\u0074\u0049\u006d\u0070\u006c\u002e\u0073\u0075\u0062\u006d\u0069\u0074\u0028\u0055\u006e\u006b\u006e\u006f\u0077\u006e\u0020\u0053\u006f\u0075\u0072\u0063\u0065\u003a\u0032\u0029\u003b\u0063\u006f\u006d\u002e\u0071\u0071\u002e\u0065\u002e\u0063\u006f\u006d\u006d\u002e\u0061\u002e\u0061\u002e\u0061\u0028\u0055\u006e\u006b\u006e\u006f\u0077\u006e\u0020\u0053\u006f\u0075\u0072\u0063\u0065\u003a\u0038\u0036\u0029\u003b\u0063\u006f\u006d\u002e\u0071\u0071\u002e\u0065\u002e\u0063\u006f\u006d\u006d\u002e\u006d\u0061\u006e\u0061\u0067\u0065\u0072\u0073\u002e\u0047\u0044\u0054\u0041\u0044\u004d\u0061\u006e\u0061\u0067\u0065\u0072\u002e\u0061\u0028\u0055\u006e\u006b\u006e\u006f\u0077\u006e\u0020\u0053\u006f\u0075\u0072\u0063\u0065\u003a\u0031\u0035\u0029\u003b\u0063\u006f\u006d\u002e\u0071\u0071\u002e\u0065\u002e\u0063\u006f\u006d\u006d\u002e\u006d\u0061\u006e\u0061\u0067\u0065\u0072\u0073\u002e\u0047\u0044\u0054\u0041\u0044\u004d\u0061\u006e\u0061\u0067\u0065\u0072\u0024\u0031\u002e\u0072\u0075\u006e\u0028\u0055\u006e\u006b\u006e\u006f\u0077\u006e\u0020\u0053\u006f\u0075\u0072\u0063\u0065\u003a\u0037\u0029\u003b\u0026\u0071\u0075\u006f\u0074\u003b\u002c\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0063\u006f\u0075\u006e\u0074\u0026\u0071\u0075\u006f\u0074\u003b\u003a\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0031\u0026\u0071\u0075\u006f\u0074\u003b\u002c\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0074\u0068\u0072\u0065\u0061\u0064\u0073\u0026\u0071\u0075\u006f\u0074\u003b\u003a\u0020\u005b\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u007b\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0074\u0069\u0064\u0026\u0071\u0075\u006f\u0074\u003b\u003a\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0031\u0038\u0030\u0035\u0037\u0026\u0071\u0075\u006f\u0074\u003b\u002c\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u006e\u0061\u006d\u0065\u0026\u0071\u0075\u006f\u0074\u003b\u003a\u0020\u0026\u0071\u0075\u006f\u0074\u003b\u0070\u006f\u006f\u006c\u002d\u0031\u0032\u002d\u0074\u0068\u0072\u0065\u0061\u0064\u002d\u0026\u0071\u0075\u006f\u0074\u003b\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u007d\u000d\u000a\u0020\u0020\u0020\u0020\u0020\u0020\u005d\u000d\u000a\u0020\u0020\u0020\u0020\u007d\u000d\u000a\u53ef\u4ee5\u62ff\u5230\u89e6\u53d1\u7ebf\u7a0b\u521b\u5efa\u7684\u6574\u4e2a\u94fe\u8def\uff0c\u5728\u6536\u96c6\u5230\u7ebf\u7a0b\u4f7f\u7528\u60c5\u51b5\u4e4b\u540e\uff0c\u6211\u4eec\u901a\u8fc7\u811a\u672c\u5bf9\u5176\u8fdb\u884c\u7b80\u5355\u7684\u4e1a\u52a1\u5f52\u7c7b\uff0c\u5982\u4e0b\u662f\u811a\u672c\u5206\u6790\u751f\u6210\u7684\u0020\u0065\u0078\u0063\u0065\u006c\u0020\u8868\uff0c\u57fa\u672c\u4e0a\u5c31\u77e5\u9053\u4e86\u90fd\u662f\u54ea\u4e9b\u4e1a\u52a1\u5728\u54ea\u4e2a\u5730\u65b9\u521b\u5efa\u4e86\u7ebf\u7a0b\u3002\u000d\u000a\u000d\u000a\u000d\u000a\u4f18\u5316\u65b9\u5411\uff1a\u000d\u000a\u0031\u002e\u7edf\u4e00\u6539\u9020\u7ebf\u7a0b\u6c60\uff0c\u57fa\u4e8e\u4f18\u9177\u7684\u0020\u004f\u006e\u0065\u0053\u0063\u0068\u0065\u0064\u0075\u006c\u0065\u0072\u0020\u7ebf\u7a0b\u6c60\uff0c\u57fa\u4e8e\u4ed6\u8fdb\u884c\u6539\u9020\u5c01\u88c5\u000d\u000a\u0032\u002e\u5b57\u8282\u7801\u7edf\u4e00\u4fee\u6539\u000d\u000a\u000d\u000a\u0031\u002e\u002e\u8bbe\u7f6e\u5141\u8bb8\u6838\u5fc3\u7ebf\u7a0b\u5728\u7a7a\u95f2\u65f6\u81ea\u52a8\u9500\u6bc1\uff1b\u000d\u000a\u0032\u002e\u002e\u9650\u5236\u7a7a\u95f2\u7ebf\u7a0b\u5b58\u6d3b\u65f6\u95f4\uff0c\u006b\u0065\u0065\u0070\u0041\u006c\u0069\u0076\u0065\u0054\u0069\u006d\u0065\u8bbe\u7f6e\u5c0f\u4e00\u70b9\uff0c\u4f8b\u5982\u0020\u0033\u0073\u000d\u000a\u8bbe\u7f6e\u6838\u5fc3\u7ebf\u7a0b\u5141\u8bb8\u9500\u6bc1\uff0c\u5e76\u4e14\u0020\u006b\u0065\u0065\u0070\u0041\u006c\u0069\u0076\u0065\u0054\u0069\u006d\u0065\u0020\u8bbe\u7f6e\u4e3a\u0020\u0035\u0073\uff1a\u8fd9\u79cd\u8bbe\u7f6e\u5bf9\u4e8e\u4e1a\u52a1\u6570\u636e\u6ca1\u6709\u8d1f\u5411\u5f71\u54cd\u000d\u000a\u652f\u6301\u5168\u5c40\u52a8\u6001\u56de\u6eda\u0026\u5b9a\u5411\u4e1a\u52a1\u56de\u6eda\u80fd\u529b\uff1a\u5f00\u5173\u5206\u522b\u63a7\u5236\uff0c\u6709\u95ee\u9898\u4f18\u5316\u53ef\u4ee5\u76f4\u63a5\u5173\u95ed\u000d\u000a\u4e0d\u9002\u5408\u91ca\u653e\u7ebf\u7a0b\u573a\u666f\u589e\u52a0\u9ed8\u8ba4\u767d\u540d\u5355\u000d\u000a\u9891\u7e41\u4f7f\u7528\u7684\u573a\u666f\u4e0d\u505a\u91ca\u653e\uff1a\u4f8b\u5982\u0020\u0055\u0054\u0020\u7684\u7ebf\u7a0b\u6c60\uff0c\u5982\u679c\u9891\u7e41\u521b\u5efa\uff0c\u7070\u5ea6\u8fc7\u7a0b\u53d1\u73b0\u4f1a\u589e\u5927\u0020\u0041\u004e\u0052\u0020\u7684\u6982\u7387\uff0c\u56e0\u4e3a\u7ebf\u7a0b\u7684\u521b\u5efa\uff0c\u5b58\u5728\u51e0\u6beb\u79d2\u7684\u8017\u65f6\uff1b\u000d\u000a\u5355\u7ebf\u7a0b\u4e14\u4f7f\u7528\u5230\u4e86\u0020\u0054\u0068\u0072\u0065\u0061\u0064\u004c\u006f\u0063\u0061\u006c\u0020\u7684\u573a\u666f\uff1a\u8fd9\u79cd\u573a\u666f\u4f1a\u5bfc\u81f4\u7ebf\u7a0b\u8bbe\u7f6e\u7684\u6570\u636e\u4e22\u5931\u000d\u000a\u4ece\u0020\u0041\u0042\u0020\u7ed3\u679c\u770b\uff0c\u8fd9\u79cd\u4f18\u5316\u65b9\u5f0f\u53ef\u4ee5\u5927\u5927\u51cf\u5c11\u7ebf\u7a0b\u6570\u91cf\u5cf0\u503c\uff08\u0031\u0030\u0030\u002b\uff09\u000d\u000a\u0048\u0061\u006e\u0064\u006c\u0065\u0072\u0054\u0068\u0072\u0065\u0061\u0064\u0020\u4f18\u5316\u000d\u000a\u7ebf\u7a0b\u7684\u6570\u91cf\u9664\u4e86\u4e0a\u9762\u8bf4\u7684\u7ebf\u7a0b\u6c60\uff0c\u6bd4\u8f83\u5e38\u7528\u5230\u7684\u8fd8\u6709\u0048\u0061\u006e\u0064\u006c\u0065\u0072\u0054\u0068\u0072\u0065\u0061\u0064\u548c\u0054\u0068\u0072\u0065\u0061\u0064\u3002\u4ee3\u7801\u91cc\u9762\u521b\u5efa\u0020\u0048\u0061\u006e\u0064\u006c\u0065\u0072\u0054\u0068\u0072\u0065\u0061\u0064\u6709\u0020\u0031\u0030\u0030\u0020\u591a\u5904\uff0c\u4f46\u662f\u5927\u90e8\u5206\u7684\u4e1a\u52a1\u90fd\u662f\u53ea\u4f7f\u7528\u4e86\u51e0\u6b21\u4e4b\u540e\uff0c\u540e\u7eed\u5c31\u57fa\u672c\u4e0d\u4f7f\u7528\u4e86\uff0c\u4f46\u56e0\u4e3a\u4e1a\u52a1\u9000\u51fa\u5e76\u6ca1\u6709\u660e\u663e\u7684\u8fb9\u754c\uff0c\u8fd0\u884c\u8fc7\u7a0b\u4e2d\u8fd8\u662f\u6709\u53ef\u80fd\u4f7f\u7528\u5230\uff0c\u6240\u4ee5\u5e76\u6ca1\u6709\u8c03\u7528\u0020\u0071\u0075\u0069\u0074\u505a\u9000\u51fa\u64cd\u4f5c\u3002\u0020\u8fd9\u79cd\u5176\u5b9e\u662f\u53ef\u4ee5\u4f7f\u7528\u5168\u5c40\u7684\u0048\u0061\u006e\u0064\u006c\u0065\u0072\u0054\u0068\u0072\u0065\u0061\u0064\u8fdb\u884c\u66ff\u6362\u4f18\u5316\u7684\uff0c\u8fd8\u662f\u8ddf\u4e0a\u9762\u7684\u601d\u8def\u4e00\u6837\uff0c\u901a\u8fc7\u4f7f\u7528\u0020\u0041\u0053\u004d\u0020\u63d2\u6869\u7684\u80fd\u529b\uff1a\u000d\u000a\u5c06\u006e\u0065\u0077\u0020\u0048\u0061\u006e\u0064\u006c\u0065\u0072\u0054\u0068\u0072\u0065\u0061\u0064\u7684\u5730\u65b9\uff0c\u66ff\u6362\u4e3a\u4e86\u5168\u5c40\u7edf\u4e00\u7684\u0020\u0048\u0061\u006e\u0064\u006c\u0065\u0072\u0054\u0068\u0072\u0065\u0061\u0064\uff1b\u000d\u000a\u5168\u5c40\u0020\u0048\u0061\u006e\u0064\u006c\u0065\u0072\u0054\u0068\u0072\u0065\u0061\u0064\u0020\u652f\u6301\u8bbe\u7f6e\u6700\u5927\u4e2a\u6570\uff1b\u000d\u000a\u76d1\u63a7\u6240\u6709\u7684\u4efb\u52a1\u8017\u65f6\uff0c\u5982\u679c\u4efb\u52a1\u8017\u65f6\u8f83\u5927\uff0c\u52a0\u5165\u767d\u540d\u5355\uff0c\u907f\u514d\u5f71\u54cd\u6574\u4f53\u7684\u6267\u884c\uff1b\u000d\u000a\u8fd9\u4e00\u6b65\u4f18\u5316\u5728\u542f\u52a8\u540e\u4e00\u6bb5\u65f6\u95f4\u4e0d\u64cd\u4f5c\u7684\u9636\u6bb5\uff0c\u5c31\u53ef\u4ee5\u51cf\u5c11\u0020\u0033\u0030\u002b\u0020\u7684\u7ebf\u7a0b\u6570\u3002\u000d\u000a\u006e\u0065\u0077\u0020\u0054\u0068\u0072\u0065\u0061\u0064\u0020\u4f18\u5316\u000d\u000a\u5f53\u6211\u4eec\u8c03\u7528\u0020\u006e\u0065\u0077\u0020\u0054\u0068\u0072\u0065\u0061\u0064\u0020\u7684\u65f6\u5019\uff0c\u53ea\u662f\u5355\u7eaf\u521b\u5efa\u4e86\u4e00\u4e2a\u5bf9\u8c61\uff0c\u53ea\u6709\u8c03\u7528\u4e86\u0020\u0073\u0074\u0061\u0072\u0074\u65b9\u6cd5\uff0c\u624d\u4f1a\u8c03\u7528\u0020\u006e\u0061\u0074\u0069\u0076\u0065\u0020\u5c42\u53bb\u521b\u5efa\u7ebf\u7a0b\uff0c\u6240\u4ee5\u6211\u4eec\u5728\u7f16\u8bd1\u671f\uff0c\u6211\u4eec\u5c06\u6240\u6709\u7684\u006e\u0065\u0077\u0020\u0054\u0068\u0072\u0065\u0061\u0064\uff0c\u5168\u90e8\u66ff\u6362\u6210\u6211\u4eec\u81ea\u5b9a\u4e49\u7684\u0020\u006e\u0065\u0077\u0020\u0055\u0043\u0054\u0068\u0072\u0065\u0061\u0064\u3002\u5728\u81ea\u5b9a\u4e49\u7684\u0020\u0055\u0043\u0054\u0068\u0072\u0065\u0061\u0064\u91cc\u9762\u91cd\u5199\u0020\u0073\u0074\u0061\u0072\u0074\u65b9\u6cd5\uff0c\u4e0d\u53bb\u542f\u52a8\u7ebf\u7a0b\uff0c\u800c\u662f\u5c06\u4efb\u52a1\u653e\u5230\u6211\u4eec\u4e0a\u9762\u7684\u7edf\u4e00\u7ebf\u7a0b\u6c60\u4e2d\u53bb\u6267\u884c\uff0c\u53ef\u4ee5\u8fdb\u4e00\u6b65\u51cf\u5c11\u7ebf\u7a0b\u7684\u521b\u5efa\u3002\u000d\u000a\u8fd9\u4e00\u6b65\u4f18\u5316\u53ef\u4ee5\u518d\u53ef\u4ee5\u51cf\u5c11\u0020\u0031\u0030\u002b\u0020\u0020\u7684\u7ebf\u7a0b\u6570\u3002
 */
//
//
///**
// * 字节替换类
// * 将 from 类型 完全替换为 to 类型，包含 属性、方法、注解、入参
// */
//public class CustomClassAdapter extends ClassVisitor {
//
//    private final Type from;
//    private final Type to;
//    private final String fileName;
//
//    public  CustomClassAdapter(String fileName, Type from, Type to, ClassVisitor next) {
//        super(Opcodes.ASM5, next);
//        this.fileName = fileName;
//        this.from = from;
//        this.to = to;
//    }
//
//    @Override
//    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        super.visit(version, access, name, signature, redirectInternalName(superName, "visit"), interfaces);
//    }
//
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
//        if (mv != null) {
//            mv = new ConfigMethodAdapter(mv);
//        }
//        return mv;
//    }
//
//    private String redirectInternalName(String value, String type) {
//        if (value.equals(from.getInternalName())) {
//            Logger.d("FakeClassAdapter", fileName, from.getInternalName(), to.getInternalName(), " type ", type);
//            CustomClassPrintlnHelper.add("fileName " + fileName + " from " + from.getInternalName() + " to " + to.getInternalName() + " type " + type);
//        }
//        return RedirectionUtils.redirect(value, from.getInternalName(), to.getInternalName());
//    }
//
//
//    private String redirectDescriptor(String value) {
//        if (value == null) {
//            return null;
//        }
//        Logger.d("FakeClassAdapter.redirectDescriptor", fileName, from.getDescriptor(), to.getDescriptor(), " value ", value);
//        return value.replace(from.getDescriptor(), to.getDescriptor());
//    }
//
//    private class ConfigMethodAdapter extends MethodVisitor {
//
//        private ConfigMethodAdapter(MethodVisitor mv) {
//            super(Opcodes.ASM5, mv);
//        }
//
//        @Override
//        public void visitTypeInsn(int opcode, String type) {
//            if (opcode == Opcodes.NEW
//                || opcode == Opcodes.ANEWARRAY) {
//                mv.visitTypeInsn(opcode, redirectInternalName(type, "visitTypeInsn1 " + opcode));
//            } else {
//                super.visitTypeInsn(opcode, type);
//            }
//        }
//
//        @Override
//        public void visitMethodInsn(int opcode, String owner, String name, String desc) {
//            if (opcode == Opcodes.INVOKESTATIC && !owner.equals("java/util/concurrent/Executors")) {
//                super.visitMethodInsn(opcode, owner, name, desc);
//            } else {
//                if (opcode == Opcodes.INVOKESPECIAL || opcode == Opcodes.INVOKESTATIC) {
//                    super.visitMethodInsn(opcode, redirectInternalName(owner, " visitMethodInsn1 " + opcode), name, desc);
//                } else {
//                    super.visitMethodInsn(opcode, owner, name, desc);
//                }
//            }
//        }
//
//        @Override
//        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
//            // 如果是静态方法操作并且不是从 Executors 过来的，直接忽略
//            if (opcode == Opcodes.INVOKESTATIC && !owner.equals("java/util/concurrent/Executors")) {
//                super.visitMethodInsn(opcode, owner, name, desc, itf);
//            } else {
//                // Opcodes.INVOKESPECIAL 这个指令是跟构造函数相关的，需要进行替换
//                if (opcode == Opcodes.INVOKESPECIAL || opcode == Opcodes.INVOKESTATIC) {
//                    super.visitMethodInsn(opcode, redirectInternalName(owner, "visitMethodInsn2 " + opcode), name, desc, itf);
//                } else {
//                    super.visitMethodInsn(opcode, owner, name, desc, itf);
//                }
//            }
//        }
//
//        @Override
//        public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
//            return super.visitTypeAnnotation(typeRef, typePath, redirectDescriptor(desc), visible);
//        }
//
//        @Override
//        public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
//            return super.visitParameterAnnotation(parameter, redirectDescriptor(desc), visible);
//        }
//    }
//}
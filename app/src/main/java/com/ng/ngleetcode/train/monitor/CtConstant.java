package com.ng.ngleetcode.train.monitor;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/12/07
 * @description :
 * 耗时结点
 */
public class CtConstant {

    public static class Key {

        public static final String SSP_FROM = "ssp_from";

        public static final String SLOT_ID = "slot_id";

        public static final String LEVEL_NODE_TYPE = "level_node_type";

        public static final String LEVEL_ID = "level_id";

        public static final String ADN_ID = "adn_id";

        public static final String PLACEMENT_ID = "placement_id";

        //是否需要oat，1是0否
        public static final String IS_OAT = "is_oat";

        public static final String INSTALL_MODULES = "install_modules";

    }

    public static class Value {

        public static final String SSP_FROM_LOCAL = "local";

        public static final String SSP_FROM_NET = "net";

    }

}

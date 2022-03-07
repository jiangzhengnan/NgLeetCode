/*
 * Copyright (C) 2004 - 2017 UCWeb Inc. All Rights Reserved.
 * Description :
 *
 * Creation    :  2017-01-22
 * Author      : zhaomin.gzm@alibaba-inc.com
 */

package com.ng.code.util;

public class IdGenerator {

    private static int id_base = 0x60000000;

    public static int generateID() {
        return id_base++;
    }


}

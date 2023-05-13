
package com.ng.code.util;

public class IdGenerator {

    private static int id_base = 0x60000000;

    public static int generateID() {
        return id_base++;
    }


}

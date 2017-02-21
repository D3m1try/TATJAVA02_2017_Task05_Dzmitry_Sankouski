package com.eatmeat.veiw;

import java.util.Formatter;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        formatter.format("%1$-50s | %2$-50s", "sdfgsdgsdfgsgsd", "jkl;jkl;jjkl;jkl;j");
        builder.append("\n");
        formatter.format("%1$-50s | %2$-50s", "sdfgsdg", "kl;jkl;j");
        System.out.println(builder.toString());


    }
}

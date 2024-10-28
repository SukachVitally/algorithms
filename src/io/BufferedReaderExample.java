package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String args[]) {
        try(BufferedReader breader = new BufferedReader(new FileReader("resources/test.txt"))) {
            int i = 0;
            // read first five chars
            while(i <= 5) {
                System.out.print((char)breader.read());
                System.out.print("|");
                i++;
            }
            // add line break
            System.out.println();
            // place mark at 6th position
            breader.mark(1);
            i = 0;
            // read next five chars
            while(i <= 5) {
                System.out.print((char)breader.read());
                System.out.print("*");
                i++;
            }
            // reset to sixth character
            breader.reset();
            i = 0;
            // line break
            System.out.println();
            // read next five chars
            while(i <= 10) {
                System.out.print((char)breader.read());
                i++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

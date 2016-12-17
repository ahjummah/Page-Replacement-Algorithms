
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jessa
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Menu:\n1.FIFO\n2.LFU\n3.LRU");
        System.out.println("Choice: ");
        int choice = 0;
        do {
            choice = scan.nextInt();
            if (choice > 3 || choice < 1) {
                System.out.println("Invalid choice. Try again: ");
            }
        } while (choice < 1 || choice > 3);

        System.out.print("Length of reference:");

        int length = scan.nextInt();
        int[] ref = new int[length];
        for (int i = 0; i < length; i++) {
            System.out.print("Reference [" + (i + 1) + "]: ");
            ref[i] = scan.nextInt();
        }

        System.out.println(Arrays.toString(ref));
        System.out.print("Page frame(s): ");
        int frames = scan.nextInt();
        if (choice == 1) {
            FIFO(ref, frames);
        }

    }

    private static void FIFO(int[] ref, int size) {
        int replace = 0;
        int faults = 0;
        ArrayList table = new ArrayList();
        for (int i = 0; i < ref.length; i++) {
            if (table.contains(ref[i])) {
                System.out.println("H: " + table.toString());
            } else if (table.size() < size) {
                table.add(ref[i]);
                System.out.println("F: " + table.toString());
                faults++;
            } else {
                table.set(replace, ref[i]);
                System.out.println("F: " + table.toString());
                replace++;
                faults++;
            }
            if (replace == size) {
                replace = 0;
            }
        }
        System.out.println("Page Faults: " + faults);
    }
}

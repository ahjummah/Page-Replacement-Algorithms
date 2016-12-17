
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

    ArrayList frames;
    int[] ref;

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        Main menu = new Main();
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
        menu.ref = new int[length];

        for (int i = 0; i < length; i++) {
            System.out.print("Reference [" + (i + 1) + "]: ");
            menu.ref[i] = scan.nextInt();
        }
        System.out.println(Arrays.toString(menu.ref));
        System.out.print("Page frame(s): ");

        int size = scan.nextInt();
        switch (choice) {
            case 1:
                menu.FIFO(menu.ref, size);
                break;
            case 3:
                menu.LRU(menu.ref, size);
                break;
            case 2:
                menu.LFU(menu.ref, size);
                break;
            default:
                break;
        }

    }

    private void FIFO(int[] ref, int size) {
        int replace = 0;
        int faults = 0;
        frames = new ArrayList();
        for (int i = 0; i < ref.length; i++) {
            if (frames.contains(ref[i])) {
                System.out.println("H: " + frames.toString());
            } else if (frames.size() < size) {
                frames.add(ref[i]);
                System.out.println("F: " + frames.toString());
                faults++;
            } else {
                frames.set(replace, ref[i]);
                System.out.println("F: " + frames.toString());
                replace++;
                faults++;
            }
            if (replace == size) {
                replace = 0;
            }
        }
        System.out.println("Page Faults: " + faults);
    }

    private void LRU(int[] ref, int size) {
        int faults = 0;
        int replace = 0;
        int[] count = new int[size];
        frames = new ArrayList();
        int j = 0;
        for (int i = 0; i < ref.length; i++) {
            if (frames.contains(ref[i])) {
                System.out.println("H: " + frames.toString());
                count[frames.indexOf(ref[i])] = i + 1;

            } else if (frames.size() < size) {
                frames.add(ref[i]);
                System.out.println("F: " + frames.toString());
                count[j] = i;
                faults++;
            } else {
                replace = getMin(count);
                frames.set(replace, ref[i]);
                System.out.println("F: " + frames.toString());
                count[replace] = i;
                faults++;
            }

            if (j >= size) {
                j = 0;
            }
            j++;
        }
        System.out.println("Page Faults: " + faults);
    }

    private int getMin(int[] count) {
        int min = 0;
        //stable ni sha si FIFO jpun
        for (int i = 0; i < count.length; i++) {
            if (count[min] > count[i]) {
                min = i;
            }
        }
        return min;
    }

    private void LFU(int[] ref, int size) {
        int f, p, num = 0, min = 0;
        int faults = 0;
        frames = new ArrayList();
        int freq[] = new int[3];
        int count[] = new int[10];
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            frames.add(" ");
            freq[i] = -1;
        }

        for (int i = 0; i < ref.length; i++) {
            flag = true;
            int page = ref[i];

            for (int j = 0; j < size; j++) {
                if (frames.get(j).equals(page)) {
                    flag = false;
                    count[page]++;
                    System.out.println("H:" + frames.toString());
                    break;
                }
            }
            if (flag) {
                if (i >= 3) {
                    for (int j = 0; j < size; j++) {
                        num = (int) frames.get(j);
                        freq[j] = count[num];
                    }
                    min = freq[0];
                    for (int j = 0; j < size; j++) {
                        if (freq[j] < min) {
                            min = freq[j];
                        }
                    }
                    for (int j = 0; j < size; j++) {
                        if (freq[j] == min) {
                            count[page]++;
                            frames.set(j, page);
                            System.out.println("F:" + frames.toString());
                            faults++;
                            break;
                        }
                    }
                } else {
                    frames.set(i, page);
                    count[page]++;
                    System.out.println("F:" + frames.toString());
                    faults++;
                }
            }

        }
        System.out.println("Page Faults: " + faults);
    }
}

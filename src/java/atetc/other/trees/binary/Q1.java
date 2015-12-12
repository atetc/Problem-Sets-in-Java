package atetc.other.trees.binary;

import java.util.Arrays;
import java.util.Collections;

/**
 * From Akvelon set 26
 *
 * Given a binary tree print it in inward spiral order i.e first print level 1, then level n, then level 2, then n-1 and so on.
 * For Ex:
 * 1
 * 2 3
 * 4 5 6 7
 * 8 9 10 11 12 13 14 15
 *
 * Print- 1 15 14 13 12 11 10 9 8 2 3 7 6 5 4
 *
 * Extend the algorithm to n-ary tree.
 */
public class Q1 {

    public static int[][] printTreeFromSpiralArray(int[] arr, int n) {
        if (n == 0) {
            return null;
        }

        int[][] result = new int[n][];
        result[0] = new int[]{arr[0]};

        int level = 0;
        int topCounter = 0;
        int bottomCounter = 0;
        boolean forward = true;

        while (level < n && topCounter < arr.length) {
            int topLevelSize = (int) Math.pow(2, level);
            int bottomLevelSize = (int) Math.pow(2, n - level);

            if (forward) {
                result[level] = Arrays.copyOfRange(arr, topCounter, topLevelSize);
            } else {
                result[level] = Arrays.copyOfRange(arr, bottomCounter, bottomLevelSize);
                Collections.reverse(Arrays.asList(result[level]));
            }

            topCounter += topLevelSize;
            bottomCounter += bottomLevelSize;

            forward = !forward;
            level++;
        }

        return result;
    }
}

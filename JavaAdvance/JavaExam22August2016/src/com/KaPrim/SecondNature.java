package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SecondNature {

    public static void main(String[] args) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	    String[] inputFlowers = reader.readLine().split(" ");
	    String[] inputBuckets = reader.readLine().split(" ");

        int[] flowers = new int[inputFlowers.length];
        int[] buckets = new int[inputBuckets.length];

        for (int i = inputBuckets.length - 1; i >= 0 ; i--) {
           buckets[inputBuckets.length - i - 1] = Integer.parseInt(inputBuckets[i]);
        }

        for (int i = 0; i < inputFlowers.length; i++) {
            flowers[i] = Integer.parseInt(inputFlowers[i]);
        }

        List<Integer> bloomed = new LinkedList<>();
        int counter = 0;
        for (int i = 0; i < flowers.length; i++) {
            if(counter >= buckets.length) {
                break;
            }
            for (int j = counter; j < buckets.length; j++) {
                if ( IntStream.of(flowers).sum() != 0) {
                    if (flowers[i] - buckets[j] > 0 ) {
                        flowers[i] -= buckets[j];
                        buckets[j] = 0;
                    } else {
                        if(flowers[i] - buckets[j] == 0) {
                            bloomed.add(flowers[i]);
                            buckets[j] = 0;
                            flowers[i] = 0;
                        } else {
                            if (j+1 < buckets.length) {
                                buckets[j+1] += (buckets[j] - flowers[i]);
                                buckets[j] = 0;
                                counter++;
                                flowers[i] = 0;
                                break;
                            }
                            flowers[i] = 0;
                        }

                    }
                }
                counter++;
            }
        }

        if (IntStream.of(flowers).sum() == 0) {
            for (Integer bucket : buckets) {
                if (bucket > 0) {
                    System.out.print(bucket + " ");
                }
            }
            System.out.println();
        } else {
            for (int flower : flowers) {
                if (flower > 0) {
                    System.out.print(flower + " ");
                }
            }
            System.out.println();
        }

        if (bloomed.size() == 0) {
            System.out.print("None");
        } else {
            for (Integer integer : bloomed) {
                System.out.print(integer + " ");
            }
        }

    }
}

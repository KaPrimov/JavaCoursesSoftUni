package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Earthquake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<ArrayDeque<Integer>> sequences = new ArrayDeque<>();
        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> seismicities = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String[] integers = reader.readLine().split(" ");
            ArrayDeque<Integer> newDeque = new ArrayDeque<>();
            for (String integer : integers) {
                newDeque.offer(Integer.parseInt(integer));
            }
            sequences.offer(newDeque);
        }
        boolean flag = false;
        while (true) {
            if(sequences.size() == 0) {
                break;
            }
            ArrayDeque<Integer> currentDeque = sequences.poll();

            while (true) {
                if(currentDeque.size() == 0 || flag) {
                    if(flag) {
                        flag = false;
                    }
                    break;
                }
                int currentSeimicity = currentDeque.poll();
                seismicities.add(currentSeimicity);
                int next = 0;
                if(currentDeque.size() > 0) {
                    next = currentDeque.poll();
                } else {
                    break;
                }

                while (true) {
                    if(currentSeimicity < next) {
                        currentDeque.offerFirst(next);
                        if(sequences.size() > 0) {
                            sequences.offerLast(currentDeque);
                            flag = true;
                        }
                        break;
                    }

                    if(currentDeque.size() <= 0) {
                        break;
                    }
                    next = currentDeque.poll();
                }

            }

        }

        StringBuilder answer = new StringBuilder();
        answer.append(seismicities.size()).append("\n");
        while (seismicities.size() > 0) {
           answer.append(seismicities.poll()).append(" ");
        }
        System.out.println(answer);

    }
}

package com.KaPrim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ParkingSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> steps = new ArrayList<String>();
        String[] inputDimensions = scanner.nextLine().split(" ");
        int[] matrixDimensions = new int[2];
        for (int i = 0; i < inputDimensions.length; i++) {
            matrixDimensions[i] = Integer.parseInt(inputDimensions[i]);
        }

        HashMap<Integer, HashSet<Integer>> parkingMatrix = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 0; i < matrixDimensions[0]; i++) {
            parkingMatrix.put(i, new HashSet<Integer>());
        }

        String inputLine = scanner.nextLine();
        while(!inputLine.equals("stop")){
            String[] indexes = inputLine.split(" ");
            Integer enteringRow = Integer.parseInt(indexes[0]);
            Integer desiredRow = Integer.parseInt(indexes[1]);
            Integer desiredCol = Integer.parseInt(indexes[2]);

            Boolean hasParked = false;

            Integer initialSteps = 1 + Math.abs(desiredRow - enteringRow) + desiredCol;
            Integer currentSteps = initialSteps;
            Integer finalCol = -1;

            if(parkingMatrix.get(desiredRow).contains(desiredCol)){
                for (int i = desiredCol - 1; i >= 1; i--) {
                    if(!parkingMatrix.get(desiredRow).contains(i)){
                        finalCol = i;
                        currentSteps = initialSteps - (desiredCol - i);
                        hasParked = true;
                        break;
                    }
                }

                if(hasParked){
                    int distanceTraversed = desiredCol + (desiredCol - finalCol);
                    if(distanceTraversed >= matrixDimensions[1]){
                        distanceTraversed = matrixDimensions[1];
                    }
                    for (int i = desiredCol + 1; i < distanceTraversed; i++) {
                        if(!parkingMatrix.get(desiredRow).contains(i)){
                            finalCol = i;
                            currentSteps = initialSteps + (i - desiredCol);
                            break;
                        }
                    }
                }
                else{
                    for (int i = desiredCol + 1; i < matrixDimensions[1]; i++) {
                        if(!parkingMatrix.get(desiredRow).contains(i)){
                            finalCol = i;
                            currentSteps = initialSteps + (i - desiredCol);
                            hasParked = true;
                            break;
                        }
                    }
                }
            }
            else{
                finalCol = desiredCol;
                hasParked = true;
            }

            if(hasParked){
                parkingMatrix.get(desiredRow).add(finalCol);
                steps.add(currentSteps.toString());
            }
            else{
                steps.add(String.format("Row %s full", desiredRow));
            }

            inputLine = scanner.nextLine();
        }

        System.out.println(String.join("\n", steps));
    }
}

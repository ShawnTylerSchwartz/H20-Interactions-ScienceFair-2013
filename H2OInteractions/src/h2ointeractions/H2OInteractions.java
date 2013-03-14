package h2ointeractions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Shawn Tyler Schwartz
 * @class Period 5, Honors Chemistry 10B
 * @school High Tech Los Angeles
 * @advisors Wun Chiou & Daniel Perahya
 * @fileName "H2OInteractions.java" (Main Class)
 * @version 1.0 - 2013 LA County Science Fair Edition
 **/
//NOTE: Starting with 2 molecules first.

public class H2OInteractions {
    
    /* REACTION MAP:
     * Hydrogen-Hydrogen Bonds:
     *  1. leftHydrogenOnex1y1 --> leftHydrogenTwox2y2
     *  2. leftHydrogenOnex1y1 --> rightHydrogenTwox2y2
     *  3. rightHydrogenOnex1y1 --> leftHydrogenTwox2y2
     *  4. rightHydrogenOnex1y1 --> rightHydrogenTwox2y2
     * 
     * Oxygen-Oxygen Bond:
     *  5. oxygenOnex1y1 --> oxygenTwox2y2
     * 
     * Hydrogen-Oxygen Bonds:
     *  6. leftHydrogenOnex1y1 --> oxygenOnex1y1
     *  7. rightHydrogenOnex1y1 --> oxygenTwox2y2
     *  8. leftHydrogenTwox2y2 --> oxygenOnex1y1
     *  9. rightHydrogenTwox2y2 --> oxygenOnex1y1
     */
        
    //Arrays
    public static double initialLeftHydrogenOneCoord[];
    public static double initialRightHydrogenOneCoord[];
    public static double initialOxygenOneCoord[];
    public static double initialOxygenTwoCoord[];
    public static double initialLeftHydrogenTwoCoord[];
    public static double initialRightHydrogenTwoCoord[];
    public static String arrayContentstoPrint;
    
    //3D Matrix - Final Coordinates After Rotation
    public static double finalLeftHydrogenOneCoordRotated[];
    public static double finalRightHydrogenOneCoordRotated[];
    public static double finalOxygenOneCoordRotated[];
    public static double finalOxygenTwoCoordRotated[];
    public static double finalLeftHydrogenTwoCoordRotated[];
    public static double finalRightHydrogenTwoCoordRotated[];
    
    //3D Matrix - Fianl Coordinates After Translation
    public static double finalLeftHydrogenOneCoordTranslated[];
    public static double finalRightHydrogenOneCoordTranslated[];
    public static double finalOxygenOneCoordTranslated[];
    public static double finalOxygenTwoCoordTranslated[];
    public static double finalLeftHydrogenTwoCoordTranslated[];
    public static double finalRightHydrogenTwoCoordTranslated[];
    
    //Rotation Matrix Constants
    public static double rotateAboutXFunctionMatrix[][][];
    public static double rotateAboutYFunctionMatrix[][][];
    public static double rotateAboutZFunctionMatrix[][][];
    
    //Rotation (About X-Axis) Matrix Cell Value Variables
    public static double rotationMatrixXCellZEROZEROZERO;
    public static double rotationMatrixXCellZEROZEROONE;
    public static double rotationMatrixXCellZEROZEROTWO;
    public static double rotationMatrixXCellZEROONEZERO;
    public static double rotationMatrixXCellZEROONEONE;
    public static double rotationMatrixXCellZEROONETWO;
    public static double rotationMatrixXCellZEROTWOZERO;
    public static double rotationMatrixXCellZEROTWOONE;
    public static double rotationMatrixXCellZEROTWOTWO;
    
    //Variables for Calculations
    public static double distance;
    public static double totalDistance;
    public static double singleUsableDistance;
    public static double pointChargeEnergy;
    public static double totalPointChargeEnergy;
    public static double dipoleMomentChargeEnergy;
    public static double totalDipoleMomentChargeEnergy;
        
    //Water Molecule One (1) Initial Positions
    public static double initialLeftHydrogenOnex1Pos;
    public static double initialLeftHydrogenOney1Pos;
    public static double initialLeftHydrogenOnez1Pos;
    public static double initialOxygenOnex1Pos;
    public static double initialOxygenOney1Pos;
    public static double initialOxygenOnez1Pos;
    public static double initialRightHydrogenOnex1Pos;
    public static double initialRightHydrogenOney1Pos;
    public static double initialRightHydrogenOnez1Pos;
    
    //Water Molecule Two (2) Initial Positions
    public static double initialLeftHydrogenTwox2Pos;
    public static double initialLeftHydrogenTwoy2Pos;
    public static double initialLeftHydrogenTwoz2Pos;
    public static double initialOxygenTwox2Pos;
    public static double initialOxygenTwoy2Pos;
    public static double initialOxygenTwoz2Pos;
    public static double initialRightHydrogenTwox2Pos;
    public static double initialRightHydrogenTwoy2Pos;
    public static double initialRightHydrogenTwoz2Pos;
    
    //Water Molecule One (1) Rotated Positions
    public static double rotatedLeftHydrogenOnex1Pos;
    public static double rotatedLeftHydrogenOney1Pos;
    public static double rotatedLeftHydrogenOnez1Pos;
    public static double rotatedOxygenOnex1Pos;
    public static double rotatedOxygenOney1Pos;
    public static double rotatedOxygenOnez1Pos;
    public static double rotatedRightHydrogenOnex1Pos;
    public static double rotatedRightHydrogenOney1Pos;
    public static double rotatedRightHydrogenOnez1Pos;
    
    //Water Molecule Two (2) Rotated Positions
    public static double rotatedLeftHydrogenTwox2Pos;
    public static double rotatedLeftHydrogenTwoy2Pos;
    public static double rotatedLeftHydrogenTwoz2Pos;
    public static double rotatedOxygenTwox2Pos;
    public static double rotatedOxygenTwoy2Pos;
    public static double rotatedOxygenTwoz2Pos;
    public static double rotatedRightHydrogenTwox2Pos;
    public static double rotatedRightHydrogenTwoy2Pos;
    public static double rotatedRightHydrogenTwoz2Pos;
    
    //Iteration Energy Storing Variables
    public static double firstIteration; //leftHydrogenOnex1y1 --> leftHydrogenTwox2y2
    public static double secondIteration; //leftHydrogenOnex1y1 --> rightHydrogenTwox2y2
    public static double thirdIteration; //rightHydrogenOnex1y1 --> leftHydrogenTwox2y2
    public static double fourthIterartion; //rightHydrogenOnex1y1 --> rightHydrogenTwox2y2
    public static double fifthIteration; //oxygenOnex1y1 --> oxygenTwox2y2
    public static double sixthIteration; //leftHydrogenOnex1y1 --> oxygenOnex1y1
    public static double seventhIteration; //rightHydrogenOnex1y1 --> oxygenTwox2y2
    public static double eighthIteration; //leftHydrogenTwox2y2 --> oxygenOnex1y1
    public static double ninthIteration; //rightHydrogenTwox2y2 --> oxygenOnex1y1
    
    //Constants to (-->) Variables
    public static double hydrogenCharge = Constants.KhydrogenPointCharge;
    public static double oxygenCharge = Constants.KoxygenPointCharge;
    
    public static void main(String[] args) throws IOException {
            rotationAboutXMatrix(2);
        try {
            // Create file 
            FileWriter fstream = new FileWriter("newoutput.txt");
            BufferedWriter outputWriter = new BufferedWriter(fstream);
            initialLeftHydrogenOneCoord = new double[3];
            initialRightHydrogenOneCoord = new double[3];
            initialOxygenOneCoord = new double[3];
            initialOxygenTwoCoord = new double[3];
            initialLeftHydrogenTwoCoord = new double[3];
            initialRightHydrogenTwoCoord = new double[3];
                 
//            for(int i = 0; i < 3; i++) {
//                initialLeftHydrogenOneCoord[i] = (double) (Math.random()*4);
//                initialRightHydrogenOneCoord[i] = (double) (Math.random()*4);
//                initialOxygenOneCoord[i] = (double) (Math.random()*4);
//                initialOxygenTwoCoord[i] = (double) (Math.random()*4);
//                initialLeftHydrogenTwoCoord[i] = (double) (Math.random()*4);
//                initialRightHydrogenTwoCoord[i] = (double) (Math.random()*4);
//            }
                        
//            double[][] rotateAboutXFunction = new double[][] {
//                { 1, 3, 3 }, // First List
//                { 1, 3, 3 }  // Second List
//            };
            
            /*
             * double[][][] numbers = new double[][][]	{
	    {
		{  12.44, 525.38,  -6.28,  2448.32, 632.04 },
		{-378.05,  48.14, 634.18,   762.48,  83.02 },
		{  64.92,  -7.44,  86.74,  -534.60, 386.73 }
	    },
	    {
		{  48.02, 120.44,   38.62,  526.82, 1704.62 },
		{  56.85, 105.48,  363.31,  172.62,  128.48 },
		{  906.68, 47.12, -166.07, 4444.26,  408.62 }
	    }
	};
        * 
        * numbers[0][0][0] = 12.44;
	numbers[0][1][1] = 525.38;
	numbers[0][2][2] = -6.28;
	numbers[0][0][3] = 2448.32;
	numbers[0][1][4] = 632.04;
	numbers[0][2][0] = -378.05;
	numbers[0][0][1] = 48.14;
	numbers[0][1][2] = 634.18;
	numbers[0][2][3] = 762.48;
	numbers[0][0][4] = 83.02;
	numbers[0][1][0] = 64.92;
	numbers[0][2][1] = -7.44;
	numbers[0][0][2] = 86.74;
	numbers[0][1][3] = -534.60;
	numbers[0][2][4] = 386.73;
	numbers[1][0][0] = 48.02;
	numbers[1][1][1] = 120.44;
	numbers[1][2][2] = 38.62;
	numbers[1][0][3] = 526.82;
	numbers[1][1][4] = 1704.62;
	numbers[1][2][0] = 56.85;
	numbers[1][0][1] = 105.48;
	numbers[1][1][2] = 363.31;
	numbers[1][2][3] = 172.62;
	numbers[1][0][4] = 128.48;
	numbers[1][1][0] = 906.68;
	numbers[1][2][1] = 47.12;
	numbers[1][0][2] = -166.07;
	numbers[1][1][3] = 4444.26;
	numbers[1][2][4] = 408.62;
             */
                        
            //Initial Position of First Water Molecule in System
            initialLeftHydrogenOneCoord[0] = -24;
            initialLeftHydrogenOneCoord[1] = 0;
            initialLeftHydrogenOneCoord[2] = 93;
            
            initialOxygenOneCoord[0] = 0;
            initialOxygenOneCoord[1] = 0;
            initialOxygenOneCoord[2] = 0;
            
            initialRightHydrogenOneCoord[0] = 96;
            initialRightHydrogenOneCoord[1] = 0;
            initialRightHydrogenOneCoord[2] = 0;
            
            
            
            
            
            outputWriter.write("The randomly generated molecules: \n\n");
            outputWriter.write("leftHydrogenOneCoord: " + Arrays.toString(initialLeftHydrogenOneCoord) + "\n");
            outputWriter.write("rightHydrogenOneCoord: " + Arrays.toString(initialRightHydrogenOneCoord) + "\n");
            outputWriter.write("oxygenOneCoord: " + Arrays.toString(initialOxygenOneCoord) + "\n");
            outputWriter.write("oxygenTwoCoord: " + Arrays.toString(initialOxygenTwoCoord) + "\n");
            outputWriter.write("leftHydrogenTwoCoord: " + Arrays.toString(initialLeftHydrogenTwoCoord) + "\n");
            outputWriter.write("rightHydrogenTwoCoord: " + Arrays.toString(initialRightHydrogenTwoCoord) + "\n");
            outputWriter.close();
        } 
        catch (Exception e){ //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
//        for(int i = 0; i < 3; i++) {
//            initialLeftHydrogenOnex1Pos = initialLeftHydrogenOneCoord[0];
//            initialLeftHydrogenOney1Pos = initialLeftHydrogenOneCoord[1];
//            initialLeftHydrogenOnez1Pos = initialLeftHydrogenOneCoord[2];
//            initialLeftHydrogenTwox2Pos = initialLeftHydrogenTwoCoord[0];
//            initialLeftHydrogenTwoy2Pos = initialLeftHydrogenTwoCoord[1];
//            initialLeftHydrogenTwoz2Pos = initialLeftHydrogenTwoCoord[2];
//            initialOxygenOnex1Pos = initialOxygenOneCoord[0];
//            initialOxygenOney1Pos = initialOxygenOneCoord[1];
//            initialOxygenOnez1Pos = initialOxygenOneCoord[2];
//            initialOxygenTwox2Pos = initialOxygenTwoCoord[0];
//            initialOxygenTwoy2Pos = initialOxygenTwoCoord[1];
//            initialOxygenTwoz2Pos = initialOxygenTwoCoord[2];
//            initialRightHydrogenOnex1Pos = initialRightHydrogenOneCoord[0];
//            initialRightHydrogenOney1Pos = initialRightHydrogenOneCoord[1];
//            initialRightHydrogenOnez1Pos = initialRightHydrogenOneCoord[2];
//            initialRightHydrogenTwox2Pos = initialRightHydrogenTwoCoord[0];
//            initialRightHydrogenTwoy2Pos = initialRightHydrogenTwoCoord[1];
//            initialRightHydrogenTwoz2Pos = initialRightHydrogenTwoCoord[2];
//        }
        
          for(int i = 0; i < 3; i++) {
            initialLeftHydrogenOnex1Pos = initialLeftHydrogenOneCoord[0];
            initialLeftHydrogenOney1Pos = initialLeftHydrogenOneCoord[1];
            initialLeftHydrogenOnez1Pos = initialLeftHydrogenOneCoord[2];
            initialLeftHydrogenTwox2Pos = initialLeftHydrogenTwoCoord[0];
            initialLeftHydrogenTwoy2Pos = initialLeftHydrogenTwoCoord[1];
            initialLeftHydrogenTwoz2Pos = initialLeftHydrogenTwoCoord[2];
            initialOxygenOnex1Pos = initialOxygenOneCoord[0];
            initialOxygenOney1Pos = initialOxygenOneCoord[1];
            initialOxygenOnez1Pos = initialOxygenOneCoord[2];
            initialOxygenTwox2Pos = initialOxygenTwoCoord[0];
            initialOxygenTwoy2Pos = initialOxygenTwoCoord[1];
            initialOxygenTwoz2Pos = initialOxygenTwoCoord[2];
            initialRightHydrogenOnex1Pos = initialRightHydrogenOneCoord[0];
            initialRightHydrogenOney1Pos = initialRightHydrogenOneCoord[1];
            initialRightHydrogenOnez1Pos = initialRightHydrogenOneCoord[2];
            initialRightHydrogenTwox2Pos = initialRightHydrogenTwoCoord[0];
            initialRightHydrogenTwoy2Pos = initialRightHydrogenTwoCoord[1];
            initialRightHydrogenTwoz2Pos = initialRightHydrogenTwoCoord[2];
        }
        
        System.out.println("leftHydrogenOnex1: " + initialLeftHydrogenOnex1Pos);
        System.out.println("leftHydrogenOney1: " + initialLeftHydrogenOney1Pos);
        System.out.println("leftHydrogenOnez1: " + initialLeftHydrogenOnez1Pos);
        System.out.println("leftHydrogenTwox2: " + initialLeftHydrogenTwox2Pos);
        System.out.println("leftHydrogenTwoy2: " + initialLeftHydrogenTwoy2Pos);
        System.out.println("leftHydrogenTwoz2: " + initialLeftHydrogenTwoz2Pos);
        System.out.println("oxygenOnex1: " + initialOxygenOnex1Pos);
        System.out.println("oxygenOney1: " + initialOxygenOney1Pos);
        System.out.println("oxygenOnez1: " + initialOxygenOnez1Pos);
        System.out.println("oxygenTwox2: " + initialOxygenTwox2Pos);
        System.out.println("oxygenTwoy2: " + initialOxygenTwoy2Pos);
        System.out.println("oxygenTwoz2: " + initialOxygenTwoz2Pos);
        System.out.println("rightHydrogenOnex1: " + initialRightHydrogenOnex1Pos);
        System.out.println("rightHydrogenOney1: " + initialRightHydrogenOney1Pos);
        System.out.println("rightHydrogenOnez1: " + initialRightHydrogenOnez1Pos);
        System.out.println("rightHydrogenTwox2: " + initialRightHydrogenTwox2Pos);
        System.out.println("rightHydrogenTwoy2: " + initialRightHydrogenTwoy2Pos);
        System.out.println("rightHydrogenTwoz2: " + initialRightHydrogenTwoz2Pos);
        System.out.println("distance: " + distance);
        System.out.println("This is my calculation: " + calculatePointCharge(initialLeftHydrogenOnex1Pos, initialLeftHydrogenTwox2Pos, initialLeftHydrogenOney1Pos, initialLeftHydrogenTwoy2Pos, initialLeftHydrogenOnez1Pos, initialLeftHydrogenTwoz2Pos, distance));
        System.out.println(readFile("newoutput.txt"));
        
        firstIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        secondIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        thirdIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        fourthIterartion = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        fifthIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        sixthIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        seventhIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        eighthIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        ninthIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        totalPointChargeEnergy = firstIteration+secondIteration+thirdIteration+fourthIterartion+fifthIteration+sixthIteration
                +seventhIteration+eighthIteration+ninthIteration;
        
        System.out.println("Sigma Point Charge Calculation: " + totalPointChargeEnergy);

    }
   
    private static String readFile(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        try {
            FileChannel fc = inputStream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            //Instead of using default, pass in a decoder.
            return Charset.defaultCharset().decode(bb).toString();
        }
        finally {
            inputStream.close();
        }
    }
    
    private static void sopl(String userInput){
        System.out.println(userInput);
    }
    
    public static double calculatePointCharge(double moleculeOneX1, double moleculeTwoX2, double moleculeOneY1, double moleculeTwoY2, double moleculeOneZ1, double moleculeTwoZ2, double distance) {
        distance = Math.sqrt(Math.sqrt(Math.pow(moleculeTwoX2-moleculeOneX1, 2) + Math.pow(moleculeTwoY2-moleculeOneY1, 2) + Math.pow(moleculeTwoZ2-moleculeOneZ1, 2)));
        pointChargeEnergy = Constants.kConstant*hydrogenCharge*oxygenCharge/Math.abs(distance);
        return pointChargeEnergy;
    }
    
    public static double rotateMatrixX(double molecule, double theta) {
        
        return 0;
    }
    
    public static double caclulateDipoleMoment(double momentOne, double momentTwo, double distance) {
        return 0;
    }
    
    public static void rotationAboutXMatrix(double theta) {
            double cosTheta = Math.cos(theta);
            double sinTheta = Math.sin(theta);
            double array[][] = {{1,0,4},{0,cosTheta,-sinTheta},{0,sinTheta,cosTheta}};
            double array1[][] = {{-24,0,93},{0,0,0},{96,0,0}}; //xyz
            double array2[][] = new double[3][3];
            int x = 3;
            System.out.println("Matrix 1 : ");
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < x; j++) {
                    System.out.print(" "+ array[i][j]);
                }
            System.out.println();
            }  
            int y = 3;
            System.out.println("Matrix 2 : ");
            for(int i = 0; i < y; i++) {
                for(int j = 0; j < y; j++) {
                System.out.print(" "+array1[i][j]);
            }  
                System.out.println();
            }
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    for(int k = 0; k < y; k++){
                        array2[i][j] += array[i][k]*array1[k][j];
                    }
                }  
            }
            System.out.println("Multiply of both matrix : ");
                for(int i = 0; i < x; i++) {
                    for(int j = 0; j < y; j++) {
                        System.out.print(" "+array2[i][j]);
                    }  
                    System.out.println();
                }
    }
}
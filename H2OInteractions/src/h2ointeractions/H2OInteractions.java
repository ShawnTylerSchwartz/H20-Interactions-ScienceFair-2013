package h2ointeractions;

import com.sun.org.glassfish.external.amx.AMX;
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
import java.util.Scanner;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
    
    //Variables for Calculations
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
    
    //Iteration Energy Storing Variables
    public static double firstCalcIteration; //leftHydrogenOnex1y1 --> leftHydrogenTwox2y2
    public static double secondCalcIteration; //leftHydrogenOnex1y1 --> rightHydrogenTwox2y2
    public static double thirdCalcIteration; //rightHydrogenOnex1y1 --> leftHydrogenTwox2y2
    public static double fourthCalcIterartion; //rightHydrogenOnex1y1 --> rightHydrogenTwox2y2
    public static double fifthCalcIteration; //oxygenOnex1y1 --> oxygenTwox2y2
    public static double sixthCalcIteration; //leftHydrogenOnex1y1 --> oxygenOnex1y1
    public static double seventhCalcIteration; //rightHydrogenOnex1y1 --> oxygenTwox2y2
    public static double eighthCalcIteration; //leftHydrogenTwox2y2 --> oxygenOnex1y1
    public static double ninthCalcIteration; //rightHydrogenTwox2y2 --> oxygenOnex1y1
    
    public static double finalRotatedXArrayOne[][];
    public static double finalRotatedYArrayOne[][];
    public static double finalRotatedZArrayOne[][]; //TODO: Implement the matrix calculations to store into these variables.
    public static double finalRotatedXArrayTwo[][];
    public static double finalRotatedYArrayTwo[][];
    public static double finalRotatedZArrayTwo[][];
    
    public static double finalTranslatedArrayOne[][];
    public static double finalTranslatedArrayTwo[][];
    
    public static double finalRotLeftHydrogenOneXPos;
    public static double finalRotLeftHydrogenOneYPos;
    public static double finalRotLeftHydrogenOneZPos;
    public static double finalRotOxygenOneXPos;
    public static double finalRotOxygenOneYPos;
    public static double finalRotOxygenOneZPos;
    public static double finalRotRightHydrogenOneXPos;
    public static double finalRotRightHydrogenOneYPos;
    public static double finalRotRighyHydrogenOneZPos;
    
    public static double finalRotLeftHydrogenTwoXPos;
    public static double finalRotLeftHydrogenTwoYPos;
    public static double finalRotLeftHydrogenTwoZPos;
    public static double finalRotOxygenTwoXPos;
    public static double finalRotOxygenTwoYPos;
    public static double finalRotOxygenTwoZPos;
    public static double finalRotRightHydrogenTwoXPos;
    public static double finalRotRightHydrogenTwoYPos;
    public static double finalRotRightHydrogenTwoZPos;
    
    
    public static double finalTransLeftHydrogenOneXPos;
    public static double finalTransLeftHydrogenOneYPos;
    public static double finalTransLeftHydrogenOneZPos;
    public static double finalTransOxygenOneXPos;
    public static double finalTransOxygenOneYPos;
    public static double finalTransOxygenOneZPos;
    public static double finalTransRightHydrogenOneXPos;
    public static double finalTransRightHydrogenOneYPos;
    public static double finalTransRightHydrogenOneZPos;
    
    public static double finalTransLeftHydrogenTwoXPos;
    public static double finalTransLeftHydrogenTwoYPos;
    public static double finalTransLeftHydrogenTwoZPos;
    public static double finalTransOxygenTwoXPos;
    public static double finalTransOxygenTwoYPos;
    public static double finalTransOxygenTwoZPos;
    public static double finalTransRightHydrogenTwoXPos;
    public static double finalTransRightHydrogenTwoYPos;
    public static double finalTransRightHydrogenTwoZPos;
    
    public static double randomTheta;
    public static double randomTranslationConstant;
      
    
    public static void main(String[] args) throws IOException {
            
            double rangeStartRandomTheta = 0;
            double rangeEndRandomTheta = Math.toRadians(360);
            Random randomGenRandomTheta = new Random();
            randomThetaGenerator(rangeStartRandomTheta, rangeEndRandomTheta, randomGenRandomTheta);
            System.out.println("Random Angle: " + randomTheta);
            rotationAboutXYZMatrix(randomTheta); //(theta)
            
            double rangeStartRandomTranslationConstant = 3;
            double rangeEndRandomTranslationConstant = 350;
            Random randomGenTranslationConstant = new Random();
            randomTranslationConstantGenerator(rangeStartRandomTranslationConstant, rangeEndRandomTranslationConstant,randomGenTranslationConstant);
            System.out.println("Random Translation Constant: " +randomTranslationConstant);
            
            randomTranslateXYZ();
            
            finalRotLeftHydrogenOneXPos = finalRotatedZArrayOne[0][0];
            finalRotLeftHydrogenOneYPos = finalRotatedZArrayOne[0][1];
            finalRotLeftHydrogenOneZPos = finalRotatedZArrayOne[0][2];
            finalRotOxygenOneXPos = finalRotatedZArrayOne[1][0];
            finalRotOxygenOneYPos = finalRotatedZArrayOne[1][1];
            finalRotOxygenOneZPos = finalRotatedZArrayOne[1][2];
            finalRotRightHydrogenOneXPos = finalRotatedZArrayOne[2][0];
            finalRotRightHydrogenOneYPos = finalRotatedZArrayOne[2][1];
            finalRotRighyHydrogenOneZPos = finalRotatedZArrayOne[2][2];
            
            finalRotLeftHydrogenTwoXPos = finalRotatedZArrayTwo[0][0];
            finalRotLeftHydrogenTwoYPos = finalRotatedZArrayTwo[0][1];
            finalRotLeftHydrogenTwoZPos = finalRotatedZArrayTwo[0][2];
            finalRotOxygenTwoXPos = finalRotatedZArrayTwo[1][0];
            finalRotOxygenTwoYPos = finalRotatedZArrayTwo[1][1];
            finalRotOxygenTwoZPos = finalRotatedZArrayTwo[1][2];
            finalRotRightHydrogenTwoXPos = finalRotatedZArrayTwo[2][0];
            finalRotRightHydrogenTwoYPos = finalRotatedZArrayTwo[2][1];
            finalRotRightHydrogenTwoZPos = finalRotatedZArrayTwo[2][2];
            
            finalTransLeftHydrogenOneXPos = finalTranslatedArrayOne[0][0];
            finalTransLeftHydrogenOneYPos = finalTranslatedArrayOne[0][1];
            finalTransLeftHydrogenOneZPos = finalTranslatedArrayOne[0][2];
            finalTransOxygenOneXPos = finalTranslatedArrayOne[1][0];
            finalTransOxygenOneYPos = finalTranslatedArrayOne[1][1];
            finalTransOxygenOneZPos = finalTranslatedArrayOne[1][2];
            finalTransRightHydrogenOneXPos = finalTranslatedArrayOne[2][0];
            finalTransRightHydrogenOneYPos = finalTranslatedArrayOne[2][1];
            finalTransRightHydrogenOneZPos = finalTranslatedArrayOne[2][2];
            
            finalTransLeftHydrogenTwoXPos = finalTranslatedArrayTwo[0][0];
            finalTransLeftHydrogenTwoYPos = finalTranslatedArrayTwo[0][1];
            finalTransLeftHydrogenTwoZPos = finalTranslatedArrayTwo[0][2];
            finalTransOxygenTwoXPos = finalTranslatedArrayTwo[1][0];
            finalTransOxygenTwoYPos = finalTranslatedArrayTwo[1][1];
            finalTransOxygenTwoZPos = finalTranslatedArrayTwo[1][2];
            finalTransRightHydrogenTwoXPos = finalTranslatedArrayTwo[2][0];
            finalTransRightHydrogenTwoYPos = finalTranslatedArrayTwo[2][1];
            finalTransRightHydrogenTwoZPos = finalTranslatedArrayTwo[2][2];
            

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
        System.out.println(readFile("newoutput.txt"));
        
        firstCalcIteration = calculatePointCharge(finalTransLeftHydrogenOneXPos, finalTransLeftHydrogenOneXPos, finalTransLeftHydrogenOneYPos, finalTransLeftHydrogenTwoXPos, finalTransLeftHydrogenOneZPos, finalTransLeftHydrogenTwoZPos);
        secondCalcIteration = calculatePointCharge(finalTransLeftHydrogenOneXPos, finalTransLeftHydrogenOneYPos, finalTransLeftHydrogenOneZPos, finalTransRightHydrogenTwoXPos, finalTransRightHydrogenTwoYPos, finalTransRightHydrogenTwoZPos);
        thirdCalcIteration = calculatePointCharge(finalTransRightHydrogenOneXPos, finalTransRightHydrogenOneYPos, finalTransRightHydrogenOneZPos, finalTransLeftHydrogenTwoXPos, finalTransLeftHydrogenTwoYPos, finalTransLeftHydrogenTwoZPos);
        fourthCalcIterartion = calculatePointCharge(finalTransRightHydrogenOneXPos, finalTransRightHydrogenOneZPos, finalTransRightHydrogenOneYPos, finalTransRightHydrogenTwoXPos, finalTransRightHydrogenTwoYPos, finalTransRightHydrogenTwoZPos);
        fifthCalcIteration = calculatePointCharge(finalTransOxygenOneXPos, finalTransOxygenOneYPos, finalTransOxygenOneZPos, finalTransOxygenTwoXPos, finalTransOxygenTwoYPos, finalTransOxygenTwoZPos);
        sixthCalcIteration = calculatePointCharge(finalTransLeftHydrogenOneXPos, finalTransLeftHydrogenOneYPos, finalTransLeftHydrogenOneZPos, finalTransOxygenOneXPos, finalTransOxygenOneYPos, finalTransOxygenOneZPos);
        seventhCalcIteration = calculatePointCharge(finalTransRightHydrogenOneXPos, finalTransRightHydrogenOneYPos, finalTransRightHydrogenOneZPos, finalTransOxygenTwoXPos, finalTransOxygenTwoYPos, finalTransOxygenTwoZPos);
        eighthCalcIteration = calculatePointCharge(finalTransLeftHydrogenTwoXPos, finalTransLeftHydrogenTwoYPos, finalTransLeftHydrogenTwoZPos, finalTransOxygenOneXPos, finalTransOxygenOneYPos, finalTransOxygenOneZPos);
        ninthCalcIteration = calculatePointCharge(finalTransRightHydrogenOneXPos, finalTransRightHydrogenOneYPos, finalTransRightHydrogenOneZPos, finalTransOxygenOneXPos, finalTransOxygenOneYPos, finalTransOxygenOneZPos);
        totalPointChargeEnergy = firstCalcIteration+secondCalcIteration+thirdCalcIteration+fourthCalcIterartion+fifthCalcIteration+sixthCalcIteration
                +seventhCalcIteration+eighthCalcIteration+ninthCalcIteration;
        
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
    
    public static double calculatePointCharge(double moleculeOneX1, double moleculeOneY1, double moleculeOneZ1, double moleculeTwoX2, double moleculeTwoY2, double moleculeTwoZ2) {
        double distance = Math.sqrt(Math.sqrt(Math.pow(moleculeTwoX2-moleculeOneX1, 2) + Math.pow(moleculeTwoY2-moleculeOneY1, 2) + Math.pow(moleculeTwoZ2-moleculeOneZ1, 2)));
        pointChargeEnergy = Constants.kConstant*Constants.KhydrogenPointCharge*Constants.KoxygenPointCharge/Math.abs(distance);
        return pointChargeEnergy;
    }
    
    public static double caclulateDipoleMoment(double momentOne, double momentTwo, double distance) {
        return 0;
    }
    
    public static void rotationAboutXYZMatrix(double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        double xRotationArrayConstants[][] = {{1,0,0},{0,cosTheta,-sinTheta},{0,sinTheta,cosTheta}}; //TOPtoBOTTOM
        double initialPositionsArraybeforeXRot[][] = {{-24,0,93},{0,0,0},{96,0,0}}; //xyz
        double yRotationArrayConstants[][] = {{cosTheta,0,-sinTheta}, {0,1,0}, {sinTheta,0,cosTheta}}; //TOPtoBOTTOM
        double zRotationArrayConstants[][] = {{cosTheta,sinTheta,0},{-sinTheta,cosTheta,0},{0,0,1}}; //TOPtoBOTTOM
        finalRotatedXArrayOne = new double[3][3];
        finalRotatedYArrayOne = new double[3][3];
        finalRotatedZArrayOne = new double[3][3];

        finalRotatedXArrayTwo = new double[3][3];
        finalRotatedYArrayTwo = new double[3][3];
        finalRotatedZArrayTwo = new double[3][3];
        //finalRotatedXArray = new double [3][3];
        //START X ROTATION MATRIX
        int x = 3;
        System.out.println("XMatrix 1 : ");
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < x; j++) {
                System.out.print(" "+ xRotationArrayConstants[i][j]);
            }
        System.out.println();
        }  
        int y = 3;
        System.out.println("XMatrix 2 : ");
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < y; j++) {
            System.out.print(" "+initialPositionsArraybeforeXRot[i][j]);
        }  
            System.out.println();
        }
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                for(int k = 0; k < y; k++){
                    finalRotatedXArrayOne[i][j] += xRotationArrayConstants[i][k]*initialPositionsArraybeforeXRot[k][j];
                }
            }  
        }
        System.out.println("XMultiply of both matrix : ");
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    System.out.print(" "+finalRotatedXArrayOne[i][j]);
                }  
                System.out.println();
            }
        //END X ROTATION MATRIX
        //START Y ROTATION MATRIX
        System.out.println("YMatrix 1 : ");
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < x; j++) {
                System.out.print(" "+ yRotationArrayConstants[i][j]);
            }
        System.out.println();
        }  
        System.out.println("YMatrix 2 : ");
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < y; j++) {
            System.out.print(" "+finalRotatedXArrayOne[i][j]);
        }  
            System.out.println();
        }
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                for(int k = 0; k < y; k++){
                    finalRotatedYArrayOne[i][j] += yRotationArrayConstants[i][k]*finalRotatedXArrayOne[k][j];
                }
            }  
        }
        System.out.println("YMultiply of both matrix : ");
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < x; j++) {
                    System.out.print(" "+finalRotatedYArrayOne[i][j]);
                }  
                System.out.println();
            }
        //END Y ROTATION MATRIX
        //START Z ROTATION MATRIX
        System.out.println("ZMatrix 1 : ");
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < x; j++) {
                System.out.print(" "+ zRotationArrayConstants[i][j]);
            }
        System.out.println();
        }  
        System.out.println("ZMatrix 2 : ");
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < y; j++) {
            System.out.print(" "+finalRotatedYArrayOne[i][j]);
        }  
            System.out.println();
        }
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                for(int k = 0; k < y; k++){
                    finalRotatedZArrayOne[i][j] += zRotationArrayConstants[i][k]*finalRotatedYArrayOne[k][j];
                }
            }  
        }
        System.out.println("ZMultiply of both matrix : ");
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    System.out.print(" "+finalRotatedZArrayOne[i][j]);
                }  
                System.out.println();
            }
        //END Z ROTATION MATRIX
    }
    
    public static void randomTranslateXYZ() {
      int m, n, c, d;
      System.out.println("Enter the number of rows and columns of matrix");
      m = 3; //column definition
      n = 3; //row definition
 
      double first[][] = new double[m][n];
      finalTranslatedArrayOne = new double[m][n];
      
      System.out.println("Enter the elements of first matrix");
 
      for (  c = 0 ; c < m ; c++ ) {
         for ( d = 0 ; d < n ; d++ ) {
            first[c][d] = randomTranslationConstant;
         }
      }
 
      for ( c = 0 ; c < m ; c++ ) {
         for ( d = 0 ; d < n ; d++ ) {
             finalTranslatedArrayOne[c][d] = first[c][d] + finalRotatedZArrayOne[c][d];  //replace '+' with '-' to subtract matrices
         }
      }
 
      System.out.println("Sum of entered matrices:-");
 
      for ( c = 0 ; c < m ; c++ ) {
         for ( d = 0 ; d < n ; d++ ) {
            System.out.print(finalTranslatedArrayOne[c][d]+"\t");
         }
         System.out.println();
      }
    }
    
     public static void randomThetaGenerator(double rangeStart, double rangeEnd, Random thetaRandom) {
        if(rangeStart > rangeEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)rangeEnd - (long)rangeStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * thetaRandom.nextDouble());
        randomTheta =  (double)(fraction + rangeStart);    
        sopl("Generated : " + randomTheta);
    }
     
     public static void randomTranslationConstantGenerator(double rangeStart, double rangeEnd, Random thetaRandom) {
        if(rangeStart > rangeEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)rangeEnd - (long)rangeStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * thetaRandom.nextDouble());
        randomTranslationConstant =  (double)(fraction + rangeStart);    
        sopl("Generated : " + randomTranslationConstant);
    }
}
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
    
    public static double finalLeftHydrogenOneXPos;
    public static double finalLeftHydrogenOneYPos;
    public static double finalLeftHydrogenOneZPos;
    public static double finalOxygenOneXPos;
    public static double finalOxygenOneYPos;
    public static double finalOxygenOneZPos;
    public static double finalRightHydrogenOneXPos;
    public static double finalRightHydrogenOneYPos;
    public static double finalRighyHydrogenOneZPos;
    
    public static double finalLeftHydrogenTwoXPos;
    public static double finalLeftHydrogenTwoYPos;
    public static double finalLeftHydrogenTwoZPos;
    public static double finalOxygenTwoXPos;
    public static double finalOxygenTwoYPos;
    public static double finalOxygenTwoZPos;
    public static double finalRightHydrogenTwoXPos;
    public static double finalRightHydrogenTwoYPos;
    public static double finalRightHydrogenTwoZPos;
    
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
            
//            finalLeftHydrogenOneXPos = finalRotatedZArrayOne[0][0];
//            finalLeftHydrogenOneYPos = finalRotatedZArrayOne[0][1];
//            finalLeftHydrogenOneZPos = finalRotatedZArrayOne[0][2];
//            finalOxygenOneXPos = finalRotatedZArrayOne[1][0];
//            finalOxygenOneYPos = finalRotatedZArrayOne[1][1];
//            finalOxygenOneZPos = finalRotatedZArrayOne[1][2];
//            finalRightHydrogenOneXPos = finalRotatedZArrayOne[2][0];
//            finalRightHydrogenOneYPos = finalRotatedZArrayOne[2][1];
//            finalRighyHydrogenOneZPos = finalRotatedZArrayOne[2][2];
//            
//            finalLeftHydrogenTwoXPos = finalRotatedZArrayTwo[0][0];
//            finalLeftHydrogenTwoYPos = finalRotatedZArrayTwo[0][1];
//            finalLeftHydrogenTwoZPos = finalRotatedZArrayTwo[0][2];
//            finalOxygenTwoXPos = finalRotatedZArrayTwo[1][0];
//            finalOxygenTwoYPos = finalRotatedZArrayTwo[1][1];
//            finalOxygenTwoZPos = finalRotatedZArrayTwo[1][2];
//            finalRightHydrogenTwoXPos = finalRotatedZArrayTwo[2][0];
//            finalRightHydrogenTwoYPos = finalRotatedZArrayTwo[2][1];
//            finalRightHydrogenTwoZPos = finalRotatedZArrayTwo[2][2];
            
            finalLeftHydrogenOneXPos = finalTranslatedArrayOne[0][0];
            finalLeftHydrogenOneYPos = finalTranslatedArrayOne[0][1];
            finalLeftHydrogenOneZPos = finalTranslatedArrayOne[0][2];
            finalOxygenOneXPos = finalTranslatedArrayOne[1][0];
            finalOxygenOneYPos = finalTranslatedArrayOne[1][1];
            finalOxygenOneZPos = finalTranslatedArrayOne[1][2];
            finalRightHydrogenOneXPos = finalTranslatedArrayOne[2][0];
            finalRightHydrogenOneYPos = finalTranslatedArrayOne[2][1];
            finalRighyHydrogenOneZPos = finalTranslatedArrayOne[2][2];
            
            finalLeftHydrogenTwoXPos = finalTranslatedArrayTwo[0][0];
            finalLeftHydrogenTwoYPos = finalTranslatedArrayTwo[0][1];
            finalLeftHydrogenTwoZPos = finalTranslatedArrayTwo[0][2];
            finalOxygenTwoXPos = finalTranslatedArrayTwo[1][0];
            finalOxygenTwoYPos = finalTranslatedArrayTwo[1][1];
            finalOxygenTwoZPos = finalTranslatedArrayTwo[1][2];
            finalRightHydrogenTwoXPos = finalTranslatedArrayTwo[2][0];
            finalRightHydrogenTwoYPos = finalTranslatedArrayTwo[2][1];
            finalRightHydrogenTwoZPos = finalTranslatedArrayTwo[2][2];
            
            //Translate function goes here...
            
            

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
//                initialLeftHydrogenOneCoord[i] = (double) (Math.randomGenRandomTheta()*4);
//                initialRightHydrogenOneCoord[i] = (double) (Math.randomGenRandomTheta()*4);
//                initialOxygenOneCoord[i] = (double) (Math.randomGenRandomTheta()*4);
//                initialOxygenTwoCoord[i] = (double) (Math.randomGenRandomTheta()*4);
//                initialLeftHydrogenTwoCoord[i] = (double) (Math.randomGenRandomTheta()*4);
//                initialRightHydrogenTwoCoord[i] = (double) (Math.randomGenRandomTheta()*4);
//            }

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
//        System.out.println("This is my calculation: " + calculatePointCharge(initialLeftHydrogenOnex1Pos, initialLeftHydrogenTwox2Pos, initialLeftHydrogenOney1Pos, initialLeftHydrogenTwoy2Pos, initialLeftHydrogenOnez1Pos, initialLeftHydrogenTwoz2Pos, distance));
        System.out.println(readFile("newoutput.txt"));
        
        firstCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        secondCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        thirdCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        fourthCalcIterartion = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        fifthCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        sixthCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        seventhCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        eighthCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
        ninthCalcIteration = calculatePointCharge(initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnex1Pos, initialOxygenTwox2Pos, initialOxygenOnez1Pos, initialOxygenTwoz2Pos, distance);
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
    
    public static double calculatePointCharge(double moleculeOneX1, double moleculeTwoX2, double moleculeOneY1, double moleculeTwoY2, double moleculeOneZ1, double moleculeTwoZ2, double distance) {
        distance = Math.sqrt(Math.sqrt(Math.pow(moleculeTwoX2-moleculeOneX1, 2) + Math.pow(moleculeTwoY2-moleculeOneY1, 2) + Math.pow(moleculeTwoZ2-moleculeOneZ1, 2)));
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
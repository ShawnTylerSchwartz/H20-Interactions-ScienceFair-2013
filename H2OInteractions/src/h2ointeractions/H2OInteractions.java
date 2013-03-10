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
    public static double moleculePositions[];
    public static double leftHydrogenOneCoord[];
    public static double rightHydrogenOneCoord[];
    public static double oxygenOneCoord[];
    public static double oxygenTwoCoord[];
    public static double leftHydrogenTwoCoord[];
    public static double rightHydrogenTwoCoord[];
    public static String arrayContentstoPrint;
    
    //Variables for Calculations
    public static double distance;
    public static double totalDistance;
    public static double singleUsableDistance;
    public static double energy;
    public static double totalEnergy;
        
    //Water Molecule One (1) Positions
    public static double leftHydrogenOnex1;
    public static double leftHydrogenOney1;
    public static double oxygenOnex1;
    public static double oxygenOney1;
    public static double rightHydrogenOnex1;
    public static double rightHydrogenOney1;
    
    //Water Molecule Two (2) Positions
    public static double leftHydrogenTwox2;
    public static double leftHydrogenTwoy2;
    public static double oxygenTwox2;
    public static double oxygenTwoy2;
    public static double rightHydrogenTwox2;
    public static double rightHydrogenTwoy2;
    
    //Constants to (-->) Variables
    public static double hydrogenCharge = Constants.KhydrogenPointCharge;
    public static double oxygenCharge = Constants.KoxygenPointCharge;
    
    public static void main(String[] args) throws IOException {
//        Random moleculeRandom = new Random();
//        int testVar = moleculeRandom.nextInt(50) + 1;
        try {
            // Create file 
            FileWriter fstream = new FileWriter("output.txt");
            BufferedWriter outputWriter = new BufferedWriter(fstream);
            //out.write("Test output");
            //out.write("Test: "+ Arrays.toString(moleculePositions));
            moleculePositions = new double[4];
            leftHydrogenOneCoord = new double[1];
            rightHydrogenOneCoord = new double[1];
            oxygenOneCoord = new double[1];
            oxygenTwoCoord = new double[1];
            leftHydrogenTwoCoord = new double[1];
            rightHydrogenTwoCoord = new double[1];
            
            for(int n = 0; n < 4; n++) {
                moleculePositions[n] = (double) (Math.random()*10);
                arrayContentstoPrint = Arrays.toString(moleculePositions);
            //sopl(Arrays.toString(moleculePositions));             
            }
            
            for(int i = 0; i < 2; i++) {
                leftHydrogenOneCoord[i] = (double) (Math.random()*4);
                rightHydrogenOneCoord[i] = (double) (Math.random()*4);
                oxygenOneCoord[i] = (double) (Math.random()*4);
                oxygenTwoCoord[i] = (double) (Math.random()*4);
                leftHydrogenTwoCoord[i] = (double) (Math.random()*4);
                rightHydrogenTwoCoord[i] = (double) (Math.random()*4);
            }
            
            sopl(arrayContentstoPrint);
            outputWriter.write("\n" + arrayContentstoPrint);
            
            //Close the output inputStream
            outputWriter.close();
        } 
        catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println(readFile("output.txt"));
//        for(int i = 0; i < 2; i++) {
//            x1 = moleculePositions[0];
//            y1 = moleculePositions[1];
//            x2 = moleculePositions[2];
//            y2 = moleculePositions[3];
//        }
//            System.out.println(x1);
//            System.out.println(y1);
//            System.out.println(x2);
//            System.out.println(y2);
//            System.out.println("\nPoint Charge Calculation: " + calculatePointCharge(2));
              leftHydrogenOnex1 = leftHydrogenOneCoord[0];
              leftHydrogenOney1 = leftHydrogenOneCoord[1];
              leftHydrogenTwox2 = leftHydrogenTwoCoord[0];
              leftHydrogenTwoy2 = leftHydrogenTwoCoord[1];
              oxygenOnex1 = oxygenOneCoord[0];
              oxygenOney1 = oxygenOneCoord[1];
              oxygenTwox2 = oxygenTwoCoord[0];
              oxygenTwoy2 = oxygenTwoCoord[1];
              rightHydrogenOnex1 = rightHydrogenOneCoord[0];
              rightHydrogenOney1 = rightHydrogenOneCoord[1];
              rightHydrogenTwox2 = rightHydrogenTwoCoord[0];
              rightHydrogenTwoy2 = rightHydrogenTwoCoord[1];
              
              System.out.println(calculatePointCharge(oxygenOnex1, oxygenTwox2, oxygenOnex1, oxygenTwox2, distance));
//              
//            for(int i = 1; i < 10; i++) {
//                distance = Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
//                energy = ((Constants.kConstant*hydrogenCharge*oxygenCharge)/Math.abs(distance));
//                System.out.println(i+ " Test: "+ energy);
//                
//            }
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
    
    private static void showRandomGeneratedPosition(int aStart, int aEnd, Random aRandom) {
        if(aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);    
        sopl("Generated : " + randomNumber);
    }
  
    private static void sopl(String userInput){
        System.out.println(userInput);
    }
    
    //Point (partial) charge calculation
//    public static double calculatePointCharge(double distance) {
//        for(int i = 1; i < 10; i++) {
//            distance = Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
//            energy = ((Constants.kConstant*hydrogenCharge*oxygenCharge)/Math.abs(distance));
//            System.out.println(energy + i);
//        }
////        energy += totalEnergy;
////        return totalEnergy;
//        return energy;
//    }
    
    public static double calculatePointCharge(double moleculeOneX1, double moleculeTwoX2, double moleculeOneY1, double moleculeTwoY2, double distance) {
        distance = Math.sqrt(Math.sqrt(Math.pow(moleculeTwoX2-moleculeOneX1, 2) + Math.pow(moleculeTwoY2-moleculeOneY1, 2)));
        energy = Constants.kConstant*hydrogenCharge*oxygenCharge/Math.abs(distance);
        System.out.println(energy);
        return energy;
    }
    
//    
//    public static double calculateDistance(double distance) {
//        for(int i = 1; i < 10; i++) {
//            distance = Math.sqrt((x2-x1)+(y2-y1));
//        }
//        distance += totalDistance;
//        return totalDistance;
//    }
    
//    public double calculateColumbForce(double momentOne, double momentTwo) {
//        for(int i = 1; i < 10; i++) {
//            energy = ((Constants.kConstant*momentOne*momentTwo)/totalDistance);
//            energy+=totalEnergy;
//        }
//        return totalEnergy;
//    }
//    
//    public static double calculateDistance(double xPosOne, double xPosTwo, double yPosOne, double yPosTwo, double tester) {
//        totalDistance = Math.sqrt(((yPosTwo-yPosOne)*(yPosTwo-yPosOne))+((xPosTwo-xPosOne)*(xPosTwo-xPosOne)));
//        return tester+=totalDistance;
//    }   
}
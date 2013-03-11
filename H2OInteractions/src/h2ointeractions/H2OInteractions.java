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
//    public static double moleculePositions[];
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
            
        try {
            // Create file 
            FileWriter fstream = new FileWriter("newoutput.txt");
            BufferedWriter outputWriter = new BufferedWriter(fstream);
            //out.write("Test output");
            //out.write("Test: "+ Arrays.toString(moleculePositions));
            //moleculePositions = new double[4];
            leftHydrogenOneCoord = new double[2];
            rightHydrogenOneCoord = new double[2];
            oxygenOneCoord = new double[2];
            oxygenTwoCoord = new double[2];
            leftHydrogenTwoCoord = new double[2];
            rightHydrogenTwoCoord = new double[2];
                 
            for(int i = 0; i < 2; i++) {
                leftHydrogenOneCoord[i] = (double) (Math.random()*4);
                rightHydrogenOneCoord[i] = (double) (Math.random()*4);
                oxygenOneCoord[i] = (double) (Math.random()*4);
                oxygenTwoCoord[i] = (double) (Math.random()*4);
                leftHydrogenTwoCoord[i] = (double) (Math.random()*4);
                rightHydrogenTwoCoord[i] = (double) (Math.random()*4);
            }
            
            outputWriter.write("The randomly generated molecules: \n\n");
            outputWriter.write("leftHydrogenOneCoord: " + Arrays.toString(leftHydrogenOneCoord) + "\n");
            outputWriter.write("rightHydrogenOneCoord: " + Arrays.toString(rightHydrogenOneCoord) + "\n");
            outputWriter.write("oxygenOneCoord: " + Arrays.toString(oxygenOneCoord) + "\n");
            outputWriter.write("oxygenTwoCoord: " + Arrays.toString(oxygenTwoCoord) + "\n");
            outputWriter.write("leftHydrogenTwoCoord: " + Arrays.toString(leftHydrogenTwoCoord) + "\n");
            outputWriter.write("rightHydrogenTwoCoord: " + Arrays.toString(rightHydrogenTwoCoord) + "\n");
            outputWriter.close();
        } 
        catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
        for(int i = 0; i < 2; i++) {
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
        }
        
        System.out.println("leftHydrogenOnex1: " + leftHydrogenOnex1);
        System.out.println("leftHydrogenOney1: " + leftHydrogenOney1);
        System.out.println("leftHydrogenTwox2: " + leftHydrogenTwox2);
        System.out.println("leftHydrogenTwoy2: " + leftHydrogenTwoy2);
        System.out.println("oxygenOnex1: " + oxygenOnex1);
        System.out.println("oxygenOney1: " + oxygenOney1);
        System.out.println("oxygenTwox2: " + oxygenTwox2);
        System.out.println("oxygenTwoy2: " + oxygenTwoy2);
        System.out.println("rightHydrogenOnex1: " + rightHydrogenOnex1);
        System.out.println("rightHydrogenOney1: " + rightHydrogenOney1);
        System.out.println("rightHydrogenTwox2: " + rightHydrogenTwox2);
        System.out.println("rightHydrogenTwoy2: " + rightHydrogenTwoy2);
        System.out.println("distance: " + distance);
        System.out.println("This is my calculation: " + calculatePointCharge(leftHydrogenOnex1, leftHydrogenTwox2, leftHydrogenOney1, leftHydrogenTwoy2, distance));
        System.out.println(readFile("newoutput.txt"));

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
    
    public static double calculatePointCharge(double moleculeOneX1, double moleculeTwoX2, double moleculeOneY1, double moleculeTwoY2, double distance) {
        //        distance = Math.sqrt(Math.sqrt(Math.pow(moleculeTwoX2-moleculeOneX1, 2) + Math.pow(moleculeTwoY2-moleculeOneY1, 2)));
//        energy = Constants.kConstant*hydrogenCharge*oxygenCharge/Math.abs(distance);
//        System.out.println(energy);
        energy = moleculeOneX1+moleculeOneY1+moleculeTwoX2+moleculeTwoY2;
        return energy;
    }  
}
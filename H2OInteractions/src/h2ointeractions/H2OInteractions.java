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
 * @assistants Wun Chiou & Daniel Perahya
 * @fileName "H2OInteractions.java" (Main Class)
 * @version 1.0 - 2013 LA County Science Fair Edition
 */
//NOTE: Starting with 2 molecules first.
public class H2OInteractions {

    public static double xPos;
    public static double yPos;
    public static double xBoundary;
    public static double yBoundary;
    public static double totalDistance;
    public static double energy;
    public static double totalEnergy;
    public static String arrayContentstoPrint;
    public static double xOneArray;
    public static double xTwoArray;
    public static double yOneArray;
    public static double yTwoArray;
    public static double moleculePositions[];
    public static double x1;
    public static double y1;
    public static double x2;
    public static double y2;
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
            for (int n = 0; n < 4; n++) {
//                for (int i = 0; i < 2; i++) {
                    moleculePositions[n] = (double) (Math.random()*10);
//                }
            arrayContentstoPrint = Arrays.toString(moleculePositions);
            
            //sopl(Arrays.toString(moleculePositions)); 
            
            
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
        
        for (int i = 0; i < 2; i++) {
            x1 = moleculePositions[0];
            y1 = moleculePositions[1];
            x2 = moleculePositions[2];
            y2 = moleculePositions[3];
        }
            System.out.println(x1);
            System.out.println(y1);
            System.out.println(x2);
            System.out.println(y2);
            System.out.println("\nPoint Charge Calculation: " + calculatePointCharge(1));
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
    
    //Point charge calculation
    public static double calculatePointCharge(double distance) {
        
        for(int i = 1; i < 10; i++) {
            energy = ((Constants.kConstant*hydrogenCharge*oxygenCharge)/distance);
        }
        return energy;
    }
    
    public static double calculateDistance() {
        totalDistance = Math.sqrt((x2-x1)+(y2-y1));
        return totalDistance;
    }
    
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
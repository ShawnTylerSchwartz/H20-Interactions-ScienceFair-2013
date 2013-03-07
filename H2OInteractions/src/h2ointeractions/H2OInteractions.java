package h2ointeractions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

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
    public static double distance;
    public static double energy;
    public static double totalEnergy;
    
    
    public static void main(String[] args) throws IOException {
//        int[] array;
//        array = new int[2];
//        array[0] = 2;
//        array[1] = 3;
        try {
            // Create file 
            FileWriter fstream = new FileWriter("output.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("Test output");
            //out.write("Test: "+ Arrays.toString(array));
            //Close the output stream
            out.close();
        }
        catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println(readFile("output.txt"));
    }
         private static String readFile(String path) throws IOException {
            FileInputStream stream = new FileInputStream(new File(path));
            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
            }
            finally {
                stream.close();
            }
  }
}
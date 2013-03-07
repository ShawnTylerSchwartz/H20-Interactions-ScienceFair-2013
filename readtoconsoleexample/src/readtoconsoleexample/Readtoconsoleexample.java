/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readtoconsoleexample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Shawn
 */
public class Readtoconsoleexample {

    /**
     * @param args the command line arguments
     */
     

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
       try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "2RainfallDataLanc.txt"));
            String line = null;
            ArrayList<String[]> rows = new ArrayList<String[]>();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split("/t");
                rows.add(row);
                
            }
            System.out.println(rows.toString());
       }
       catch (IOException e) {
       }     
    }
    
}
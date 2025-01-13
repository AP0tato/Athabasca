package com.athabasca;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper 
{
    public static List<String> readFile(String file) throws IOException
    {
        List<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String s;
        while( (s = br.readLine())!=null )
            list.add(s);

        br.close();
        return list;
    }

    public static void writeLine(String file, String line, String header) throws IOException
    {
        List<String> list = readFile(file);
        list.add(line);
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        String s = header+"\n";
        for(String i : list)
            s += i+"\n";
        bw.write(s);
        
        bw.close();
    }
}

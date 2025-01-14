package com.athabasca;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String s;
            while( (s = br.readLine())!=null )
                list.add(s);
        }

        return list;
    }

    public static void writeLine(String file, String line, String header) throws IOException
    {
        File f = new File(file);
        boolean fileExists = f.exists()&&!f.isDirectory();
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
        {
            if(!fileExists)
            {
                bw.write(header);
                bw.newLine();
            }
            bw.write(line);
            bw.newLine();
        }
    }
}

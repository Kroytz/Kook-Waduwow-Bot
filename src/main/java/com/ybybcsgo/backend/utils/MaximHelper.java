package com.ybybcsgo.backend.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class MaximHelper {

    public static void ConvertFullChatRecordToMaxim()
    {
        String path = "./chat.txt";
        String outputPath = "./maximOut.txt";

        // Initialize file to write
        File outFile = new File(outputPath);
        PrintWriter outWriter = null;
        try {
            outWriter = new PrintWriter(outFile, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        if (outWriter != null)
        {

        }

    }

    // DEPRECATED: Seems high memory usage causing cloud machine crash
//    public static void ConvertFullChatRecordToMaximAllLine()
//    {
//        String path = "./chat.txt";
//        String outputPath = "./maximOut.txt";
//
//        // Initialize file to write
//        File outFile = new File(outputPath);
//        PrintWriter outWriter = null;
//        try {
//            outWriter = new PrintWriter(outFile, "UTF-8");
//        } catch (FileNotFoundException | UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (outWriter != null)
//        {
//            try
//            {
//                // Read file
//                List<String> chatRecordList = Files.readAllLines(new File(path).toPath());
//                boolean fetching = false;
//                for (String line : chatRecordList) {
//
//                    if (line.equals("[图片]") || line.equals("[表情]"))
//                        continue;
//
//                    if (line.isEmpty()) {
//
//                        // Stop fetching when meet space
//                        if (fetching) {
//                            fetching = false;
//
//                            // end record
//                            outWriter.println("");
//                        }
//
//                        continue;
//                    }
//
//                    if (fetching) {
//                        // use print to connect
//                        outWriter.print(line);
//                    }
//                    else {
//                        // Found sb Wadu's record, start fetch whole record
//                        if (line.contains("(2070239158)")) {
//                            fetching = true;
//                        }
//                    }
//
//                }
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }

}

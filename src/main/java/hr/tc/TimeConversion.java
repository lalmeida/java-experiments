package hr.tc;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    class Result {


        public static String timeConversion(String s) {
            SimpleDateFormat apPmTimeFormat = new SimpleDateFormat("hh:mm:ssa");
            SimpleDateFormat militaryTimeFormat = new SimpleDateFormat("HH:mm:ss");
            Date time;
            try {
                time = apPmTimeFormat.parse(s);
            } catch (ParseException e) {
                throw new RuntimeException("Input not valid!");
            }
            return militaryTimeFormat.format(time);
        }

    }

    class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String s = bufferedReader.readLine();

            String result = Result.timeConversion(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }


package chap03;

import utils.TextIO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class TextIOPlus extends TextIO {
    public TextIOPlus() {

    }

    /**
     * TODO: code lại phần này để lấy text từ URL
     * URL là: https://hanustartup.org/pr2/ch3/Hello.j
     * @param urlString
     * @return
     */
    public static String readTextFromURL(String urlString) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlString);
            Scanner s = new Scanner(url.openStream());
            while (s.hasNextLine()) {
                sb.append(s.nextLine());
                sb.append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

//    private static String readText() {
//        StringBuffer fileBuf = new StringBuffer();
//        char NL = true;
//
//        while (!TextIO.eof()) {
//            fileBuf.append(TextIO.getln()).append('\n');
//        }
//
//        return fileBuf.length() > 0 ? fileBuf.toString() : null;
//    }

    public static String readTextFileContent(Class cls, String fileName) {
        URL fileURL = cls.getResource(fileName);
        if (fileURL == null) {
            return null;
        } else {
            String filePath = fileURL.getPath();
            StringBuilder sb = new StringBuilder();
            try {
                Scanner sc = new Scanner(new File(filePath));
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine());
                    sb.append(System.lineSeparator());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }

//    public static String readTextFileContent(String filePath) {
//        TextIO.readFile(filePath);
//        StringBuffer fileBuf = new StringBuffer();
//        char NL = true;
//
//        while (!TextIO.eof()) {
//            fileBuf.append(TextIO.getln()).append('\n');
//        }
//
//        return fileBuf.length() > 0 ? fileBuf.toString() : null;
//    }

    public static String getString(String prompt) {
        prompt = prompt.trim();
        if (!prompt.endsWith(":")) {
            System.out.println(prompt + ":");
        } else {
            System.out.println(prompt);
        }
        return getlnString();
    }
}

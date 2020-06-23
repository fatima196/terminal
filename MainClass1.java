/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;
import static terminal.Terminal.currentFile;
import static terminal.Terminal.defaultDirectory;

/**
 *
 * @author lenovo
 */
public class MainClass1 {
     //public static File defaultDirectory , currentFile ;
    
        public static void main(String[] args) throws FileNotFoundException {
                currentFile=new File("D:\\");//current dir
                defaultDirectory =new File(System.getProperty("user.dir")) ;
            
            File file;
            Parser p = new Parser();

             while (true){
              Scanner in = new Scanner(System.in);
              String command = in.nextLine(); 
   	String [] words = command.split(Pattern.quote("|"));
        Vector<String> parts=new Vector<>();
        for(int y=0;y<words.length;y++){
            words[y]=words[y].trim();
            if(words[y].length() > 1){
                parts.add(words[y]);
            }
        }
            /* for (String str:words){
              //   str=str.trim();
                 parts.add(str);
             }*/
              
        /*for (int o=0;o<parts.size()-1;o++){
              System.out.println("---"+parts.get(o));
        }*/
              
        for(int i=0;i<parts.size();i++){
            String sub_command=parts.get(i);
            boolean x;
            x = p.parse(sub_command);
            if (x == true && p.getArguments().length == 1 && p.getCmd().equals("cd")) {
               Terminal t = new Terminal();
               String arg = p.getArguments()[0];
               if (arg.equals("..")) {
                    t.cd();
                    //        break;
                }
                else {
                    file = new File(arg);
                    if (file.isDirectory()) {
                       t.cd(arg);
                    }
                    else{
                       t.cd(arg);
                    }
                }
            }else if (x == true && p.getCmd().equals("cp")) {
                Terminal t = new Terminal();
                t.cp(p.getArguments()[0], p.getArguments()[1]);
            } else if (x == true && p.getCmd().equals("mv")) {
                Terminal t = new Terminal();
                t.mv(p.getArguments()[0], p.getArguments()[1]);
            } else if (x == true && p.getCmd().equals("rm")) {
                Terminal t = new Terminal();
                t.rm(p.getArguments()[0]);
            } else if (x == true && p.getCmd().equals("mkdir")) {
                Terminal t = new Terminal();
                t.mkdir(p.getArguments()[0]);
            } else if (x == true && p.getCmd().equals("rmdir")) {
                Terminal t = new Terminal();
                t.rmdir(p.getArguments()[0]);
            } else if (x == true && p.getCmd().equals("pwd")) {
                Terminal t = new Terminal();
                if (p.getArguments().length == 2) {
                    try {
                        boolean flag = false;
                        if (p.getArguments()[0].equals(">>")) {
                            flag = true;
                        }
                        PrintWriter out = new PrintWriter(new FileOutputStream(new File(p.getArguments()[1]), flag));
                        out.println(t.pwd());
                        out.close();
                        System.out.println("Done");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                } else {
                    System.out.println(t.pwd());
                }
            } else if (x == true && p.getCmd().equals("clear")) {
                Terminal t = new Terminal();
                t.clear();
            } else if ((x == true && p.getCmd().equals("ls"))) {
                Terminal t = new Terminal();
                if (p.getArguments().length == 2) {
                    boolean flag = false;
                    if (p.getArguments()[0].equals(">>")) {
                        flag = true;
                    }else if (p.getArguments()[0].equals(">")){
                        flag = false;
                    }
                    //  t.ls(p.getArguments()[1], flag);
                    t.ls(p.getArguments()[1],flag);
                    System.out.println("Done");
                } else {
                    t.ls();
                }

            } else if (x == true && p.getCmd().equals("help")) {
                Terminal t = new Terminal();
                    t.help();
            }
            else if(x == true && p.getCmd().equals("date")){
                Terminal t=new Terminal();
                t.date();

            }
            else if(x==true && p.getCmd().equals("more")){
            Terminal t =new Terminal ();
            t.more(p.getArguments()[0]);
        }
        else if(x==true &&p.getCmd().equals("cat")){
            Terminal t=new Terminal();
            if(p.getArguments().length == 1){
                t.cat(p.getArguments()[0]);
            }
              //  boolean flag = false;
                   // if (p.getArguments()[2].equals(">>")) flag = true;
                   // t.cat(parts.get(1), parts.get(2), parts.get(4), flag);
                    //System.out.println("Done");
           // }
            else{
                t.cat(p.getArguments());
            }  
        }else if(x == true && p.getCmd().equals("args")){
                Terminal t=new Terminal();
                t.args(p.getArguments()[0]);
            }

            // p.parse(command);
            // TODO code application logic here
        }
    }
}}


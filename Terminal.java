
package terminal;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Terminal {

    /**
     * @param args the command line arguments
     */
    public static File defaultDirectory , currentFile ;//working directory ;
    
    //without parametar
    public void cd() {
        String str=pwd();
        str=defaultDirectory.getPath();
        currentFile=new File (str);
        //System.out.println("currentFile.getPath()=="+currentFile.getPath());
    }
    //with parametar
    public void cd(String path) {
        File newDir = new File(path);
        String str=pwd();
        str =newDir.getPath();
        currentFile=new File(str);
        //System.out.println("currentFile.getPath()=="+currentFile.getPath());
    }
    
    //without parameter
    public void ls() {
        //list all fils on current file 
        //display on console screen
        String arr[] = currentFile.list();
        for (String str : arr)
            System.out.println(str);
    }

    //with parameter 
    public void ls(String filePath,boolean append ) {
        String arr[] = currentFile.list();
        try {
            //create new file and/or oppen it in mood append 
            //write on this file the list of all files on current file 
            
            File file=new File(filePath);
            if (!filePath.contains(":")){
              file=new File(currentFile.getPath()+ File.separator + filePath);
             // file.getAbsolutePath();
            }
                //file.getAbsolutePath();
            if (append==false){
            PrintWriter out ;
            out = new PrintWriter(new FileOutputStream(file),append);
            for (String str : arr)
                out.println(str);
            out.close();
            }
            
            else if (append==true){
                FileWriter fileWriter = new FileWriter(file, append); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (String str : arr)
                printWriter.println(str);  //New line
                printWriter.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    
    //clear with print 20 empty lines 
    public void clear (){
            for (int i=0;i<20;i++){
                System.out.println();
            }
    }
    
    
    
    
    /*cp(String sourcePath, String destinationPath)--> copy the contents of 1st file to the 2nd file.
    If the 2nd file doesn’t exist,
    then first it creates one and content is copied to it.
    But if it existed then it is simply overwritten without any warning. */
    
     public void cp(String srcPath, String destPath) {
        try {
            File source = new File(srcPath);
            File destination = new File(destPath);
            if ((!srcPath.contains(":")) && (!destPath.contains(":"))){
              source=new File(currentFile.getPath()+ File.separator + srcPath);
              destination=new File(currentFile.getPath()+ File.separator + destPath);
            }
            if (source.isFile()){//check if source dosn't a directory.
                            //srcPath       //destPath
                
                Files.copy(source.toPath(),destination.toPath());
                
                
            }//File.Copy(String, String) method overload to copy text (.txt) files.
                                                                  //this overload does not allow overwriting.
            else {
                System.out.println(source.toPath());
                System.out.println(" is a directory and cp takes files as arguments not directories");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }
     
     public void mv(String srcPath, String destPath) {
        try {
            File source = new File(srcPath);
            File destination = new File(destPath);
            if ((!srcPath.contains(":")) && (!destPath.contains(":"))){
              source=new File(currentFile.getPath()+ File.separator + srcPath);
              destination=new File(currentFile.getPath()+ File.separator + destPath);
            }
            Files.move(source.toPath(), destination.toPath());//.move();-->copies the file
                                                              //to destination and then deletes
                                                              //the original copy from the source.
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
     
     
      public void rm(String filePath) {
        File file = new File(filePath);
        if (!filePath.contains(":")) {
              file=new File(currentFile.getPath()+ File.separator + filePath);
            }
        if (file.isDirectory()) {
            //System.out.println("dir.");
            String arr[] = file.list();
            for (String str : arr) {
                System.out.println(str);
                File tempFile = new File(filePath +'\\'+ str);
                if (tempFile.isFile())
                    //System.out.println("file");
                    tempFile.delete();
            }
        }
            file.delete();
    }
      
      public void rmdir(String dirPath){
          File file=new File(dirPath);
          if (!dirPath.contains(":")) {
              file=new File(currentFile.getPath()+ File.separator + dirPath);
            }
          if (file.isDirectory())
              file.delete();
      }
      
     /* public void rmDir(String dirPath){
          File file =new File (dirPath);
          if (file.isDirectory())
              file.delete();
      }*/
      
      public void cat(String filePath) {
        try {
            File file = new File(filePath);
            if (!filePath.contains(":")) {
              file=new File(currentFile.getPath()+ File.separator + filePath);
            }
            Scanner in = new Scanner(file);
            while (in.hasNextLine()){
                System.out.println(in.nextLine());
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
      
         public void cat(String arr[]) {
            for(String str:arr){
                //System.out.println(str);
                //System.out.println(arr);
                try {
                File file = new File(str);
                if (!str.contains(":")) {
                    file=new File(currentFile.getPath()+ File.separator + str);
                }
                Scanner in = new Scanner(file);
                while (in.hasNextLine())
                    System.out.println(in.nextLine());
                //cat C:\Users\lenovo\Documents\\t1.txt C:\Users\lenovo\Documents\\t1.txt C:\Users\lenovo\Documents\\t1.txtSystem.out.println("------------------");
                in.close();
                }catch (Exception e) {
                System.out.println(e.getMessage());
                }
        }
    }
      
      
      public boolean mkdir(String dirPath){
          File file = new File (dirPath);
          if (!dirPath.contains(":")) {
              file=new File(currentFile.getPath()+ File.separator + dirPath);
            }
          return file.mkdir();//bulit in function in pakcage File 
      }
      
      
      public String pwd(){
          return currentFile.getPath();
      }
      
      
      public void date(){
          Date date=new Date();
          System.out.println(date);
      }
      
      
        
        public void more (String filePath) throws FileNotFoundException{
            File file =new File (filePath);
            if (!filePath.contains(":")) {
                file=new File(currentFile.getPath()+ File.separator + filePath);
            }
            Scanner in =new Scanner (file);
            Scanner rep=new Scanner(System.in);
            
            int count =0;
            while (in.hasNextLine()){
                System.out.println(in.nextLine());
                count++;
                if (count==10){
                    System.out.println("you need more?" +'\n'+ "  >>if yes enter yes " +'\n'+ "  >>if No enter no ");
                    String reply=rep.nextLine();
                    if (reply.equals("yes")){
                        count=0;
                    }
                    else 
                        return ;
                }
            }
            in.close(); //rep.close();
        }
      
      
      public void help(){
        System.out.println("cd directoryPath--> moving to another directory.");
        System.out.println("ls--> List / print all files and directories in current path.");
        System.out.println("rm filePath or directoryPath --> removing file or removing files from a directory");
        System.out.println("rmdir directoryPath --> delete an empty directory.");
        System.out.println("mkdir directoryPath --> creating new directory.");
        System.out.println("pwd --> print working directory.");
        System.out.println("cat FilePath --> Printing the content of a text file.");
        System.out.println("cat FilePath1 FilePath2 to FilePath3 --> Printing the content of file1 and file2 in file3.");
        System.out.println("more FilePath --> Printing the content of a text file in a scrollable manner.");
        System.out.println("cp SourcePath DestinationPath --> Copying the content from SourceFile to DestinationFile.");
        System.out.println("mv SourcePath DestinationPath --> Moving the content from SourceFile to DestinationFile.");
        System.out.println("clear --> Clearing console content.");
        System.out.println("args commandName --> List all command arguments.");
        System.out.println("date --> Current date/time.");
        System.out.println("exit --> Stop all.");
    }
      
       public void args(String command) {
        if (command.equals("cd")) {
            System.out.println("No parameters or .. --> change current directory to the default directory");
            System.out.println("Number of args is 1 --> New directoryPath to change to");
        }
        else if (command.equals("ls")){
            System.out.println("No parameters");
            System.out.println("Number of args is 1 --> new file to list in ");
        }else if (command.equals("rm"))
            System.out.println("Number of args is 1 --> FilePath to be removed or directoryPath to remove files there");
        else if (command.equals("rmdir"))
            System.out.println("Number of args is 1 --> Path of empty directory to be removed");
        else if (command.equals("mkdir"))
            System.out.println("Number of args is 1 --> DirectoryPath to be created");
        else if (command.equals("pwd")){
            System.out.println("No parameters");
            System.out.println("Number of args is 1 --> FilePath to save cuurentdir in it");}
        else if (command.equals("more"))
            System.out.println("Number of args is 1 : FilePath to be displayed");
        else if (command.equals("cat")) {
            System.out.println("There are 3 options :");
            System.out.println("Number of args is 1 : FilePath to be displayed");
            System.out.println("Number of args is 2 : FilePath1 and FilePath2 to be displayed");
            System.out.println("Number of args is 4 : FilePath1 FilePath2 > FilePath3");
        } else if (command.equals("cp"))
            System.out.println("Number of args is 2 : SourcePath DestinationPath");
        else if (command.equals("mv"))
            System.out.println("Number of args is 2 : SourcePath DestinationPath");
        else if (command.equals("date"))
            System.out.println("No parameters");
        else System.out.println("Error");

    }
      
      
      
    
      
      
      
      
      
     
    
     
     
    
    
    
    
    
/*
public void cp(String sourcePath, String destinationPath );**
public void mv(String sourcePath, String destinationPath);**
public void rm(String sourcePath);
public void pwd();
public void cat(String[] paths);
*/

    

    
}

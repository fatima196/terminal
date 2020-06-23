
package terminal;

import java.util.Vector;


public class Parser {
    String[] args; // Will be filled by arguments extracted by parse method  
    String  cmd; // Will be filled by the command extracted by parse method 
    public boolean parse(String input){
        String [] words= input.split(" ");
                   //System.out.println(words[0]);

        Vector<String> parts=new Vector<>();
        for(int i=0;i<words.length;i++){
            words[i]=words[i].trim();
            if(words[i].length() > 0){
                parts.add(words[i]);
            }
        }
        if(parts.size()==0){
            return false;}
        if(parts.get(0).equals("cd")){
             cmd="cd";
          // System.out.println("KKK");
            if(parts.size()>2){
                System.out.println("Too many parameters, write help for details");
                return false;
            }
         //   System.out.print("HHHHkHGGG");
            else if(parts.size()==2){
           args=new String [1];
           args[0]=parts.get(1);
           return true;
            }
            else{return false;}
        }
        else if(parts.get(0).equals("cp")){
             cmd="cp";
            if(parts.size()!=3){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
           
            args=new String[2];
            args[0]=parts.get(1);
            args[1]=parts.get(2);
            return true;
        }
        else if(parts.get(0).equals("mv")){
            cmd="mv";
            if(parts.size()!=3){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            
            args=new String[2];
            args[0]=parts.get(1);
            args[1]=parts.get(2);
            return true;
        }
        else if(parts.get(0).equals("rm")){
             cmd="rm";
            if(parts.size()!=2){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            args=new String[1];
            args[0]=parts.get(1);
            return true;
        }
        else if(parts.get(0).equals("mkdir")){
             cmd="mkdir";
            if(parts.size()!=2){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
           
            args=new String[1];
            args[0]=parts.get(1);
            return true;
        }
        else if(parts.get(0).equals("rmdir")){
            cmd="rmdir";
            if(parts.size()!=2){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            
            args=new String[1];
            args[0]=parts.get(1);
            return true;
        }
        else if(parts.get(0).equals("pwd")){
             cmd="pwd";
            if(parts.size()==3 &&(parts.get(1).equals(">")||parts.get(1).equals(">>"))){
               
                args=new String[2];
                args[0]=parts.get(1); 
                args[1]=parts.get(2);
                return true;
            }
            else if(parts.size()!=1){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            else{
            
                args=new String[0];
                return true;
            }
        }
        else if(parts.get(0).equals("clear")){
               cmd="clear";
            if(parts.size()!=1){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
         
            args=new String[0];
            return true;
        }
        else if(parts.get(0).equals("ls")){
             cmd="ls";
            if(parts.size()==3 &&(parts.get(1).equals(">")||parts.get(1).equals(">>"))){
               
                args=new String[2];
                args[0]=parts.get(1); 
                args[1]=parts.get(2);
                return true;
            }
            else if(parts.size()!=1){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            else{
               
                args=new String[0];
                return true;
            }
        }
        else if(parts.get(0).equals("help")){
              cmd="help";
         
            if(parts.size()!=1){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            else{
                args=new String[0];
                return true;
            }
        }
        else if(parts.get(0).equals("date")){
            cmd="date";
            
            if(parts.size()!=1){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            else{
                args=new String[0];
                return true;
            }
        }
        else if(parts.get(0).equals("args")){
            if(parts.size()!=2){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            else{
                cmd="args";
                args=new String[1];
                args[0]=parts.get(1);
                return true;
            }
        }
        else if(parts.get(0).equals("more")){
            if(parts.size()!=2){
                System.out.println("Invalid parameters, write help for details");
                return false;
            }
            cmd="more";
            args=new String[1];
            args[0]=parts.get(1);
            return true;
        }
        else if(parts.get(0).equals("cat")){
            cmd="cat";
            if(parts.size()<2){
                args =new String [1];
                args[0] = parts.get(1);
                //System.out.println("Invalid parameters, write help for details");
                //return false;
            }
            else{
                args =new String [parts.size()-1];
                for(int i=1;i<parts.size();i++){
                    args[i-1]=parts.get(i);
                    //System.out.println("args-->"+args[i-1]);
                    
                }
                return true;
            }
        }
        else if(parts.get(0).equals("exit")){
            System.exit(0);
        }
        else{
            System.out.println("Invalid command");
        }
        
        return true;
        
    } 
    public String getCmd(){
        return cmd;
    }
    
    public String[] getArguments(){
        return args;
}
}

import java.util.*;

class EvenOdd implements Runnable{
    boolean odd = true;
    boolean even = true;
    static int n;
    static int counter = 1;
    synchronized void printEven() {
        try{
            while(odd == false){
            wait();
        }
        }catch(InterruptedException e){

        }
        
        System.out.println(Thread.currentThread().getName() + " " + counter);
        counter++;
        even = true;
        notify();
    }
    synchronized void printOdd(){
        try{
            while(even == false){
            wait();
        }
        }catch(InterruptedException e){
            
        }
        System.out.println(Thread.currentThread().getName() + " " + counter);
        counter++;  
        odd = true;
        notify();
    }

    public void run(){
        while(counter<=n){
            synchronized(this){
                if(Thread.currentThread().getName().equals("Odd")){
                    printOdd();
                }if(Thread.currentThread().getName().equals("Even")){
                    printEven();
                }
            }
        }
    }
    public static void main(String []args){
        EvenOdd e = new EvenOdd();
        n = 10;
        Thread t1 = new Thread(e);
        Thread t2 = new Thread(e);
        t1.start();
        t2.start();
    }
}
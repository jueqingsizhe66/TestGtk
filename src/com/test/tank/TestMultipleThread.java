/**
 * 解释：
 */
package com.test.tank;

/**
 * @author    叶昭良
 * @time      2015年3月18日下午11:24:26
 * @version   com.test.tankTestMultipleThread V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */


class SynStack 
{
    // the maximum producer.
    private char[] data = new char[6];
    private int cnt  = 0; //  ge shu

    public synchronized void push(char ch)
    {
        //if(cnt == ss.data.length)
        if(cnt == data.length)  // in the same class
        {
            try{
                this.wait();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            // pause;
        }
        this.notify();
 
        data[cnt] =ch ;
        ++cnt;
        System.out.printf("It is outputing %d product,it is %c\n",cnt,ch);
    }
    public synchronized char pop()
    {
        if(cnt == 0)
        {
            try{
                this.wait();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            // pause;
            //  if full,then execute the consumer,
            //  so finally two thread have been stoped in ,,,,

        }
        if(cnt == 3)
        {
        	this.notify();
        }        
        
        --cnt;
        char ch = data[cnt];
        System.out.printf("It is eating %d product,it is %c\n",cnt,ch);
       // System.out.println("2222/n");
        return data[cnt];
    }

}

// producer
class Producer implements Runnable
{
    private SynStack ss = null;
    public Producer(SynStack ss)
    {
       
        this.ss =ss;
    }
    public void run()
    {
       /*
        try{
            Thread.sleep(2000); //sleep have throws ,so must try  catch
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        */
	    char ch;
	    for(int i = 0;i < 20; i++) 
	    {
	        ch = (char)('a'+i);
	        ss.push(ch);
	    }
    }
}
// consumer
class Consumer implements Runnable
{
    private SynStack ss = null;
    public Consumer(SynStack ss)
    {
        this.ss =ss;
    }
    public void run()
    {
        for(int i = 0 ; i<20; i++)
        {
            System.out.printf("%c\n",ss.pop());
        }
    }
}
public class TestMultipleThread
{
    public static void main(String[] args)
    {
       SynStack ss = new SynStack(); // ss is an array
       // the same array  ss!
       Producer p = new Producer(ss); // Producer define a thread.
       Consumer c = new Consumer(ss);

       Thread t1 = new Thread(p);
       t1.start();

       Thread t2 = new Thread(c);
       t2.start();

    }

}

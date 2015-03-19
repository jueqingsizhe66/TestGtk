/**
 * 解释：
 */
package TestNetwork;
import java.awt.*;
import java.awt.event.*;
/**
 * @author    叶昭良
 * @time      2015年3月19日下午2:04:15
 * @version   TestNetworkTestTextField V1.0
 * 功能： 
                步骤：
 * 注意：
 * 掌握：
                思考：
 * 回顾：
 */
public class TestTextField
{

	/**
	 * @param args 
	 * 原因：
	 * 解决：
	 * 功能：
	 *       思考：        
	 *       步骤：
	 */
    public  static TextField tf1,tf2,tf3;
    //private  static TextField tf1,tf2,tf3;   //private会出现问题
                           //monitor类出现未定义
    public TestTextField()	
    {
    	Frame f = new Frame();
        tf1 = new TextField(10);
        tf2 = new TextField(10);
        tf3 = new TextField(10);
        Label Lb = new Label("+");
        Button bt = new Button("=");
        f.setLayout(new FlowLayout());

        f.add(tf1);
        f.add(Lb);
        f.add(tf2);
        f.add(bt);
        f.add(tf3);

        //通过一个类  来封闭事件处理
        MyMonitor mm = new MyMonitor();
        bt.addActionListener(mm);
/*        bt.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
			    int num1 =  Integer.parseInt(TestTextField.tf1.getText());
		        int num2 =  Integer.parseInt(TestTextField.tf2.getText());
		        int num3 =  num1+num2;
		        TestTextField.tf3.setText(num3+"");
			}
		});*/
        f.addWindowListener(new WindowAdapter()
		{
        	@Override
        	public void windowClosing(WindowEvent e)
        	{
        		System.exit(0);
        	}
		});
        f.pack();
        f.setVisible(true);
    }
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		TestTextField ttf = new TestTextField();
	}

}

class MyMonitor extends WindowAdapter implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        int num1 =  Integer.parseInt(TestTextField.tf1.getText());
        int num2 =  Integer.parseInt(TestTextField.tf2.getText());
        int num3 =  num1+num2;
        TestTextField.tf3.setText(num3+"");
    }
    
   // @Override
    public void WindowClosing(WindowEvent e)
    {
        System.exit(-1);
    }
}

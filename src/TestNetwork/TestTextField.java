/**
 * ���ͣ�
 */
package TestNetwork;
import java.awt.*;
import java.awt.event.*;
/**
 * @author    Ҷ����
 * @time      2015��3��19������2:04:15
 * @version   TestNetworkTestTextField V1.0
 * ���ܣ� 
                ���裺
 * ע�⣺
 * ���գ�
                ˼����
 * �عˣ�
 */
public class TestTextField
{

	/**
	 * @param args 
	 * ԭ��
	 * �����
	 * ���ܣ�
	 *       ˼����        
	 *       ���裺
	 */
    public  static TextField tf1,tf2,tf3;
    //private  static TextField tf1,tf2,tf3;   //private���������
                           //monitor�����δ����
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

        //ͨ��һ����  ������¼�����
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
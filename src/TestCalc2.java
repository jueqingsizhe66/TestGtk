
/**
 * @author Ҷ����
 * @version : ������v 2.0  �滻��entry ������SpinBox��ComboBox
 *
 */
import javax.swing.JOptionPane;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import java.math.*;
import java.lang.Math;
public class TestCalc2
{

	/**
	 * @param args
	 */
	static int window;

	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		//��ʼ��
		GTK.gtk_init();
		//��������  ���ó�static int window����
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//���ô�������
		GTK.gtk_window_set_title(window, "������v2.0");
		//���Ӵ���
		GTK.gtk_widget_show(window);
		
		//�رնԻ���
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
		}, null);
		
		//���񲼾�
	    int houseGrid = GTK.gtk_grid_new();
	    //���������ؼ�
	   
	    Calc(window,houseGrid,0);
	    
	    //���뵽windows����
		GTK.gtk_container_add(window, houseGrid);
		//����ѭ��
		GTK.gtk_main();
		
	}
	/**
	 * 
	 * @param window     ���ڵı�ʶ
 	 * @param houseGrid  ���ⷿ��ı�ʶ
	 * @param start      �������ⷿ�����ʼ����
	 */
	public static void Calc(int window,int houseGrid,int start)
	{
		final int sbOne = GTK.gtk_spin_button_new_with_range(-32768, 32767, 1);
		final int sbAnother = GTK.gtk_spin_button_new_with_range(-32768, 32767, 1);
		GTK.gtk_spin_button_set_value(sbOne, 12.0);
		GTK.gtk_spin_button_set_value(sbAnother, 4.0);
		final int cbbOperator = GTK.gtk_combo_box_text_new();
		GTK.gtk_combo_box_text_append_text(cbbOperator, "+");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "-");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "*");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "/");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "%");
		GTK.gtk_combo_box_text_append_text(cbbOperator, "^");
		
		int label1 = GTK.gtk_label_new("=");
		final int label2 = GTK.gtk_label_new("��֪������ʲô");
		
		// Box���ڴ洢  + - operator



		//��������
		
		GTK.gtk_grid_attach(houseGrid, sbOne, 0, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, cbbOperator, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonPlus, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonMinus, 1, start+1, 1, 1);
		GTK.gtk_grid_attach(houseGrid, sbAnother, 2, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label1, 3, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label2, 4, start, 1, 1);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(sbOne);
		GTK.gtk_widget_show(sbAnother);
		GTK.gtk_widget_show(cbbOperator);

		GTK.gtk_widget_show(label1);
		GTK.gtk_widget_show(label2);
		//GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(houseGrid);
		
		
		//�����¼����ƻ��� 
		GTK.g_signal_connect(cbbOperator, "changed", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				
				double plus1 = GTK.gtk_spin_button_get_value(sbOne);
				double plus2 = GTK.gtk_spin_button_get_value(sbAnother);
				
				//����һ�� ֻ��ģʽ��entry
				//GTK.gtk_editable_set_editable(entryOne, false); //ֻ������д�롣
				
				String cbbString = GTK.gtk_combo_box_text_get_active_text(cbbOperator);
				//panduan(plus1,entryOne);
				panduan(plus2,sbAnother);
				double sum1 = 0;
				if(cbbString.equalsIgnoreCase("+"))
				{
					 sum1 = plus1 + plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("-"))
				{
					sum1 = plus1 -plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("*"))
				{
					sum1 = plus1 * plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("/"))
				{
					panduan(plus2,sbAnother);
					sum1 = plus1 / plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("%"))
				{
					panduan(plus2,sbAnother);
					sum1 = plus1 % plus2;
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}else if(cbbString.equalsIgnoreCase("^"))
				{
					//String temp = Double.toString(plus2);
					
					sum1 = Math.pow(plus1,plus2);
					GTK.gtk_label_set_text(label2, Double.toString(sum1));
				}
				
			}
		}, null);
			
		
	}
	/**
	 * 
	 * @param plus1  entry1�������ַ���
	 * @param entry1 entry1�ı�ʶ
	 */
	public static void panduan(double plus1,int sbAnother)
	{
		if(plus1 == 0)
		{
			JOptionPane.showMessageDialog(null, "����������Ϊ0,�Ѿ���Ϊ�� ����������");
			GTK.gtk_spin_button_set_value(sbAnother, 1.0);;
			return;
		}
	}


}
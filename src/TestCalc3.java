
/**
 * @author Ҷ����
 * @version : ������v1.0
 * @version : ������v 2.0  �滻��entry ������SpinBox��ComboBox
 * @version : ������v 3.0  
 *              1.ʹ��button���������¼�  ���������Ǻ��������������ȵļ��㣬
 *              2.ʹ��TextView  and TextIter�ѽ�����뵽���С��� 
 *              3.����         
 *
 */
import javax.swing.JOptionPane;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
//import java.math.*;

public class TestCalc3
{

	/**
	 * @param args
	 */
	static int window;
	static boolean isEnter = false;

	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		//��ʼ��
		GTK.gtk_init();
		//��������  ���ó�static int window����
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//���ô�������
		GTK.gtk_window_set_title(window, "������v3.0");
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
	    
	    Calc(window,houseGrid,0); //������
	    specialCal(window,houseGrid,2); //������
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
		
		GTK.gtk_combo_box_set_active(cbbOperator, 0);
		int btnEquals = GTK.gtk_button_new_with_label("=");
		final int label2 = GTK.gtk_label_new("��֪������ʲô");
		
		int labelCommon = GTK.gtk_label_new("��ͨ���������㣺");
		// Box���ڴ洢  + - operator



		//��������
		GTK.gtk_grid_attach(houseGrid, labelCommon, 0, start, 1, 1);
		//����һ��������
		start= start + 1;
		GTK.gtk_grid_attach(houseGrid, sbOne, 0, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, cbbOperator, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonPlus, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonMinus, 1, start+1, 1, 1);
		GTK.gtk_grid_attach(houseGrid, sbAnother, 2, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, btnEquals, 3, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label2, 4, start, 1, 1);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(labelCommon);
		GTK.gtk_widget_show(sbOne);
		GTK.gtk_widget_show(sbAnother);
		GTK.gtk_widget_show(cbbOperator);

		GTK.gtk_widget_show(btnEquals);
		GTK.gtk_widget_show(label2);
		//GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(houseGrid);
		
		
		//�����¼����ƻ��� 
		GTK.g_signal_connect(btnEquals, "clicked", new IGCallBack()
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
	/**
	 * 
	 * @param window     ���������ڵı�ʶ
	 * @param gridHouse  ���ⷿ�ı�ʶ
	 * @param start      ���ⷿ�ı��
	 */
	public static void specialCal(int window,int gridHouse,int start)
	{
		// �����ؼ�
		final int btnSin = GTK.gtk_button_new_with_label("sin");
		final int btnCos = GTK.gtk_button_new_with_label("cos");
		final int btnTan = GTK.gtk_button_new_with_label("tan");
		final int btnAsin = GTK.gtk_button_new_with_label("asin");
		final int btnAcos = GTK.gtk_button_new_with_label("acos");
		final int btnAtan = GTK.gtk_button_new_with_label("atan");
		final int btnLog = GTK.gtk_button_new_with_label("log");
		final int btnSqrt = GTK.gtk_button_new_with_label("sqrt");
		final int btnabs = GTK.gtk_button_new_with_label("abs");
		final int btnFloor = GTK.gtk_button_new_with_label("floor");
		final int btnCeil = GTK.gtk_button_new_with_label("ceil");
		final int btnToDegree = GTK.gtk_button_new_with_label("���ȱ�Ƕ�");
		final int btnToRadius = GTK.gtk_button_new_with_label("�Ƕȱ仡��");
		final int spinbox= GTK.gtk_spin_button_new_with_range(-32767, 32767, 0.1);
		final int btnEquals = GTK.gtk_button_new_with_label("=");
		final int tv1 = GTK.gtk_text_view_new();
		final int labelSpecial = GTK.gtk_label_new("������������㣺(�ȵ�������������ֵ�����Ⱥ�)");
		//����GTK TextView��ģʽ
		GTK.gtk_text_view_set_wrap_mode(tv1, GTK.GTK_WRAP_WORD);
		//���ù�����
		final int scrolledBar = 0 ;


		GTK.gtk_spin_button_set_value(spinbox, 44.0);
		int innerGrid = GTK.gtk_grid_new();
		//���ӿؼ�
		GTK.gtk_grid_attach(innerGrid, btnSin, 0, 0, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnCos, 1, 0, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnTan, 2, 0, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnAsin, 0, 1, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnAcos, 1, 1, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnAtan, 2, 1, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnLog, 0, 2, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnSqrt, 1, 2, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnabs, 2, 2, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnFloor, 0, 3, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnCeil, 1, 3, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnToDegree, 0, 4, 1, 1);
		GTK.gtk_grid_attach(innerGrid, btnToRadius, 1, 4, 1, 1);
		
		GTK.gtk_grid_attach(gridHouse,labelSpecial,0,start,1,1);
		
		start = start + 1; //������һ��
		GTK.gtk_grid_attach(gridHouse, innerGrid, 0, start, 3, 5);
		GTK.gtk_grid_attach(gridHouse, spinbox, 3, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnEquals, 4, start, 1, 1);
		// GTK.gtk_grid_attach(gridHouse, tv1, 4, start+1, 1, 1);
		createScrollBar(scrolledBar,tv1,gridHouse,start);
		
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(labelSpecial);
		GTK.gtk_widget_show(btnSin);
		GTK.gtk_widget_show(btnCos);
		GTK.gtk_widget_show(btnTan);
		GTK.gtk_widget_show(btnAsin);
		GTK.gtk_widget_show(btnAcos);
		GTK.gtk_widget_show(btnAtan);
		GTK.gtk_widget_show(btnSqrt);
		GTK.gtk_widget_show(btnLog);
		GTK.gtk_widget_show(btnabs);
		GTK.gtk_widget_show(btnCeil);
		GTK.gtk_widget_show(btnFloor);
		GTK.gtk_widget_show(btnToDegree);
		GTK.gtk_widget_show(btnToRadius);
		GTK.gtk_widget_show(tv1); //���ǵ�����  ����show ���򿴲���
		GTK.gtk_widget_show(spinbox);
		GTK.gtk_widget_show(btnEquals);
		//GTK.gtk_widget_show(scrolledBar);  //��Ҫ�����showֻ��scrollbar���򣬲�Ȼ����
		GTK.gtk_widget_show(innerGrid);
		
		
		
		//���Ӱ�ť�¼�
		insertButtonEvent( btnSin,tv1);
		insertButtonEvent( btnCos,tv1);
		insertButtonEvent( btnTan,tv1);
		insertButtonEvent( btnAsin,tv1);
		insertButtonEvent( btnAcos,tv1);
		insertButtonEvent( btnAtan,tv1);
		insertButtonEvent( btnLog,tv1);
		insertButtonEvent( btnabs,tv1);
		insertButtonEvent( btnCeil,tv1);
		insertButtonEvent( btnFloor,tv1);
		insertButtonEvent( btnToDegree,tv1);
		insertButtonEvent( btnToRadius,tv1);
		
		
		GTK.g_signal_connect(spinbox,"changed",new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				isEnter = true;
			}
		},null);
		
		GTK.g_signal_connect(btnEquals, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				double sum1  = 0 ;
				double apple = GTK.gtk_spin_button_get_value(spinbox);
				if(isEnter == false)
				{
					JOptionPane.showMessageDialog(null, "��δѡ�������������������ѡ�񣬲��������֣���������");
					GTK.gtk_spin_button_set_value(spinbox, 44.0);
					return;
				}else
				{
					String temp = GetStringFromTextViewFunction(tv1);
					if(temp.equals("sin"))
					{
						double orange = Math.toRadians(apple);
						sum1  = Math.sin(orange);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("cos"))
					{
						double orange = Math.toRadians(apple);
						sum1  = Math.cos(orange);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("tan"))
					{
						double orange = Math.toRadians(apple);
						sum1  = Math.tan(orange);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("asin"))
					{
						double orange = Math.toRadians(apple);
						sum1  = Math.asin(orange);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("acos"))
					{
						double orange = Math.toRadians(apple);
						sum1  = Math.acos(orange);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("atan"))
					{
						double orange = Math.toRadians(apple);
						sum1  = Math.atan(orange);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("log"))
					{
						sum1  = Math.log(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("sqrt"))
					{
						sum1  = Math.sqrt(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("abs"))
					{
						sum1  = Math.abs(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("floor"))
					{
						sum1  = Math.floor(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("ceil"))
					{
						sum1  = Math.ceil(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("���ȱ�Ƕ�"))
					{
						sum1  = Math.toDegrees(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("�Ƕȱ仡��"))
					{
						sum1  = Math.toRadians(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}
				}
			}
		}, null);
		
	}
	
	/**
	 * 
	 * @param scrolledBar  ��������ʶ
	 * @param textview     �����ı���ʶ
	 * @param gridHouse    ���ⷿ�ı�ʶ
	 * @param start        ���ⷿ�ĳ�ʼ��ʼ��
	 */
	public static void createScrollBar(int scrolledBar,int textview,int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		//�������Ǹ��������
		GTK.gtk_grid_attach(gridHouse, scrolledBar, 4, start+1, 1, 1); // 4��Ϊ0 ����
		GTK.gtk_widget_set_size_request(scrolledBar, 300, 50);
		GTK.gtk_container_add(scrolledBar,textview); // ����textview����������������
	}
	
	/**
	 * 
	 * @param textview  �����ı�TextView�ı�ʶ
	 * @param temp      ����TextView ���ַ�����
	 */
	public static void InsertStringToTextViewFunction(int textview,String temp)
	{
		//TextIter��һ��TextView�ĵ�������
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //����һ���յ�iter����Ҫ��textBuffer���и�ֵ
		//GTK.gtk_text_iter_forward_to_end(textIter);
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// ����textview��textBuffer��ĩβ��
		GTK.gtk_text_buffer_insert(textBuffer, textIter, temp);
		
		//GTK.gtk_text_buffer_g
		GTK.gtk_text_iter_free(textIter);
		
	}
	/**
	 * 
	 * @param textview   TextView�Ķ����ı��ı�ʶ
	 * @return           ���ص��ַ���
	 */
	public static String GetStringFromTextViewFunction(int textview)
	{
		//TextIter��һ��TextView�ĵ�������
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //����һ���յ�iter����Ҫ��textBuffer���и�ֵ
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// ����textview��textBuffer��ĩβ��
		String temp = GTK.gtk_text_buffer_get_text(textBuffer);
		
		String[] splitArray = temp.split("\\n");
		temp = splitArray[splitArray.length-1];
		System.out.println(temp);
		return temp;
		//GTK.gtk_text_buffer_g
		//GTK.gtk_text_iter_free(textIter);
		
	}
	/**
	 * 
	 * @param btnSin   ��ť�ı�ʶ
	 * @param tv1      textview�ı�ʶ
	 */
	public static void insertButtonEvent(final int btnSin,final int tv1)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				String temp = GTK.gtk_button_get_label(btnSin);
				InsertStringToTextViewFunction(tv1,temp);
				isEnter = false;
			}
		}, null);
	}


}
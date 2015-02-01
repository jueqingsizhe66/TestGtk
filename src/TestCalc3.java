
/**
 * @author 叶昭良
 * @version : 计算器v1.0
 * @version : 计算器v 2.0  替换掉entry 采用了SpinBox和ComboBox
 * @version : 计算器v 3.0  
 *              1.使用button触发计算事件  加入了三角函数、对数函数等的计算，
 *              2.使用TextView  and TextIter把结果输入到其中。。 
 *              3.对于         
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
		// TODO 自动生成的方法存根
		//初始化
		GTK.gtk_init();
		//建立窗口  设置成static int window变量
		window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		//设置窗口名称
		GTK.gtk_window_set_title(window, "计算器v3.0");
		//添加窗口
		GTK.gtk_widget_show(window);
		
		//关闭对话框
		GTK.g_signal_connect(window, "destroy", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);
		
		//网格布局
	    int houseGrid = GTK.gtk_grid_new();
	    //载入计算机控件
	    
	    Calc(window,houseGrid,0); //有两行
	    specialCal(window,houseGrid,2); //有两行
	    //载入到windows当中
		GTK.gtk_container_add(window, houseGrid);
		//启动循环
		GTK.gtk_main();
		
	}
	/**
	 * 
	 * @param window     窗口的标识
 	 * @param houseGrid  整租房间的标识
	 * @param start      设置整租房间的起始行数
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
		final int label2 = GTK.gtk_label_new("我知道答案是什么");
		
		int labelCommon = GTK.gtk_label_new("普通的四则运算：");
		// Box用于存储  + - operator



		//加入整租
		GTK.gtk_grid_attach(houseGrid, labelCommon, 0, start, 1, 1);
		//在下一行再添加
		start= start + 1;
		GTK.gtk_grid_attach(houseGrid, sbOne, 0, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, cbbOperator, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonPlus, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonMinus, 1, start+1, 1, 1);
		GTK.gtk_grid_attach(houseGrid, sbAnother, 2, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, btnEquals, 3, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label2, 4, start, 1, 1);
		
		//显示控件
		GTK.gtk_widget_show(labelCommon);
		GTK.gtk_widget_show(sbOne);
		GTK.gtk_widget_show(sbAnother);
		GTK.gtk_widget_show(cbbOperator);

		GTK.gtk_widget_show(btnEquals);
		GTK.gtk_widget_show(label2);
		//GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(houseGrid);
		
		
		//加入事件控制机制 
		GTK.g_signal_connect(btnEquals, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				
				// TODO 自动生成的方法存根
				double plus1 = GTK.gtk_spin_button_get_value(sbOne);
				double plus2 = GTK.gtk_spin_button_get_value(sbAnother);
				
				//增加一个 只读模式的entry
				//GTK.gtk_editable_set_editable(entryOne, false); //只读不能写入。
				
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
	 * @param plus1  entry1的数字字符串
	 * @param entry1 entry1的标识
	 */
	public static void panduan(double plus1,int sbAnother)
	{
		if(plus1 == 0)
		{
			JOptionPane.showMessageDialog(null, "被除数不能为0,已经置为空 请重新输入");
			GTK.gtk_spin_button_set_value(sbAnother, 1.0);;
			return;
		}
	}
	/**
	 * 
	 * @param window     计算器窗口的标识
	 * @param gridHouse  整租房的标识
	 * @param start      整租房的编号
	 */
	public static void specialCal(int window,int gridHouse,int start)
	{
		// 建立控件
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
		final int btnToDegree = GTK.gtk_button_new_with_label("弧度变角度");
		final int btnToRadius = GTK.gtk_button_new_with_label("角度变弧度");
		final int spinbox= GTK.gtk_spin_button_new_with_range(-32767, 32767, 0.1);
		final int btnEquals = GTK.gtk_button_new_with_label("=");
		final int tv1 = GTK.gtk_text_view_new();
		final int labelSpecial = GTK.gtk_label_new("特殊的四则运算：(先点击运算符，再敲值，最后等号)");
		//设置GTK TextView的模式
		GTK.gtk_text_view_set_wrap_mode(tv1, GTK.GTK_WRAP_WORD);
		//设置滚动条
		final int scrolledBar = 0 ;


		GTK.gtk_spin_button_set_value(spinbox, 44.0);
		int innerGrid = GTK.gtk_grid_new();
		//添加控件
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
		
		start = start + 1; //跳到下一行
		GTK.gtk_grid_attach(gridHouse, innerGrid, 0, start, 3, 5);
		GTK.gtk_grid_attach(gridHouse, spinbox, 3, start, 1, 1);
		GTK.gtk_grid_attach(gridHouse, btnEquals, 4, start, 1, 1);
		// GTK.gtk_grid_attach(gridHouse, tv1, 4, start+1, 1, 1);
		createScrollBar(scrolledBar,tv1,gridHouse,start);
		
		
		//显示控件
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
		GTK.gtk_widget_show(tv1); //还是得先是  必须show 否则看不到
		GTK.gtk_widget_show(spinbox);
		GTK.gtk_widget_show(btnEquals);
		//GTK.gtk_widget_show(scrolledBar);  //不要在这边show只在scrollbar区域，不然报错
		GTK.gtk_widget_show(innerGrid);
		
		
		
		//添加按钮事件
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
				// TODO 自动生成的方法存根
				isEnter = true;
			}
		},null);
		
		GTK.g_signal_connect(btnEquals, "clicked", new IGCallBack() 
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				double sum1  = 0 ;
				double apple = GTK.gtk_spin_button_get_value(spinbox);
				if(isEnter == false)
				{
					JOptionPane.showMessageDialog(null, "您未选择特殊操作符，请重新选择，并输入数字，进行运算");
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
					}else if(temp.equals("弧度变角度"))
					{
						sum1  = Math.toDegrees(apple);
						String finalResult = "("+apple+")"+" = "+sum1+"\n";
						InsertStringToTextViewFunction(tv1,finalResult);
					}else if(temp.equals("角度变弧度"))
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
	 * @param scrolledBar  滚动条标识
	 * @param textview     多行文本标识
	 * @param gridHouse    整租房的标识
	 * @param start        整租房的初始开始处
	 */
	public static void createScrollBar(int scrolledBar,int textview,int gridHouse,int start)
	{
		scrolledBar = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrolledBar);
		//滚动条是更大的容器
		GTK.gtk_grid_attach(gridHouse, scrolledBar, 4, start+1, 1, 1); // 4改为0 报错
		GTK.gtk_widget_set_size_request(scrolledBar, 300, 50);
		GTK.gtk_container_add(scrolledBar,textview); // 添加textview到滚动条容器当中
	}
	
	/**
	 * 
	 * @param textview  多行文本TextView的标识
	 * @param temp      插入TextView 的字符串。
	 */
	public static void InsertStringToTextViewFunction(int textview,String temp)
	{
		//TextIter是一个TextView的迭代器。
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //这是一个空的iter，需要用textBuffer进行赋值
		//GTK.gtk_text_iter_forward_to_end(textIter);
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// 或者textview的textBuffer的末尾！
		GTK.gtk_text_buffer_insert(textBuffer, textIter, temp);
		
		//GTK.gtk_text_buffer_g
		GTK.gtk_text_iter_free(textIter);
		
	}
	/**
	 * 
	 * @param textview   TextView的多行文本的标识
	 * @return           返回的字符串
	 */
	public static String GetStringFromTextViewFunction(int textview)
	{
		//TextIter是一个TextView的迭代器。
		int textBuffer =  GTK.gtk_text_view_get_buffer(textview);
		int textIter = GTK.gtk_text_iter_new();  //这是一个空的iter，需要用textBuffer进行赋值
		GTK.gtk_text_buffer_get_end_iter(textBuffer, textIter);// 或者textview的textBuffer的末尾！
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
	 * @param btnSin   按钮的标识
	 * @param tv1      textview的标识
	 */
	public static void insertButtonEvent(final int btnSin,final int tv1)
	{
		GTK.g_signal_connect(btnSin, "clicked",new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				//double sbValue = GTK.gtk_spin_button_get_value(spinbox);
				String temp = GTK.gtk_button_get_label(btnSin);
				InsertStringToTextViewFunction(tv1,temp);
				isEnter = false;
			}
		}, null);
	}


}

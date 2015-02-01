
/**
 * @author  赵帅  修改by Yezhaoliang
 * @version  计算器 v1.0
 * 
 */
import com.rupeng.gtk4j.GTK;//引入类
import com.rupeng.gtk4j.IGCallBack;
import com.sun.webkit.ContextMenu.ShowContext;

//调用类的方法而已。以前居然不懂
public class Counter
{
	static int n = 0;// btn数组数值
	static int[] btn = new int[16];// 建立btn数组
	static int entry;
	static String str = "";// 另初始值为""(entry的文本)

	/**
	 * 
	 * @param bt 按钮的标识
	 */
	private static void setBtn(final int bt)// 定义取得按钮值得方法
	{
		GTK.g_signal_connect(bt, "clicked", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				String getNum = GTK.gtk_button_get_label(bt);// 获得btn数值
				str += getNum;
				GTK.gtk_entry_set_text(entry, str);
			}
		}, null);
	}

	public static void main(String[] args)
	{
		// 初始化GTK环境
		GTK.gtk_init();
		// 新建一个顶级窗口
		int window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "整数计算器 V1.0");
		GTK.gtk_widget_show(window);// 显示窗口
		
		//安静退出
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO 自动生成的方法存根
				GTK.gtk_main_quit();
			}
		}, null);

		// 网格布局 网格布局有点麻烦
		int fixed = GTK.gtk_fixed_new();
		GTK.gtk_container_add(window, fixed);
		GTK.gtk_widget_show(fixed);
		
		//载入控件
		createCalc(window, fixed);

		GTK.gtk_main();// 启动主循环，不加闪退

	}
	/**
	 * 
	 * @param window  窗口标识
	 * @param fixed   采用固定布局方式
	 */
	public static void createCalc(int window, int fixed)
	{
		//创建控件   +   添加控件到整租房Fixed   +  显示控件
		entry = GTK.gtk_entry_new();
		GTK.gtk_widget_set_size_request(entry, 250, 50);
		GTK.gtk_fixed_put(fixed, entry, 0, 0);
		GTK.gtk_widget_show(entry);

		for (int i = 1; i < 4; i++)// 双层循环给btn赋值
		{
			for (int j = 0; j < 3; j++)
			{
				n++; //n==0 开始

				btn[n] = GTK.gtk_button_new_with_label(n + "");

				GTK.gtk_widget_set_size_request(btn[n], 40, 40);
				GTK.gtk_fixed_put(fixed, btn[n], j * 50, i * 50);
				GTK.gtk_widget_show(btn[n]);
			}
		}
		btn[0] = GTK.gtk_button_new_with_label(0 + "");
		GTK.gtk_widget_set_size_request(btn[0], 40, 40);
		GTK.gtk_fixed_put(fixed, btn[0], 3 * 50, 1 * 50);
		GTK.gtk_widget_show(btn[0]);
		
		btn[10] = GTK.gtk_button_new_with_label("+");

		GTK.gtk_widget_set_size_request(btn[10], 40, 40);
		GTK.gtk_fixed_put(fixed, btn[10], 3 * 50, 2 * 50);
		GTK.gtk_widget_show(btn[10]);

		btn[11] = GTK.gtk_button_new_with_label("-");
		GTK.gtk_widget_set_size_request(btn[11], 40, 40);
		GTK.gtk_fixed_put(fixed, btn[11], 4 * 50, 2 * 50);
		GTK.gtk_widget_show(btn[11]);

		btn[12] = GTK.gtk_button_new_with_label("*");
		GTK.gtk_widget_set_size_request(btn[12], 40, 40);
		GTK.gtk_fixed_put(fixed, btn[12], 3 * 50, 3 * 50);
		GTK.gtk_widget_show(btn[12]);

		btn[13] = GTK.gtk_button_new_with_label("/");
		GTK.gtk_widget_set_size_request(btn[13], 40, 40);
		GTK.gtk_fixed_put(fixed, btn[13], 4 * 50, 3 * 50);
		GTK.gtk_widget_show(btn[13]);

		btn[14] = GTK.gtk_button_new_with_label("=");
		GTK.gtk_widget_set_size_request(btn[14], 50, 40);
		GTK.gtk_fixed_put(fixed, btn[14], 5 * 50, 0 * 50);
		GTK.gtk_widget_show(btn[14]);

		//清除按键
		int imag = GTK.gtk_image_new_from_stock(GTK.GTK_STOCK_CANCEL,
				GTK.GTK_ICON_SIZE_BUTTON);
		btn[15] = GTK.gtk_button_new();
		GTK.gtk_button_set_image(btn[15], imag);
		GTK.gtk_widget_set_size_request(btn[15], 40, 40);
		GTK.gtk_widget_show(btn[15]);
		GTK.gtk_fixed_put(fixed, btn[15], 200, 50);
		
		
		//添加事件
		GTK.g_signal_connect(btn[15], "clicked", new IGCallBack()// 监听"="
				{

					@Override
					public void execute(int instance, int eventData,
							Object object)
					{
						if (str.length() > 0)
						{
							str = str.substring(0, str.length() - 1);
							GTK.gtk_entry_set_text(entry, str);
						} else
						{
							return;
						}

					}
				}, null);

		for (int i = 0; i < 14; i++)// for循环setBtn方法为btn[i]设置监听
		{
			setBtn(btn[i]);
		}
		/*
		 * setBtn(btn[0]); setBtn(btn[1]); setBtn(btn[2]); setBtn(btn[3]);
		 * setBtn(btn[4]); setBtn(btn[5]); setBtn(btn[6]); setBtn(btn[7]);
		 * setBtn(btn[8]); setBtn(btn[9]); setBtn(btn[10]); setBtn(btn[11]);
		 * setBtn(btn[12]); setBtn(btn[13]);
		 */

		GTK.g_signal_connect(btn[14], "clicked", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{

				char ch = 0;// 加减乘除的符号
				int nu = 0;// 加减乘除的位置
				boolean bool = true;
				for (int z = 1; z < str.length(); z++)// 局部变量z
				{
					if (!Character.isDigit(str.charAt(z)))// 判断哪个是非数字的符号
					{
						ch = str.charAt(z);// 得到符号
						nu = z;// 取得符号务位置
						bool = false;
					}

				}
				/*
				 * if (str.charAt(0) == 48) { GTK.gtk_entry_set_text(entry,
				 * "此计算器不算小数"); //str = ""; return; }//为何分开会有冲突
				 */
				if (str == ""
						|| (!(Character.isDigit(str.charAt(0))) && (str
								.charAt(0) != '-')))// 如果直接输入"符号”并且不是"-"
				// ，则返回
				{
					GTK.gtk_entry_set_text(entry, "请输入数字");
					str = "";
					return;
				}
				if (bool || !Character.isDigit(str.charAt(str.length() - 1)))// 如果只输入数字或者数字加（+-*/）就点击“=”则返回
				{
					GTK.gtk_entry_set_text(entry, str);
					return;
				}

				int num1 = Integer.parseInt(str.substring(0, nu));// 得到符号前的数值
				int num2 = Integer.parseInt(str.substring(nu + 1));// 获得符号后面的数值
				int num = 0;
				//
				if (ch == '+')
				{
					num = num1 + num2;
				} else if (ch == '-')
				{
					num = num1 - num2;
				} else if (ch == '*')
				{
					num = num1 * num2;
				} else
				{
					num = num1 / num2;
				}
				String getnum1 = Integer.toString(num);

				str = getnum1;// 令str保留结果可以继续计算

				GTK.gtk_entry_set_text(entry, getnum1);

			}
		}, null);
	}
}
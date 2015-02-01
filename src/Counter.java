
/**
 * @author  ��˧  �޸�by Yezhaoliang
 * @version  ������ v1.0
 * 
 */
import com.rupeng.gtk4j.GTK;//������
import com.rupeng.gtk4j.IGCallBack;
import com.sun.webkit.ContextMenu.ShowContext;

//������ķ������ѡ���ǰ��Ȼ����
public class Counter
{
	static int n = 0;// btn������ֵ
	static int[] btn = new int[16];// ����btn����
	static int entry;
	static String str = "";// ����ʼֵΪ""(entry���ı�)

	/**
	 * 
	 * @param bt ��ť�ı�ʶ
	 */
	private static void setBtn(final int bt)// ����ȡ�ð�ťֵ�÷���
	{
		GTK.g_signal_connect(bt, "clicked", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				String getNum = GTK.gtk_button_get_label(bt);// ���btn��ֵ
				str += getNum;
				GTK.gtk_entry_set_text(entry, str);
			}
		}, null);
	}

	public static void main(String[] args)
	{
		// ��ʼ��GTK����
		GTK.gtk_init();
		// �½�һ����������
		int window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "���������� V1.0");
		GTK.gtk_widget_show(window);// ��ʾ����
		
		//�����˳�
		GTK.g_signal_connect(window, "destroy", new IGCallBack()
		{

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				GTK.gtk_main_quit();
			}
		}, null);

		// ���񲼾� ���񲼾��е��鷳
		int fixed = GTK.gtk_fixed_new();
		GTK.gtk_container_add(window, fixed);
		GTK.gtk_widget_show(fixed);
		
		//����ؼ�
		createCalc(window, fixed);

		GTK.gtk_main();// ������ѭ������������

	}
	/**
	 * 
	 * @param window  ���ڱ�ʶ
	 * @param fixed   ���ù̶����ַ�ʽ
	 */
	public static void createCalc(int window, int fixed)
	{
		//�����ؼ�   +   ���ӿؼ������ⷿFixed   +  ��ʾ�ؼ�
		entry = GTK.gtk_entry_new();
		GTK.gtk_widget_set_size_request(entry, 250, 50);
		GTK.gtk_fixed_put(fixed, entry, 0, 0);
		GTK.gtk_widget_show(entry);

		for (int i = 1; i < 4; i++)// ˫��ѭ����btn��ֵ
		{
			for (int j = 0; j < 3; j++)
			{
				n++; //n==0 ��ʼ

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

		//�������
		int imag = GTK.gtk_image_new_from_stock(GTK.GTK_STOCK_CANCEL,
				GTK.GTK_ICON_SIZE_BUTTON);
		btn[15] = GTK.gtk_button_new();
		GTK.gtk_button_set_image(btn[15], imag);
		GTK.gtk_widget_set_size_request(btn[15], 40, 40);
		GTK.gtk_widget_show(btn[15]);
		GTK.gtk_fixed_put(fixed, btn[15], 200, 50);
		
		
		//�����¼�
		GTK.g_signal_connect(btn[15], "clicked", new IGCallBack()// ����"="
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

		for (int i = 0; i < 14; i++)// forѭ��setBtn����Ϊbtn[i]���ü���
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

				char ch = 0;// �Ӽ��˳��ķ���
				int nu = 0;// �Ӽ��˳���λ��
				boolean bool = true;
				for (int z = 1; z < str.length(); z++)// �ֲ�����z
				{
					if (!Character.isDigit(str.charAt(z)))// �ж��ĸ��Ƿ����ֵķ���
					{
						ch = str.charAt(z);// �õ�����
						nu = z;// ȡ�÷�����λ��
						bool = false;
					}

				}
				/*
				 * if (str.charAt(0) == 48) { GTK.gtk_entry_set_text(entry,
				 * "�˼���������С��"); //str = ""; return; }//Ϊ�ηֿ����г�ͻ
				 */
				if (str == ""
						|| (!(Character.isDigit(str.charAt(0))) && (str
								.charAt(0) != '-')))// ���ֱ������"���š����Ҳ���"-"
				// ���򷵻�
				{
					GTK.gtk_entry_set_text(entry, "����������");
					str = "";
					return;
				}
				if (bool || !Character.isDigit(str.charAt(str.length() - 1)))// ���ֻ�������ֻ������ּӣ�+-*/���͵����=���򷵻�
				{
					GTK.gtk_entry_set_text(entry, str);
					return;
				}

				int num1 = Integer.parseInt(str.substring(0, nu));// �õ�����ǰ����ֵ
				int num2 = Integer.parseInt(str.substring(nu + 1));// ��÷��ź������ֵ
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

				str = getnum1;// ��str����������Լ�������

				GTK.gtk_entry_set_text(entry, getnum1);

			}
		}, null);
	}
}
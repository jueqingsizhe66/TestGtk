
/**
 * @author Ҷ����
 * @timestart  18:55
 * @timeEnd    19:50
 * @version : ������v1.0
 *
 */
import javax.swing.JOptionPane;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
public class TestCalc
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
		GTK.gtk_window_set_title(window, "������v1.0");
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
		final int entryOne = GTK.gtk_entry_new();
		final int entryAnother = GTK.gtk_entry_new();
		
		final int buttonPlus = GTK.gtk_button_new_with_label("+");
		int buttonMinus= GTK.gtk_button_new_with_label("-");
		
		int label1 = GTK.gtk_label_new("=");
		final int label2 = GTK.gtk_label_new("��֪������ʲô");
		
		// Box���ڴ洢  + - operator
		int box =  GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 1);
		GTK.gtk_box_pack_start(box, buttonPlus, false, false, 0);
		GTK.gtk_box_pack_start(box, buttonMinus, false, false, 0);


		//��������
		
		GTK.gtk_grid_attach(houseGrid, entryOne, 0, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, box, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonPlus, 1, start, 1, 1);
//		GTK.gtk_grid_attach(houseGrid, buttonMinus, 1, start+1, 1, 1);
		GTK.gtk_grid_attach(houseGrid, entryAnother, 2, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label1, 3, start, 1, 1);
		GTK.gtk_grid_attach(houseGrid, label2, 4, start, 1, 1);
		
		//��ʾ�ؼ�
		GTK.gtk_widget_show(entryOne);
		GTK.gtk_widget_show(entryAnother);
		GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(buttonPlus);
		GTK.gtk_widget_show(buttonMinus);
		GTK.gtk_widget_show(label1);
		GTK.gtk_widget_show(label2);
		//GTK.gtk_widget_show(box);
		GTK.gtk_widget_show(houseGrid);
		
		
		//�����¼����ƻ��� 
		GTK.g_signal_connect(buttonPlus, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������

				String plus1 = GTK.gtk_entry_get_text(entryOne);
				String plus2 = GTK.gtk_entry_get_text(entryAnother);
				// �Ľ�  ����������Ŀ
				GTK.gtk_entry_set_max_length(entryOne, 5);
				GTK.gtk_entry_set_max_length(entryAnother, 5);
				
				//����һ�� ֻ��ģʽ��entry
				//GTK.gtk_editable_set_editable(entryOne, false); //ֻ������д�롣
				
				
				
				panduan(plus1,entryOne);
				panduan(plus2,entryAnother);
				int sum1 = Integer.parseInt(plus1)+Integer.parseInt(plus2);
				GTK.gtk_label_set_text(label2, Integer.toString(sum1));
			}
		}, null);
		
		
		GTK.g_signal_connect(buttonMinus, "clicked", new IGCallBack()
		{
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO �Զ����ɵķ������
				String plus1 = GTK.gtk_entry_get_text(entryOne);
				String plus2 = GTK.gtk_entry_get_text(entryAnother);
				
				int sum1 = Integer.parseInt(plus1)-Integer.parseInt(plus2);
				GTK.gtk_label_set_text(label2, Integer.toString(sum1));
			}
		}, null);
		
		
	}
	/**
	 * 
	 * @param plus1  entry1�������ַ���
	 * @param entry1 entry1�ı�ʶ
	 */
	public static void panduan(String plus1,int entry1)
	{

		char[] panduanOne = plus1.toCharArray();
		for(int i = 0 ; i < panduanOne.length; i++)
		{
			if(panduanOne[i] >= 47 && panduanOne[i] <= 57)
			{
				continue;
			}else
			{
				JOptionPane.showMessageDialog(null, "�������Entry�����������,�Ѿ���Ϊ�� ����������");
				GTK.gtk_entry_set_text(entry1, "");
				return;
			}
		}

	}

}
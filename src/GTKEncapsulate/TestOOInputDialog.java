/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��8������10:36:09
 * @version   GTKEncapsulateTestOOInputDialog V1.0
 */
public class TestOOInputDialog
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GTK.gtk_init();
		
		OOInputDialog oidi = new OOInputDialog();
	//	oidi.setValue("�������������ı�");  ��Ϊ��Щ��Ϣ�Ķ�������run�����
		// �������ǾͰѹ����Ի�����ڹ��캯������
		oidi.setValue("�������������ı�");
		oidi.run();
		
		GTK.gtk_main();
	}

}
/**
 * 
 */
package GTKEncapsulate;



import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��5������1:46:29
 * @version   GTKEncapsulateOOMenu V1.0
 */
public class OOMenu extends OOWidget
{
	//������
	//1������һ�˵�
	//2����һ���˵�ͷ
	//3�����ܵĲ˵�����������˵�ͷ���ҵ��˵�����
	private static VerticalMenu[] vm = new VerticalMenu[10];
	private static Menua[] ma = new Menua[10];
	//ͨ��i��ֵ ��λ��ĳ����ֱ�˵���
	private static int i =0;
	public OOMenu() 
	{
		setId(GTK.gtk_menu_bar_new());
	}
	
	//�Ѵ�ֱ������ˮƽ����
	public void addVerticalMenuTOBar(String VerticalMenuName)
	{
		//���ӵ�ˮƽ�Ĳ˵�����
		GTK.gtk_menu_shell_append(this.getId(), vm[i].getId());
	}
	//������ ����һ����ֱ��
	public void createVerticalMenu(String VerticalMenuName)
	{
		vm[i] = new VerticalMenu(VerticalMenuName);
		vm[i].show();
		i++;
	}
	//��һ��  ����һ��menu
	public void createMenu()
	{
		ma[i] = new Menua();
		ma[i].show();
	}
	//�ڶ���  ���������߲�
	public void addMenuVegetable(String vegetableName)
	{
		ma[i].addVegetable(vegetableName);
	}
	//����װmenus
	class VerticalMenu extends OOWidget
	{
		public VerticalMenu(String vertivalMenuName)
		{
			setId(GTK.gtk_menu_item_new_with_mnemonic(vertivalMenuName));
		}
		//����Menu����
		public void fillVerticalMenu(Menua menuApple)
		{
			this.show();
			GTK.gtk_menu_item_set_submenu(this.getId(), menuApple.getId());
		}
		public Menua createMenu()
		{
			Menua ma = new Menua();
			return ma;
		}
		
		//����һ���˵�������ж������꣬�ڰ�menuApple���ӵ���ֱ��
		public void addMenuVegetable(Menua menuApple,String vegetableName)
		{
			menuApple.addVegetable(vegetableName);		
		}
		

	}
	
	//�ڲ���Ҳ�Ǽ̳�OOWidget��  ��Ҫд
	class Menua extends OOWidget
	{
		//private String menuName;
		public Menua()
		{
			setId(GTK.gtk_menu_new());
		}
		
		public void addVegetable(String vegetableName)
		{
			Vegetable vg = new Vegetable(vegetableName);
			vg.show();
			GTK.gtk_menu_shell_append(this.getId(), vg.getId());
		}
		
	/*			public String getText()
		{
			return this.menuName;
		}*/
		
		//�˵�������߲�
		class Vegetable extends OOWidget
		{
			public Vegetable(String vegetableName)
			{
				setId(GTK.gtk_menu_item_new_with_label(vegetableName));
			}
		}
	}

}

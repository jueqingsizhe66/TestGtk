/**
 * 
 */
package GTKEncapsulate;



import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日上午1:46:29
 * @version   GTKEncapsulateOOMenu V1.0
 */
public class OOMenu extends OOWidget
{
	//分三步
	//1创建单一菜单
	//2创建一个菜单头
	//3创建总的菜单条，把许多菜单头都挂到菜单条上
	private static VerticalMenu[] vm = new VerticalMenu[10];
	private static Menua[] ma = new Menua[10];
	//通过i的值 定位到某个垂直菜单条
	private static int i =0;
	public OOMenu() 
	{
		setId(GTK.gtk_menu_bar_new());
	}
	
	//把垂直条加入水平条中
	public void addVerticalMenuTOBar(String VerticalMenuName)
	{
		//添加到水平的菜单条中
		GTK.gtk_menu_shell_append(this.getId(), vm[i].getId());
	}
	//第三步 创建一个垂直条
	public void createVerticalMenu(String VerticalMenuName)
	{
		vm[i] = new VerticalMenu(VerticalMenuName);
		vm[i].show();
		i++;
	}
	//第一步  创建一个menu
	public void createMenu()
	{
		ma[i] = new Menua();
		ma[i].show();
	}
	//第二部  不但添加蔬菜
	public void addMenuVegetable(String vegetableName)
	{
		ma[i].addVegetable(vegetableName);
	}
	//用于装menus
	class VerticalMenu extends OOWidget
	{
		public VerticalMenu(String vertivalMenuName)
		{
			setId(GTK.gtk_menu_item_new_with_mnemonic(vertivalMenuName));
		}
		//导入Menu对象！
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
		
		//增加一个菜单项，当所有都添加完，在把menuApple添加到垂直中
		public void addMenuVegetable(Menua menuApple,String vegetableName)
		{
			menuApple.addVegetable(vegetableName);		
		}
		

	}
	
	//内部类也是继承OOWidget的  需要写
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
		
		//菜单里面的蔬菜
		class Vegetable extends OOWidget
		{
			public Vegetable(String vegetableName)
			{
				setId(GTK.gtk_menu_item_new_with_label(vegetableName));
			}
		}
	}

}


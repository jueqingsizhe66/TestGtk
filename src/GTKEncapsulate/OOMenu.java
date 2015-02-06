/**
 * 
 */
package GTKEncapsulate;



import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日上午1:46:29
 * @version   GTKEncapsulateOOMenu V1.0
 *                           把menu一分为三
 */
public class OOMenu extends OOWidget
{
	//分三步
	//1创建单一菜单
	//2创建一个菜单头
	//3创建总的菜单条，把许多菜单头都挂到菜单条上
	private static SubMenu[] vm = new SubMenu[10];
	private static Menua[] ma = new Menua[10];
	//通过i的值 定位到某个垂直菜单条
	private static int i =0;
	public OOMenu() 
	{
		setId(GTK.gtk_menu_bar_new());
	}
	
	//把垂直条加入水平条中
	//public void addVerticalMenuTOBar(String VerticalMenuName)  修正为
	public void addVerticalMenuTOBar()
	{
		//添加到水平的菜单条中
		vm[i].fillSubMenu(ma[i]);
		GTK.gtk_menu_shell_append(this.getId(), vm[i].getId());
		i++;
	}
	//第三步 创建一个垂直条
	public void createVerticalMenu(String VerticalMenuName)
	{
		vm[i] = new SubMenu(VerticalMenuName);
		vm[i].show();
		//出现了一个bug  必须把它移到add之后，因为顺序一般是  createVerticalMenu-->createMenu-->
		//addMenuVegetable..-->addVerticalMenuTOBar
		//不然会导致空指针！！所以注释掉i++,并把它放到addVertical
		//i++;
	}
	//第一步  创建一个menu
	public void createMenu()
	{
		ma[i] = new Menua();
		ma[i].show();
	}
	//第二部  不断添加蔬菜
	public void addMenuVegetable(OOMenuVegetable tomato)
	{
		 ma[i].addVegetable(tomato);
	}
	//用于装menus
	class SubMenu extends OOWidget
	{
		public SubMenu(String vertivalMenuName)
		{
			setId(GTK.gtk_menu_item_new_with_mnemonic(vertivalMenuName));
		}
		//导入Menu对象！
		//必须在addSubMenuToMenuBar之前添加fillSubMenu的调用
		public void fillSubMenu(Menua menuApple)
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
		//OOMenuVegetable是我新建的一个蔬菜类！从OOMenu的内部类隔离出来
		public void addMenuVegetable(Menua menuApple,OOMenuVegetable tomato)
		{
			 menuApple.addVegetable(tomato);		
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
		//从void 类型变到Vegetable类型
		public void addVegetable(OOMenuVegetable tomato)
		{
			tomato.show();
			GTK.gtk_menu_shell_append(this.getId(), tomato.getId());
			//return tomato;
		}
		
	/*			public String getText()
		{
			return this.menuName;
		}*/
		
		
	}
	//菜单里面的蔬菜
/*		class Vegetable extends OOWidget
		{
			public Vegetable(String vegetableName)
			{
				setId(GTK.gtk_menu_item_new_with_label(vegetableName));
			}
		}*/

}


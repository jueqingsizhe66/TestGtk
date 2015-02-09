
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    叶昭良
 * @time      2015年2月5日下午5:34:51
 * @version   GTKEncapsulateOODialog V1.0
 *                                V2.0修改 为OOWindow 使他变成单容器控件
 *                                V3.0 增加了ActionArea(可以增加控件按钮，表征dialog的行为区域）
 *                                          ContentArea （可以增加显示的内容，表征dialog的内容区域）
 *                                V4.0  增加了response, 修改了run ACtionArea  contentArea的方法为OOResponseTyep
 *                                
 */
public class OODialog extends OOWindow
{
	private static int actionArea = 0;
	private static int contentArea = 0;
	public OODialog()
	{
		setId(GTK.gtk_dialog_new());
	}
	/**
	 *   V1.0 在一切设置完毕后  必须要让他run起来，类似于线程的做法,并且一定要摧毁它 this.destroy..
	 */
/*	public  int run()
	{
		int response =  GTK.gtk_dialog_run(this.getId());
		return response;
	}*/
	/**
	 * 
	 * @return  V2.0 在一切设置完毕后  必须要让他run起来，类似于线程的做法,并且一定要摧毁它 this.destroy..
	 *           返回一个OOResponseType类型的数据  保证数据的有效性
	 */
	public OOResponseType run()
	{
		int response =  GTK.gtk_dialog_run(this.getId());
		return  OOResponseType.parseResponseType(response);
	}
	/**
	 *   创建一个Actionarea并赋值给类变量，类似于grid 和box的作用
	 *   使得dialog可以再增加控件的行为区域
	 */
	public OOBox createActionArea() 
	{
		actionArea =  GTK.gtk_dialog_get_action_area(this.getId());
		//默认的GTK+的actionArea的返回值 为OOBox类型，返回OOBox编号。
		OOBox banana = new OOBox(actionArea);
		return banana;
	}
	/**
	 * V1.0  创建一个ContentArea并赋值给类变量，类似于grid 和box的作用
	 *   使得dialog可以再增加控件的内容区域
	 *   contentarea并没有限制为OOBox类型，具体还可以调研，未确认
	 *  
	 */
	public OOContainer createContentArea()
	{
		contentArea =  GTK.gtk_dialog_get_content_area(this.getId());
		//contentArea默认的范围更广  就可以是grid box bin...
		OOContainer orange = new OOContainer(contentArea);
		return orange;
		
	}
	/**
	 * 
	 * @param banana  OOResonpseType类型变量
	 *                     若给定一个返回值类型 对话框做出一定的反应，比如打开，保存等。
	 */
	public void response(OOResponseType banana)
	{
		GTK.gtk_dialog_response(this.getId(), banana.getValue());
	}
	
/*	*//**
	 * 
	 * @param ooc  待添加的控件对象的ID，一般是button类
	 * GTK.gtk_container_add(areaDialog, btnApple);  一般是横的box的形式添加，ActionArea类似 
	 *//*
	public void addWidget(OOContainer ooc)
	{
		GTK.gtk_container_add(actionArea, ooc.getId());
	}
	
	*//**
	 * 
	 * @param ooc  待添加的控件对象的ID  可以是一个gridID  也可以是一个treeView,textview等
	 *        关键作用是显示信息，书写信息
	 *//*
	public void addWidgetContent(OOContainer ooc)
	{
		GTK.gtk_container_add(contentArea, ooc.getId());
	}*/
	
	
	
}

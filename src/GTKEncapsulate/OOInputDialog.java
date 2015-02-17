/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月7日下午12:09:25
 * @version   GTKEncapsulateOOInputDialog V1.0  暂时没办法 如鹏封装未有
 *                       V2.0原来这个是可以在OODialog实现之后，就可以实现的部分
 *                       V3.0 终于明白了response的作用是什么，让添加到OODialog的控件具有什么的信号！
 *                                         比如你添加OOButton空间到OODialog，当点击按钮，发出一个OOResponseTyep
 *                                         的ok信号就可以用 this.response(OOResponseType.OK)来执行
 *                                         然后你就可以让这个对话框给run起来，接受返回值的信号！判断到底要做什么行为
 */
public class OOInputDialog extends OODialog
{
/*	public OOInputDialog()
	{
		setId(GTK.gtk_mes);
	}*/
/*	public OOInputDialog()
	{
		super();
	}*/
	//默认采用父类的构造方法  super()可以不写
	private OOBox ob = null;
	private OOContainer oc = null;
	OOEntry entryApple = null ;
	OOButton obOk = null;
	OOButton obCancel = null;
	OOInputDialog oid = this;
	OOLabel olInput = null;
	
	//构造函数内部创建界面
	public OOInputDialog()
	{
		//虽然contentarea只可以容纳一个控件，但是我可以利用box grid，类似于window的作用。依然丰盛
			oc = this.createContentArea();
			OOBox obTemp = new OOBox(true);
			obTemp.show();
			oc.add(obTemp);
			
			olInput = new OOLabel("请输入：");
			olInput.show();
			obTemp.addWidget(olInput);
			entryApple = new OOEntry();
			entryApple.show();
			obTemp.addWidget(entryApple);
	
			
			obOk = new OOButton("确定");
			obOk.show();
			obOk.addClickedListener(new IGCallBack()
			{
				
				@Override
				public void execute(int instance, int eventData, Object object)
				{
					// TODO Auto-generated method stub
					// 为什么这边需要 response(GtkResponseType.OK);
					//  run过程默认是执行的  再说了response根本不应该在这边调用
					response(OOResponseType.OK);
					/*String text = getValue();
					System.out.println(text);
					oid.destroy();
					OOMessageDialog.showInfo(text, "你输入的是");*/
					//GTK.gtk_widget_destroy(oid.getId()); //可以放在messagebox之前或者之后
					//杨老师把它防砸meesagebox之前
				}
			});
			obCancel = new OOButton("取消");
			obCancel.show();
			obCancel.addClickedListener(new IGCallBack()
			{
				
				@Override
				public void execute(int instance, int eventData, Object object)
				{
					// TODO Auto-generated method stub
					// 为什么这边需要 response(GtkResponseType.OK);
					response(OOResponseType.CANCEL);
					//oid.destroy();
					//GTK.gtk_widget_destroy(oid.getId());
					//GTK.gtk_main_quit();
				}
			});
			ob = this.createActionArea();
			
	
			ob.addWidget(obOk);
			ob.addWidget(obCancel);
				

	}
	
/*	public OOResponseType run()
	{
		this.addDestroyListener(new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				GTK.gtk_widget_destroy(oid.getId());
				//GTK.gtk_main_quit();
			}
		});
		
	
	
		int response =  GTK.gtk_dialog_run(this.getId());
		return  OOResponseType.parseResponseType(response);
	}*/
	public void setValue(String text)
	{
		entryApple.setText(text);
	}
	public String getValue()
	{
		return entryApple.getText();
	}
}

/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��7������12:09:25
 * @version   GTKEncapsulateOOInputDialog V1.0  ��ʱû�취 ������װδ��
 *                       ԭ������ǿ�����OODialogʵ��֮�󣬾Ϳ���ʵ�ֵĲ���
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
	//Ĭ�ϲ��ø���Ĺ��췽��  super()���Բ�д
	private OOBox ob = null;
	private OOContainer oc = null;
	OOEntry entryApple = null ;
	OOButton obOk = null;
	OOButton obCancel = null;
	OOInputDialog oid = this;
	OOLabel olInput = null;
	
	//���캯���ڲ���������
	public OOInputDialog()
	{
		//��Ȼcontentareaֻ��������һ���ؼ��������ҿ�������box grid��������window�����á���Ȼ��ʢ
				oc = this.createContentArea();
				OOBox obTemp = new OOBox(true);
				obTemp.show();
				oc.add(obTemp);
				
				olInput = new OOLabel("�����룺");
				olInput.show();
				obTemp.addWidget(olInput);
				entryApple = new OOEntry();
				entryApple.show();
				obTemp.addWidget(entryApple);

				
				obOk = new OOButton("ȷ��");
				obOk.show();
				obOk.addClickedListener(new IGCallBack()
				{
					
					@Override
					public void execute(int instance, int eventData, Object object)
					{
						// TODO Auto-generated method stub
						// Ϊʲô�����Ҫ response(GtkResponseType.OK);
						//  run����Ĭ����ִ�е�  ��˵��response������Ӧ������ߵ���
						String text = getValue();
						System.out.println(text);
						oid.destroy();
						OOMessageDialog.showInfo(text, "���������");
						//GTK.gtk_widget_destroy(oid.getId()); //���Է���messagebox֮ǰ����֮��
						//����ʦ��������meesagebox֮ǰ
					}
				});
				obCancel = new OOButton("ȡ��");
				obCancel.show();
				obCancel.addClickedListener(new IGCallBack()
				{
					
					@Override
					public void execute(int instance, int eventData, Object object)
					{
						// TODO Auto-generated method stub
						// Ϊʲô�����Ҫ response(GtkResponseType.OK);
						oid.destroy();
						//GTK.gtk_widget_destroy(oid.getId());
						//GTK.gtk_main_quit();
					}
				});
				ob = this.createActionArea();
				

				ob.addWidget(obOk);
				ob.addWidget(obCancel);
				

	}
	
	public OOResponseType run()
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
	}
	public void setValue(String text)
	{
		entryApple.setText(text);
	}
	public String getValue()
	{
		return entryApple.getText();
	}
}
/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��6������3:33:43
 * @version   GTKEncapsulateOOBin V1.0
 */
public class OOBin extends OOContainer
{
	@Override
	public void add(OOWidget ow)
	{
		int[] children = GTK.gtk_container_get_children(this.getId());
		if(children.length <= 0 )
		{
			//���ø����add����
			super.add(ow);
			//super.add  ���ø���
			//this.add   ���ñ���
			//super() ���ø��๹�췽��(�ڹ��캯����)
			//this()  ���ñ���Ĺ��췽��(�ڹ��캯���У��ǵ���Ϊʲд������)
		}else
		{
			throw new IllegalArgumentException("�������Ѿ�����һ������");
		}
	}
}
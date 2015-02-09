package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class OODrawingArea  extends OOWidget
{

	public OODrawingArea()
	{
		setId(GTK.gtk_drawing_area_new());
	}

	public void addDrawListener(final OODrawCallback ocbApple) //��������Ҫ����һ���ӿڣ���ͷ���Լ����Ѿ�
	                          //ʵ����OODrawCallback�Ľӿڣ������е��á� �ȵ�OODrawingArea����������ɣ���������
							//draw������������ӿڶ���ʱ������Ҫ����ȥʵ�ֽ�ڶ���ķ��������������з�����
	{
		GTK.g_signal_connect(this.getId(), "draw", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				//�ѻ�������eventData���ȵ���
				OOCairo cr = new OOCairo(eventData);
				//�ṩһ�����Ľӿڣ���ÿ��ʵ�ֶ�����
				//ͨ�����������͵Ļ�����һ�����з����������˽ӿڵ�δʵ�ֵ��࣬��ô����
				//������������ӿ��෽���Ķ���ʱ����Ҫʵ������ӿڵ�δʵ���࣬��Ϊ
				//������Throws�ķ������Ҿ�������������Ķ���ȥʵ����������˭����
				//˭�͸���ȥά�����Ҿ��ṩһ���ؼ������Լ�ȥ��������������ǰ�Ļ�eventData
				//��ʵ����һ���ؼ����Ѿ�ͨ��draw�ӿڴ���
				//��Ȼ��ߵĽӿڿ����������෽���е��������������䷽�������ǲ�δʵ������������
				//�÷������ڻ��ǿշ������ӿڵı��ʾ�����˭���õ�ʱ��ȥʵ����
				//�����ܽ᣺  ��"draw"�źŷ��� ִ����һ��IGCallBack�Ľӿڣ�ͨ���ڲ���ʵ�֣�
				//          ��executeʵ��IGCallback�ӿڵ�execute��������Ҫ�Ƕ�����һ��Cairo����
				//          ����ȥ�˻滭������Ȼ��ͨ���½���һ��OODrawCallback�ṩһ�����ݵĽӿ�
				//          ��������drawCairo��������drawCairo��δ����ķ���������һ���ӿڵ�δʵ��
				//          ������������������һ���������п��ţ�
				// ���Ǳ����ڴ���δʵ�ַ�������ӿڵĶ���ȥʵ������ע���Ƕ����У�
				// 
				// ���룺 һ��class�Ķ��壬������implemetns,��ô���ڲ���һ������  CanWithouImplement = true;
				//       ���һ��calss���壬����implements,��ô���ڵ�  CanWithouImplement = false��
				//      ͨ���������������Ϊʲô����������δʵ��drawCairo�������ǿ��Ե�����drawCairo
				// ������һ�仰����ȷ�ģ���߿϶��������˾���drawCairo��������������ʲô������֪����
				// ���룺 implements ocbApple�Ǳ���ʵ��ocbApple�����з�������ocbApple.drawCairo������Ҫʵ��һ��������
				//     ������Ϊ��һ��ͷ�Ͷ�����final  OODrawCallback ocbApple,�βζ����˽ӿ����������õ�ʱ����Ҫ����
				//           �ӿڶ���һ�����ͨ���ڲ���ʵ��)
				//     ���Է����� ͨ����OODrawCallback����һ��δʵ�ַ��������ֻ�����Ҫʵ������δʵ�ַ���
				//�ٴ�С�᣺ ��߽�����һ����ʽ�ϵĶ��壡�ӿ����βα����г��֣����ڵ��þ��и��βα������ĺ���ʱ�򣬱���
				//         ȥʵ�����ǣ���Ȼ�ڸ÷����Ķ����п���ֱ��ʹ�ø÷������൱�����ڸ���ʽ������������ʽ������
				//         �÷������ȵ����Ҫ�ã����ú�������ʱ�������ȥʵ�ָýӿڣ�ʹ�������ʽ���ã�ocbApple.drawCairo)
				//         �������� (�ؼ��֣� �βΣ�ʵ��   �β��ǽӿڱ�������   ʵ��һ�����ڲ�������ʵ�Σ�
				ocbApple.drawCairo(cr);
			}
		}, null);
	}
}
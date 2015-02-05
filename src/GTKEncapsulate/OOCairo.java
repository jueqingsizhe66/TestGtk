
package GTKEncapsulate;

import com.rupeng.gtk4j.Cairo;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    Ҷ����
 * @time      2015��2��4������11:16:39
 * @version   GTKEncapsulateOOCairo V1.0  ��װ��ͼ��  ��װ��Ŀ��������this.cr�ĳ��֡�
 *                                        ��ͬʱ��������������������Լ��÷������߿ɶ���
 *                                        Ϊ���Ͻ��ԣ��ı����е�this.crΪthis.this.cr
 */
public class OOCairo
{
	
	//Math.PIҲ�ǿ��Ե�
	final static double PI = 3.1415926;
	//��Ϊ������ÿһ��Cairo��ԭʼ��������һ��this.cr��������
	//��Cairo��this.cr�����ǻ���g_signal_conncetion��execute����eventData���Ա��봫�ݸ�CCairo���캯��һ��
	//Eventdata�Ĳ�ȷ��ֵ������Щ��ȷ���Ĳ������Ͱ���������βξͺã��ȵ��õ�ʱ����ȥ�޸�����

	private int cr ;
	
	public OOCairo(int cr)
	{
		this.cr = cr;
	}
	
	/**
	 * 
	 * @param rgb  OORGB�Ķ���  
	 * @purpose    ���û��ʵ���ɫ
	 */
	public void setPenColor(OORGB rgb)
	{
		Cairo.cairo_set_source_rgb(this.cr, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
	}
	/**
	 * 
	 * @param width  ���ʵĿ���
	 * @purpose      ���û��ʵĿ���
	 */
	public void setPenSize(int width)
	{
		Cairo.cairo_set_line_width(this.cr, width);
	}
	
	/**
	 * 
	 * @param fontSize  �������ֵĴ�С
	 */
	public void setFontSize(int fontSize)
	{
		Cairo.cairo_set_font_size(this.cr, fontSize);
	}
	/**
	 * 
	 * @param family      �������  �����塢simsun��΢���źڡ������
	 * @param isItatic    �Ƿ�б��
	 * @param isBold      �Ƿ����
	 * @purpose           �������������
	 */
	public void setFontType(String family,boolean isItatic,boolean isBold)
	{
		Cairo.cairo_select_font_face(this.cr, family, isItatic?Cairo.CAIRO_FONT_SLANT_ITALIC:Cairo.CAIRO_FONT_SLANT_NORMAL,
				isBold?Cairo.CAIRO_FONT_WEIGHT_BOLD:Cairo.CAIRO_FONT_SLANT_NORMAL);
	}
	/**
	 * 
	 * @param family      �������  �����塢simsun��΢���źڡ������
	 */
	public void setFontType(String family)
	{
		Cairo.cairo_select_font_face(this.cr, family,Cairo.CAIRO_FONT_SLANT_ITALIC,Cairo.CAIRO_FONT_WEIGHT_BOLD);
	}
	/**
	 *   ��������   б��  �������������
	 */
	public void setFontType()
	{
		Cairo.cairo_select_font_face(this.cr, "����",Cairo.CAIRO_FONT_SLANT_ITALIC,Cairo.CAIRO_FONT_WEIGHT_BOLD);
	}
	
	/**
	 * 
	 * @param note    utf8���͵����壿��
	 * @purpose       ������ʾ�ַ���
	 */
	public void showText(String note)
	{
		Cairo.cairo_show_text(this.cr, note);
	}
	
	
	/**
	 * 
	 * @param pointX   �Ƶ����ʵ�ĳ���x����
	 * @param pointY   �Ƶ����ʵ�ĳ���y����
	 * @purpose        �ƶ����ʵ�λ�ã�������һ����
	 */
	public void moveTo(int pointX, int pointY)
	{
		Cairo.cairo_move_to(this.cr, pointX, pointY);
	}
	
	//*********************************���ο�ʼ*****************************//
	/**
	 * 
	 * @param sourceX       �ߵ����x����
	 * @param sourceY       �ߵ����x����
	 * @param destinationX  �ߵ��յ�x����
	 * @param destinationY  �ߵ��յ�x����
	 * @purpose             ����һ����
	 */
	public void drawLine(int sourceX,int sourceY, int destinationX, int destinationY)
	{
		//�ƶ���ĳ��
	    moveTo(sourceX,sourceY);
	    //�Ӹõ����һ����
	    Cairo.cairo_line_to(this.cr, destinationX, destinationY);
		Cairo.cairo_stroke(this.cr);
	}
	/**
	 * 
	 * @param circlePointx   Բ�ĵ�x������
	 * @param circlePointy   Բ�ĵ�y������
	 * @param circleRadius   Բ�İ뾶
	 * @purpose              ����һ��λ�ô���Բ
	 */
	public void drawCircle(double circlePointx ,double circlePointy, double circleRadius)
	{
		//Cairo.cairo_arc(this.cr, circlePointx, circlePointy, circleRadius, 0, 2*this.PI);
		drawArcByClockwise(circlePointx,circlePointy,circleRadius,0,2*this.PI); //�����ŷ�װ
		Cairo.cairo_stroke(this.cr);
	}
	
	/**
	 * 
	 * @param circlePointx   Բ�ĵ�x������
	 * @param circlePointy   Բ�ĵ�y������
	 * @param arc_radius      Բ���İ뾶
	 * @param angleApple     ����x��˳ʱ����תangleApple�Ƕȿ�ʼ����Բ��
	 * @param angleBanana    ����x��˳ʱ����ת��angleBanana�ǶȽ���Բ���Ļ���
	 */
	public void drawArcByClockwise(double circlePointx,double circlePointy,double arc_radius,
			double angleApple,double angleBanana)
	{
		Cairo.cairo_arc(this.cr, circlePointx, circlePointy, arc_radius, angleApple, angleBanana);
		Cairo.cairo_stroke(this.cr);
	}
	
	public void drawRectangle(int startPointx, int startPointy,int width,int height)
	{
		Cairo.cairo_rectangle(this.cr, startPointx, startPointy, width, height);
	}
	
	//*****************************�ر����� ****************************************//
	/**
	 *   @purpose   ��ʾCairo��Ϣ�� ������ÿ����һ�λ滭��Ҫ����һ��
	 */
	public void stroke()
	{
		Cairo.cairo_stroke(this.cr);
	}
	/**
	 *   @purpose   ������Stroke�����ã��������
	 */
	public void fill()
	{
		Cairo.cairo_fill(this.cr);
	}
}
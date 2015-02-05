
package GTKEncapsulate;

import com.rupeng.gtk4j.Cairo;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/**
 * @author    叶昭良
 * @time      2015年2月4日上午11:16:39
 * @version   GTKEncapsulateOOCairo V1.0  封装画图类  封装的目的是屏蔽this.cr的出现。
 *                                        并同时减少输入参数的数量，以及让方法更具可读性
 *                                        为了严谨性，改变所有的this.cr为this.this.cr
 */
public class OOCairo
{
	
	//Math.PI也是可以的
	final static double PI = 3.1415926;
	//因为基本上每一个Cairo的原始函数都有一个this.cr环境变量
	//而Cairo的this.cr本质是基于g_signal_conncetion的execute函数eventData所以必须传递给CCairo构造函数一个
	//Eventdata的不确定值！！有些不确定的参数，就把他定义成形参就好，等到用的时候再去修改他。

	private int cr ;
	
	public OOCairo(int cr)
	{
		this.cr = cr;
	}
	
	/**
	 * 
	 * @param rgb  OORGB的对象  
	 * @purpose    设置画笔的颜色
	 */
	public void setPenColor(OORGB rgb)
	{
		Cairo.cairo_set_source_rgb(this.cr, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
	}
	/**
	 * 
	 * @param width  画笔的宽度
	 * @purpose      设置画笔的宽度
	 */
	public void setPenSize(int width)
	{
		Cairo.cairo_set_line_width(this.cr, width);
	}
	
	/**
	 * 
	 * @param fontSize  设置文字的大小
	 */
	public void setFontSize(int fontSize)
	{
		Cairo.cairo_set_font_size(this.cr, fontSize);
	}
	/**
	 * 
	 * @param family      字体家族  ：宋体、simsun、微软雅黑、楷体等
	 * @param isItatic    是否斜体
	 * @param isBold      是否粗体
	 * @purpose           设置字体的类型
	 */
	public void setFontType(String family,boolean isItatic,boolean isBold)
	{
		Cairo.cairo_select_font_face(this.cr, family, isItatic?Cairo.CAIRO_FONT_SLANT_ITALIC:Cairo.CAIRO_FONT_SLANT_NORMAL,
				isBold?Cairo.CAIRO_FONT_WEIGHT_BOLD:Cairo.CAIRO_FONT_SLANT_NORMAL);
	}
	/**
	 * 
	 * @param family      字体家族  ：宋体、simsun、微软雅黑、楷体等
	 */
	public void setFontType(String family)
	{
		Cairo.cairo_select_font_face(this.cr, family,Cairo.CAIRO_FONT_SLANT_ITALIC,Cairo.CAIRO_FONT_WEIGHT_BOLD);
	}
	/**
	 *   设置宋体   斜体  粗体的文字类型
	 */
	public void setFontType()
	{
		Cairo.cairo_select_font_face(this.cr, "宋体",Cairo.CAIRO_FONT_SLANT_ITALIC,Cairo.CAIRO_FONT_WEIGHT_BOLD);
	}
	
	/**
	 * 
	 * @param note    utf8类型的字体？！
	 * @purpose       用于显示字符串
	 */
	public void showText(String note)
	{
		Cairo.cairo_show_text(this.cr, note);
	}
	
	
	/**
	 * 
	 * @param pointX   移到画笔到某点的x坐标
	 * @param pointY   移到画笔到某点的y坐标
	 * @purpose        移动画笔的位置，到另外一个点
	 */
	public void moveTo(int pointX, int pointY)
	{
		Cairo.cairo_move_to(this.cr, pointX, pointY);
	}
	
	//*********************************几何开始*****************************//
	/**
	 * 
	 * @param sourceX       线的起点x坐标
	 * @param sourceY       线的起点x坐标
	 * @param destinationX  线的终点x坐标
	 * @param destinationY  线的终点x坐标
	 * @purpose             绘制一条线
	 */
	public void drawLine(int sourceX,int sourceY, int destinationX, int destinationY)
	{
		//移动到某点
	    moveTo(sourceX,sourceY);
	    //从该点绘制一条线
	    Cairo.cairo_line_to(this.cr, destinationX, destinationY);
		Cairo.cairo_stroke(this.cr);
	}
	/**
	 * 
	 * @param circlePointx   圆心的x轴坐标
	 * @param circlePointy   圆心的y轴坐标
	 * @param circleRadius   圆的半径
	 * @purpose              返回一定位置处的圆
	 */
	public void drawCircle(double circlePointx ,double circlePointy, double circleRadius)
	{
		//Cairo.cairo_arc(this.cr, circlePointx, circlePointy, circleRadius, 0, 2*this.PI);
		drawArcByClockwise(circlePointx,circlePointy,circleRadius,0,2*this.PI); //体现着封装
		Cairo.cairo_stroke(this.cr);
	}
	
	/**
	 * 
	 * @param circlePointx   圆心的x轴坐标
	 * @param circlePointy   圆心的y轴坐标
	 * @param arc_radius      圆弧的半径
	 * @param angleApple     沿着x轴顺时针旋转angleApple角度开始绘制圆弧
	 * @param angleBanana    沿着x轴顺时针旋转到angleBanana角度结束圆弧的绘制
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
	
	//*****************************必备工具 ****************************************//
	/**
	 *   @purpose   显示Cairo信息！ 基本上每进行一次绘画需要调用一次
	 */
	public void stroke()
	{
		Cairo.cairo_stroke(this.cr);
	}
	/**
	 *   @purpose   类似于Stroke的作用，用于填充
	 */
	public void fill()
	{
		Cairo.cairo_fill(this.cr);
	}
}

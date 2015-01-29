/**
 * 
 */

/**
 * @author 
 *
 */
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.Cairo;
public class TestZhaoshuai
{
 static int window;
 public static void main(String[] args)
 {
  //看别人写的程序，和自己去写两码事！
   
  GTK.gtk_init();
  window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
  GTK.gtk_widget_show(window);  
  GTK.g_signal_connect(window, "destroy", new IGCallBack()
  {
   public void execute(int instance, int eventData, Object object)
   {
    GTK.gtk_main_quit();// 彻底关闭程序
   }
  }, null);
   
  int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
  GTK.gtk_widget_show(box);
  GTK.gtk_container_add(window, box);
   
  int drawArea = GTK.gtk_drawing_area_new();
  GTK.gtk_widget_show(drawArea);
  GTK.gtk_widget_set_size_request(drawArea, 500, 500);
  GTK.gtk_box_pack_start(box, drawArea, false, false, 0);
  GTK.g_signal_connect(drawArea, "draw", new IGCallBack()
  {
    
   @Override
   public void execute(int instance, int eventData, Object object)
   {
	    CCairo cr = new CCairo(eventData);
	    cr.setSourceRGB(CairoRGB.RED);
	    cr.moveTo(0, 0);
	    cr.lineTo(100, 100);
	    cr.stroke();
	     
	    cr.setSourceRGB(CairoRGB.BLUE);
	    cr.moveTo(200, 200);
	    cr.circle(200, 200, 50);
	    cr.fill();
	     
	    cr.moveTo(300, 300);
	    //cr.selectFontFace("宋体", false, false);
	    cr.selectFontFace("宋体");
	    cr.setSourceRGB(CairoRGB.BLACK);
	    cr.showText("如鹏网，最牛逼");
	     
	   }
	  }, null);
	   
  GTK.gtk_main();
 }
}

 class CairoRGB//直接定义在类中的是方法和变量  只能在方法体内进行数据操作
{
	private double red;// 0到1之间 比例
	private double green;
	private double blue;

	public double getRed()
	{
		return this.red;
	}

	public double getGreen()
	{
		return this.green;
	}

	public double getBlue()
	{
		return this.blue;
	}

	public CairoRGB(double red, double green, double blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;

	}

	public final static CairoRGB RED = new CairoRGB(1, 0, 0);//fanal修饰的是常量不能重新被定义
	public final static CairoRGB GREEN = new CairoRGB(0, 1, 0);
	public final static CairoRGB BLUE = new CairoRGB(0, 0, 1);
	public final static CairoRGB BLACK = new CairoRGB(0,0,0);  
}


 
 /**
  * @author 帅果果
  * cr是画布编号
  */

 class CCairo
 {
 	private int cr;
 /**
  * 设置画笔颜色
  * @param cr画布编号
  */
 	public CCairo(int cr)
 	{
 	this.cr=cr;
 	}
 	public void setSourceRGB(CairoRGB rgb)
 	{
 		Cairo.cairo_set_source_rgb(this.cr,rgb.getRed(),  rgb.getGreen(), rgb.getBlue());
 	}
 	/**
 	  * 移动画笔到指定坐标
 	  * @param x  移动目标坐标的横坐标
 	  * @param y  移动目标坐标的纵坐标
 	  */
 	public void moveTo(double x,double y)//cr移动坐标  点
 	{
 		Cairo.cairo_move_to(cr, x, x);
 	}
 	public void lineTo(double x,double y)//直线
 	{
 		Cairo.cairo_line_to(cr, x, y);
 	}
 	public void arc(double centerX,double centerY,double radius,
 			double angle1,double angle2)//画弧线  中心坐标 半径，起始弧度，结束弧度
 	{
 		Cairo.cairo_arc(cr, centerX, centerY, radius, angle1, angle2);
 	}
 	public void circle(double centerX,double centerY,double radius)//圆
 	{
 		//arc(centerX,centerY,radius,0,3.141592653*2);
 		arc(centerX,centerY,radius,0,Math.PI*2);
 	}
 	 public void selectFontFace(String family,boolean isItalic,boolean isBold)
 	 {            //选择     字体               // 字体 ， 是否斜体，  是否粗体
 	  Cairo.cairo_select_font_face(this.cr, "宋体", 
 	    isItalic?Cairo.CAIRO_FONT_SLANT_ITALIC:Cairo.CAIRO_FONT_SLANT_NORMAL,
 	      isBold?Cairo.CAIRO_FONT_WEIGHT_BOLD:Cairo.CAIRO_FONT_WEIGHT_NORMAL);
 	 }
 	 public void selectFontFace(String family)
 	 {            //选择     字体               // 字体 ， 是否斜体，  是否粗体
 		 selectFontFace(family, false, false);
 	 }
 	  /**
 	   * 矩形
 	   * @param x
 	   * @param y
 	   * @param width
 	   * @param height
 	   */
 	 public void rectangle(double x, double y,
 	   double width, double height)
 	 {
 	  Cairo.cairo_rectangle(cr, x, y, width, height);
 	 }
 	  
 	 public void stroke()//画
 	 {
 	  Cairo.cairo_stroke(cr);
 	 }
 	  
 	 public void fill()//填充
 	 {
 	  Cairo.cairo_fill(cr);
 	 }
 	  
 	 public void setFontSize(double size)//设置字体大小
 	 {
 	  Cairo.cairo_set_font_size(cr, size);
 	 }
 	 public void showText(String utf8)//文本类型
 	 {
 	  Cairo.cairo_show_text(cr, utf8);
 	 }
 }


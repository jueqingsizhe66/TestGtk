/**
 *  批注！ 这边不是ｅｘｔｅｎｄｓ　而是直接使用enum进行定义了！一直有些困惑现在解决了
 *  
 *  还要注意enum类似于class  在前面可以添加public  进行反问的修饰
 */
package GTKEncapsulate;

/**
 * @author    叶昭良
 * @time      2015年2月9日下午9:28:07
 * @version   GTKEncapsulateOOKeycode V1.0  封装大部分的GTK对于键盘识别的keycode
 */
public enum OOKeycode
{
	//字母
		A("键盘上的a",65),
		B("键盘上的b",66),
		C("键盘上的c",67),
		D("键盘上的d",68),
		E("键盘上的e",69),
		F("键盘上的f",70),
		G("键盘上的g",71),
		H("键盘上的h",72),
		I("键盘上的i",73),
		J("键盘上的j",74),
		K("键盘上的k",75),
		L("键盘上的l",76),
		M("键盘上的m",77),
		N("键盘上的n",78),
		O("键盘上的o",79),
		P("键盘上的p",80),
		Q("键盘上的q",81),
		R("键盘上的r",82),
		S("键盘上的s",83),
		T("键盘上的t",84),
		U("键盘上的u",85),
		V("键盘上的v",86),
		W("键盘上的w",87),
		X("键盘上的x",88),
		Y("键盘上的y",89),
		Z("键盘上的z",90),
	//数字
	    NUM_0("键盘上的0和)",48),
	    NUM_1("键盘上的1和!",49),
	    NUM_2("键盘上的2和@",50),
	    NUM_3("键盘上的3和#",51),
	    NUM_4("键盘上的4和$",52),
	    NUM_5("键盘上的5和%",53),
	    NUM_6("键盘上的6和^",54),
	    NUM_7("键盘上的7和&",55),
	    NUM_8("键盘上的8和*",56),
	    NUM_9("键盘上的9和(",57),
	//比较重要的几个按键
	    SHIFT("键盘上的SHIFT键",16),
	    CTRL("键盘上的ctrl键",17),
	    ALT("键盘上的ALT键",18),
	    CAPS_LOCK("键盘上的大写键",20),
	    ESC("键盘上的ESC键",27),
	    TAB("键盘上的Tab键",9),
	    RIGTH_SHIFT("键盘上右边的SHIFT键",161),
	    RIGHT_CTRL("键盘上右边的ctrl键",163),
	    RIGHT_ALT("键盘上右边的ALT键",165),
	    WINDOWS("键盘上的WINDOWS键",91),
	    FN("键盘上的FN键",17),
	    BACKSPACE("键盘上的baskspace回退键",8),
	    SPACE("键盘上的空格键",32),
	//方向键
	    LEFT("键盘上的左方向键",37),
	    UP("键盘上的上方向键",38),
	    RIGHT("键盘上的右方向键",39),
	    DOWN("键盘上的下方向键",40),

	//小键盘数字 当前情况下必须是在小键盘开启的情况下，否则会匹配错误
	    SMALLBOARD_0("小键盘上的0和insert",96),
	    SMALLBOARD_1("小键盘上的1和end",97),
	    SMALLBOARD_2("小键盘上的2和向下方向键",98),
	    SMALLBOARD_3("小键盘上的3和pgdn",99),
	    SMALLBOARD_4("小键盘上的4和向左方向键",100),
	    SMALLBOARD_5("小键盘上的5",101),
	    SMALLBOARD_6("小键盘上的6和向右方向键",102),
	    SMALLBOARD_7("小键盘上的7和home",103),
	    SMALLBOARD_8("小键盘上的8和向上方向键",104),
	    SMALLBOARD_9("小键盘上的9和pgdn",105),
	//小键盘上的+ - * /
	    SMALLBOARD_PLUS("小键盘上的加号",107),
	    SMALLBOARD_MINUS("小键盘上的减号",109),
	    SMALLBOARD_MULTIPLY("小键盘上的乘号",106),
	    SMALLBOARD_DIVIDE("小键盘上的除号",111),
	//小键盘上的enter和点号（delete),enter和主键盘一样
	    SMALLBOARD_DELETE("小键盘上的点号",110),
	    ENTER("小键盘上的ENTER以及主键盘的ENTER",13),

	//右手边的一些符号键
	    SINGLEQUOTE("键盘上的双引号和单引号",222),
	    SEMICOLON("键盘上的分号和冒号colon键",186),
	    COMMA("键盘上的逗号键和<",188),
	    POINT("键盘盘上的句号和>",190),
	    SLASH("键盘上的/正斜杠表示除法和问号",191),
	    BACKSLASH("键盘上的反斜杠\\和竖线",220),
	    LEFTSQUARE("键盘上的左中括号和左大括号",219),
	    RIGHTSQUARE("键盘上的右中括号和右大括号",221),
	    
	    DASH("键盘上的破折号和减号",189),
	    EQUAL("键盘上的等号和加号",187),
	    REVERSEQUOTE("键盘上的反引号和约等号",192),
	    
	  //一些不经常用的
//	    PRINT("键盘上的PRINT",)
	    PAUSE("键盘上的暂停键和break",19),
	    DELETE("键盘上的delete和insert键",46),
	    HOME("键盘上的HOME键",36),
	    PAGEUP("键盘上的PAGEUP",33),
	    PAGEDOWN("键盘上的PAGEDOWN",34),
	    END("键盘上的END",35),
	    NUMLOCK("键盘上的NUMLOCK",144),
	    
	    //F1--F12
	    F1("键盘上的F1键",112),
	    F2("键盘上的F2键",113),
	    F3("键盘上的F3键",114),
	    F4("键盘上的F4键",115),
	    
	    F5("键盘上的F5键",116),
	    F6("键盘上的F6键",117),
	    F7("键盘上的F7键",118),
	    F8("键盘上的F8键",119),
	    F9("键盘上的F9键",120),
	    F10("键盘上的F10键",121),
	    F11("键盘上的F11键",122),
	    F12("键盘上的F12键",123),
	    ;



	
	//ctrl shift tab caps_lock  , . ; / ' [ ]的
	private int keycode;
	private String keyName;
	private OOKeycode(String keyName,int keycode)
	{
		this.keyName = keyName;
		this.keycode = keycode;
	}
	
	public int getKeycode()
	{
		return this.keycode;
	}
	public String getKeyName()
	{
		return this.keyName;
	}
	
	public static OOKeycode parseOOKeycode(int keycode)
	{
		OOKeycode[] apples = OOKeycode.values();
		for(int i = 0 ; i < apples.length; i++)
		{
			if(keycode == apples[i].getKeycode())
			{
				return apples[i];
			}
		}
		throw new IllegalArgumentException(keycode+"是一个不合法的参数");
	}
}

/**
 * 
 */
package GTKEncapsulate;

/**
 * @author    Ҷ����
 * @time      2015��2��9������9:28:07
 * @version   GTKEncapsulateOOKeycode V1.0  ��װ�󲿷ֵ�GTK���ڼ���ʶ���keycode
 */
public enum OOKeycode
{
	//��ĸ
		A("�����ϵ�a",65),
		B("�����ϵ�b",66),
		C("�����ϵ�c",67),
		D("�����ϵ�d",68),
		E("�����ϵ�e",69),
		F("�����ϵ�f",70),
		G("�����ϵ�g",71),
		H("�����ϵ�h",72),
		I("�����ϵ�i",73),
		J("�����ϵ�j",74),
		K("�����ϵ�k",75),
		L("�����ϵ�l",76),
		M("�����ϵ�m",77),
		N("�����ϵ�n",78),
		O("�����ϵ�o",79),
		P("�����ϵ�p",80),
		Q("�����ϵ�q",81),
		R("�����ϵ�r",82),
		S("�����ϵ�s",83),
		T("�����ϵ�t",84),
		U("�����ϵ�u",85),
		V("�����ϵ�v",86),
		W("�����ϵ�w",87),
		X("�����ϵ�x",88),
		Y("�����ϵ�y",89),
		Z("�����ϵ�z",90),
	//����
	    NUM_0("�����ϵ�0��)",48),
	    NUM_1("�����ϵ�1��!",49),
	    NUM_2("�����ϵ�2��@",50),
	    NUM_3("�����ϵ�3��#",51),
	    NUM_4("�����ϵ�4��$",52),
	    NUM_5("�����ϵ�5��%",53),
	    NUM_6("�����ϵ�6��^",54),
	    NUM_7("�����ϵ�7��&",55),
	    NUM_8("�����ϵ�8��*",56),
	    NUM_9("�����ϵ�9��(",57),
	//�Ƚ���Ҫ�ļ�������
	    SHIFT("�����ϵ�SHIFT��",16),
	    CTRL("�����ϵ�ctrl��",17),
	    ALT("�����ϵ�ALT��",18),
	    CAPS_LOCK("�����ϵĴ�д��",20),
	    ESC("�����ϵ�ESC��",27),
	    TAB("�����ϵ�Tab��",9),
	    RIGTH_SHIFT("�������ұߵ�SHIFT��",161),
	    RIGHT_CTRL("�������ұߵ�ctrl��",163),
	    RIGHT_ALT("�������ұߵ�ALT��",165),
	    WINDOWS("�����ϵ�WINDOWS��",91),
	    FN("�����ϵ�FN��",17),
	    BACKSPACE("�����ϵ�baskspace���˼�",8),
	    SPACE("�����ϵĿո��",32),
	//�����
	    LEFT("�����ϵ������",37),
	    UP("�����ϵ��Ϸ����",38),
	    RIGHT("�����ϵ��ҷ����",39),
	    DOWN("�����ϵ��·����",40),

	//С�������� ��ǰ����±�������С���̿���������£������ƥ�����
	    SMALLBOARD_0("С�����ϵ�0��insert",96),
	    SMALLBOARD_1("С�����ϵ�1��end",97),
	    SMALLBOARD_2("С�����ϵ�2�����·����",98),
	    SMALLBOARD_3("С�����ϵ�3��pgdn",99),
	    SMALLBOARD_4("С�����ϵ�4���������",100),
	    SMALLBOARD_5("С�����ϵ�5",101),
	    SMALLBOARD_6("С�����ϵ�6�����ҷ����",102),
	    SMALLBOARD_7("С�����ϵ�7��home",103),
	    SMALLBOARD_8("С�����ϵ�8�����Ϸ����",104),
	    SMALLBOARD_9("С�����ϵ�9��pgdn",105),
	//С�����ϵ�+ - * /
	    SMALLBOARD_PLUS("С�����ϵļӺ�",107),
	    SMALLBOARD_MINUS("С�����ϵļ���",109),
	    SMALLBOARD_MULTIPLY("С�����ϵĳ˺�",106),
	    SMALLBOARD_DIVIDE("С�����ϵĳ���",111),
	//С�����ϵ�enter�͵�ţ�delete),enter��������һ��
	    SMALLBOARD_DELETE("С�����ϵĵ��",110),
	    ENTER("С�����ϵ�ENTER�Լ������̵�ENTER",13),

	//���ֱߵ�һЩ���ż�
	    SINGLEQUOTE("�����ϵ�˫���ź͵�����",222),
	    SEMICOLON("�����ϵķֺź�ð��colon��",186),
	    COMMA("�����ϵĶ��ż���<",188),
	    POINT("�������ϵľ�ź�>",190),
	    SLASH("�����ϵ�/��б�ܱ�ʾ�������ʺ�",191),
	    BACKSLASH("�����ϵķ�б��\\������",220),
	    LEFTSQUARE("�����ϵ��������ź��������",219),
	    RIGHTSQUARE("�����ϵ��������ź��Ҵ�����",221),
	    
	    DASH("�����ϵ����ۺźͼ���",189),
	    EQUAL("�����ϵĵȺźͼӺ�",187),
	    REVERSEQUOTE("�����ϵķ����ź�Լ�Ⱥ�",192),
	    
	  //һЩ�������õ�
//	    PRINT("�����ϵ�PRINT",)
	    PAUSE("�����ϵ���ͣ����break",19),
	    DELETE("�����ϵ�delete��insert��",46),
	    HOME("�����ϵ�HOME��",36),
	    PAGEUP("�����ϵ�PAGEUP",33),
	    PAGEDOWN("�����ϵ�PAGEDOWN",34),
	    END("�����ϵ�END",35),
	    NUMLOCK("�����ϵ�NUMLOCK",144),
	    
	    //F1--F12
	    F1("�����ϵ�F1��",112),
	    F2("�����ϵ�F2��",113),
	    F3("�����ϵ�F3��",114),
	    F4("�����ϵ�F4��",115),
	    
	    F5("�����ϵ�F5��",116),
	    F6("�����ϵ�F6��",117),
	    F7("�����ϵ�F7��",118),
	    F8("�����ϵ�F8��",119),
	    F9("�����ϵ�F9��",120),
	    F10("�����ϵ�F10��",121),
	    F11("�����ϵ�F11��",122),
	    F12("�����ϵ�F12��",123),
	    ;



	
	//ctrl shift tab caps_lock  , . ; / ' [ ]��
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
		throw new IllegalArgumentException(keycode+"��һ�����Ϸ��Ĳ���");
	}
}
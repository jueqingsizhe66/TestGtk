package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class OODrawingArea  extends OOWidget
{

	public OODrawingArea()
	{
		setId(GTK.gtk_drawing_area_new());
	}

	public void addDrawListener(final OODrawCallback ocbApple) //声明了我要调用一个接口，里头可以假设已经
	                          //实现了OODrawCallback的接口，并进行调用。 等到OODrawingArea这个对象生成，并添加了
							//draw监听器，传入接口对象时候，则需要具体去实现借口对象的方法（并且是所有方法）
	{
		GTK.g_signal_connect(this.getId(), "draw", new IGCallBack()
		{
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				// TODO Auto-generated method stub
				//把画布环境eventData事先导入
				OOCairo cr = new OOCairo(eventData);
				//提供一个画的接口，让每次实现都可以
				//通过现象来解释的话，在一个类中方法，调用了接口的未实现的类，那么当你
				//定义这个包含接口类方法的对象时候，需要实现这个接口的未实现类，因为
				//类似于Throws的方法，我就让你调用这个类的对象去实现它，你们谁调用
				//谁就给我去维护，我就提供一本秘籍，你自己去修炼！！！！当前的话eventData
				//其实就是一本秘籍，已经通过draw接口传入
				//虽然这边的接口可以类似于类方法中的声明，并调用其方法，但是并未实现它！！！！
				//该方法现在还是空方法，接口的本质就是让谁调用的时候去实现它
				//所以总结：  在"draw"信号发出 执行了一个IGCallBack的接口，通过内部类实现，
				//          在execute实现IGCallback接口的execute方法，主要是定义了一个Cairo对象
				//          传进去了绘画环境，然后通过新建了一个OODrawCallback提供一个内容的接口
				//          并调用了drawCairo，而由于drawCairo是未定义的方法，且是一个接口的未实现
				//          方法，所以允许放在一个定义类中空着，
				// 但是必须在存在未实现方法的类接口的对象去实现它（注意是对象中）
				// 
				// 猜想： 一个class的定义，不带有implemetns,那么其内部有一个变量  CanWithouImplement = true;
				//       如果一个calss定义，带有implements,那么其内的  CanWithouImplement = false、
				//      通过这个猜想来解释为什么我们下面在未实现drawCairo方法还是可以调用了drawCairo
				// 但是有一句话是正确的，这边肯定是声明了具有drawCairo的能力。（具体什么能力不知道）
				// 猜想： implements ocbApple是必须实现ocbApple的所有方法，而ocbApple.drawCairo仅仅需要实现一个方法。
				//     错误：因为在一开头就定义了final  OODrawCallback ocbApple,形参定义了接口声明，调用的时候需要传入
				//           接口对象（一般可以通过内部类实现)
				//     测试方法： 通过在OODrawCallback增加一个未实现方法，发现还是需要实现两种未实现方法
				//再次小结： 这边仅仅是一个形式上的定义！接口在形参变量中出现，则在调用具有该形参变量表的函数时候，必须
				//         去实现他们，当然在该方法的定义中可以直接使用该方法。相当于是在该形式方法中事先隐式调用了
				//         该方法，等到真的要用（调用函数）的时候则必须去实现该接口，使得这个隐式调用（ocbApple.drawCairo)
				//         具有意义 (关键字： 形参，实参   形参是接口变量声明   实参一般是内部类生成实参）
				ocbApple.drawCairo(cr);
			}
		}, null);
	}
}

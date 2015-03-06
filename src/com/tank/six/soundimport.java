package com.tank.six;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
//在这边创建了一个播放声音的类   这边是开始声音   如果你想要坦克运行声音 也用同样的办法   放在对应的线程
//说明  如果我把这个类放在构造函数中启动  则当wav文件播放完毕之后  他就退出了  不在播放
	//而我如果假设有一个wav文件  我把它放在子弹线程  或者坦克线程中的run函数   则他会一直产生！  知道坦克或者子弹的线程结束了  我可以试试看



//播放声音的类
class AePlayWave extends Thread {

	
	private String filename;
	public AePlayWave(String wavfile) {
		filename = wavfile;
//从构造函数可以看出只能播放wavfile文件
	}

	public void run() {

		//创造一个声音文件 对象
		File soundFile = new File(filename);
		

		//AudioInputStream 类似于FileInputStream一样
		AudioInputStream audioInputStream = null;
		try {
			//根据这个文件对象转化为 audioInputStream 供我们使用
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		//再利用AudioFormat 格式化掉刚才的stream流的声音文件信息
		AudioFormat format = audioInputStream.getFormat();
		
		//借助SourceDateLine和format   获取DataLine.Info对象
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			//利用AudioSystem.getline再次把info信息转化为  SourceDataLine 
			auline = (SourceDataLine) AudioSystem.getLine(info);
			
			//执行打开就可以了
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		//打开之后 还得按一下播放键
		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			
			//当读完写完之后就把它关闭
			auline.drain();
			auline.close();
		}

	}

	
}

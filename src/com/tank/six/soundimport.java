package com.tank.six;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
//����ߴ�����һ��������������   ����ǿ�ʼ����   �������Ҫ̹���������� Ҳ��ͬ���İ취   ���ڶ�Ӧ���߳�
//˵��  ����Ұ��������ڹ��캯��������  ��wav�ļ��������֮��  �����˳���  ���ڲ���
	//�������������һ��wav�ļ�  �Ұ��������ӵ��߳�  ����̹���߳��е�run����   ������һֱ������  ֪��̹�˻����ӵ����߳̽�����  �ҿ������Կ�



//������������
class AePlayWave extends Thread {

	
	private String filename;
	public AePlayWave(String wavfile) {
		filename = wavfile;
//�ӹ��캯�����Կ���ֻ�ܲ���wavfile�ļ�
	}

	public void run() {

		//����һ�������ļ� ����
		File soundFile = new File(filename);
		

		//AudioInputStream ������FileInputStreamһ��
		AudioInputStream audioInputStream = null;
		try {
			//��������ļ�����ת��Ϊ audioInputStream ������ʹ��
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		//������AudioFormat ��ʽ�����ղŵ�stream���������ļ���Ϣ
		AudioFormat format = audioInputStream.getFormat();
		
		//����SourceDateLine��format   ��ȡDataLine.Info����
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			//����AudioSystem.getline�ٴΰ�info��Ϣת��Ϊ  SourceDataLine 
			auline = (SourceDataLine) AudioSystem.getLine(info);
			
			//ִ�д򿪾Ϳ�����
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		//��֮�� ���ð�һ�²��ż�
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
			
			//������д��֮��Ͱ����ر�
			auline.drain();
			auline.close();
		}

	}

	
}
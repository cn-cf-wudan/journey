package org.journey.generator.util;

import java.io.*;

/** 
 * @ClassName: IOHelper 
 * @ClassNameExplain: 流操作辅助类
 * @Description: 
 * @author wudan-mac
 * @date 16/4/21 上午11:58
 */
public class IOHelper {

	/**
	 * @Title: copy
	 * @TitleExplain:
	 * @Description: 拷贝流内容
	 * @param in 输入流
	 * @param out 输出流
	 * @return void
	 * @version 1.0.0
	 * @author wudan-mac
	 */
	public static void copy(Reader in, Writer out) throws IOException {

		int c = -1;
		while((c = in.read()) != -1) {
			out.write(c);
		}
	}

	/**
	 * @Title: readFile
	 * @TitleExplain:
	 * @Description: 读取文件内容
	 * @param file 文件
	 * @return String 文件内容
	 * @version 1.0.0
	 * @author wudan-mac
	 */
	public static String readFile(File file) throws IOException {
		Reader in = new FileReader(file);
		StringWriter out = new StringWriter();
		copy(in,out);
		return out.toString();
	}

	/**
	 * @Title: saveFile
	 * @TitleExplain:
	 * @Description: 保存内容到文件
	 * @param file 文件
	 * @param content 要写入的内容
	 * @return void
	 * @version 1.0.0
	 * @author wudan-mac
	 */
	public static void saveFile(File file, String content) throws IOException {
		Writer writer = new FileWriter(file);
		writer.write(content);
		writer.close();
	}
	
}

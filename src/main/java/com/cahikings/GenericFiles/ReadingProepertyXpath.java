package com.cahikings.GenericFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadingProepertyXpath {

	static Properties prop=null;
	
public static Properties readxpathdata(String xpathfile) throws IOException {
	prop=new Properties();
FileInputStream fis=new FileInputStream(xpathfile);
prop.load(fis);
return prop;
}
}

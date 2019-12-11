package com.cahikings.GenericFiles;

import java.util.Map;
import java.util.TreeMap;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelTestDataHandler {

	public static Map<String,String> getTestDataInMap(String testdataFilename,String sheetName,String testcaseId) throws Exception
	{
		Map<String, String> testDataInMap=new TreeMap<String,String>();
		String query=null;
		query= String.format("Select * from %S where Run='Y' and TestCaseID=%S",sheetName,testcaseId);
		
		Fillo filo=new Fillo();
		Connection conn =null;
		Recordset recordset=null;
		
		try {
			conn=filo.getConnection(testdataFilename);
			recordset=conn.executeQuery(query);
			
			System.out.println(recordset.getCount()+ " TestCase are there.");
			
			while(recordset.next())
			{
				for(String field:recordset.getFieldNames())
				{
					testDataInMap.put(field, recordset.getField(field));
				}
			}
		}catch (FilloException e)
		{
			e.printStackTrace();
			throw new Exception ("Data not found.....");
		}
		conn.close();
		
		return testDataInMap;
		
	}
}

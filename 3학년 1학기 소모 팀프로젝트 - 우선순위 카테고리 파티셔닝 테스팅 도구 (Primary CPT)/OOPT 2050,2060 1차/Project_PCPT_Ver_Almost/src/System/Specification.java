package System;

import java.util.*;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Specification {
	private ArrayList<Category> categories;
	private HashMap<ArrayList<String>, Integer> testCase = new HashMap<ArrayList<String>, Integer>();
	private Hashtable<String, Property> propertyTable = new Hashtable<String, Property>();

	// Constructor
	public Specification() {
		categories = new ArrayList<Category>();
	}

	// number of categories
	public int getNumberOfCategories() {
		return categories.size();
	}

	// fill TestCase
	
	public int generateTestCase() {
		ArrayList<Property> propertyList = new ArrayList<Property>();
		ArrayList<RepresentativeValue> testCaseList = new ArrayList<RepresentativeValue>();
		f(0, propertyList, testCaseList, testCase, 0);

		//for debugging----------------------------------------	//
		System.out.println("testcase num=" + testCase.size());	//
		System.out.println(testCase);							//
		//-----------------------------------------------------	//
		return 0;
	}
	public int export2excel() {
		// Workbook 
		Workbook xlsWb = new HSSFWorkbook(); // Excel 2007 이전 버전

		// Sheet 
		Sheet sheet1 = xlsWb.createSheet("Test Case");
		Row row = null;
		Cell cell = null;
		// ----------------------------------------------------------

		// row 1
		row = sheet1.createRow(0);
		cell = row.createCell(1);
		cell.setCellValue("Total " + testCase.size() + " test cases generated.");

		// row 2
		row = sheet1.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("No");
		cell = row.createCell(1);
		cell.setCellValue("Test Case");
		cell = row.createCell(2);
		cell.setCellValue("Priority Score");

		// row 3 ~ n
		int maxTestCaseLength = 0;
		int rowNum = 2;
		int testCaseNum = 1;
		// testCase sort and excel export
		Iterator<ArrayList<String>> it = sortByValue(testCase).iterator();
		while (it.hasNext()) {
			ArrayList<String> key = (ArrayList<String>) it.next();
			int score = testCase.get(key);
			row = sheet1.createRow(rowNum++);

			cell = row.createCell(0);
			cell.setCellValue(testCaseNum++);
			cell = row.createCell(1);
			cell.setCellValue(key.toString());
			cell = row.createCell(2);
			cell.setCellValue(score);

			int keyLength = key.toString().length();
			if (keyLength > maxTestCaseLength) {
				maxTestCaseLength = keyLength;
			}
		}

		// set column width
		sheet1.setColumnWidth(1, maxTestCaseLength * 210);

		// save excel file
		try {
			File xlsFile = new File("testExcel.xls");
			FileOutputStream fileOut = new FileOutputStream(xlsFile);
			xlsWb.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Workbook close
		try {
			xlsWb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public Category getCategory(int categoryIndex) {
		return categories.get(categoryIndex);
	}

	public int setCategory(int categoryIndex, String categoryName) {
		categories.get(categoryIndex).setCategoryName(categoryName);
		return 0;
	}

	public int addCategory(String categoryName) {
		categories.add(new Category(categoryName, propertyTable));
		return 0;
	}	

	//수정시작
	public int delCategory(int categoryIndex) {
		
		for(int i = 0; i < this.categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++)
		{
			this.categories.get(categoryIndex).delRepresentativeValue(i);	//여기서 알아서 property 일관성 유지 해주기 때문에 property쪽에 문제 없음.
		}
		categories.remove(categoryIndex);
		return 0;
	}
	
	public void updateData(){
		for(int i=0;i<categories.size();i++){
			for(int j=0;j<categories.get(i).getNumberOfRepresentativeValues();j++){
				for(int k=0;k<categories.get(i).getRepresentativeValue(j).getNumberOfIfPropertyConstraints();k++){
					if(propertyTable.get(categories.get(i).getRepresentativeValue(j).getIfProperty(k).getName())==null){
						categories.get(i).getRepresentativeValue(j).delIfProperty(k);
					}
				}
			}
		}
	}
	
	private void updatePropertyList(ArrayList<Property> propertyList, int categoryIndex, int rvIndex) {
		for (int p = 0; p < categories.get(categoryIndex).getNumberOfRepresentativeValues(); p++) {
			for (int q = 0; q < categories.get(categoryIndex).getRepresentativeValue(p)
					.getNumberOfPropertyConstraints(); q++) {
				if (propertyList.contains(categories.get(categoryIndex).getRepresentativeValue(p).getProperty(q))) {
					for (int m = 0; m < propertyList.size(); m++) {
						if (propertyList.get(m)
								.equals(categories.get(categoryIndex).getRepresentativeValue(p).getProperty(q))) {
							propertyList.remove(m);
						}
					}
				}
			}
		}
		for (int j = 0; j < categories.get(categoryIndex).getRepresentativeValue(rvIndex)
				.getNumberOfPropertyConstraints(); j++) {
			propertyList.add(categories.get(categoryIndex).getRepresentativeValue(rvIndex).getProperty(j));
		}
	}

	
	private ArrayList<String> refreshTestCaseList(ArrayList<RepresentativeValue> testCaseList, int categoryIndex,
			int rvIndex) {
		while (testCaseList.size() > categoryIndex) {
			testCaseList.remove(testCaseList.size() - 1);
		}
		testCaseList.add(categories.get(categoryIndex).getRepresentativeValue(rvIndex));

		ArrayList<String> tempTestCase = new ArrayList<String>();
		for (int j = 0; j < testCaseList.size(); j++) {
			tempTestCase.add(testCaseList.get(j).getRepresentativeValueName());
		}
		return tempTestCase;
	}
	
	private int prioritySum(ArrayList<RepresentativeValue> testCaseList) {
		int prioritySum = 0;
		for (int j = 0; j < testCaseList.size(); j++) {
			prioritySum += testCaseList.get(j).getPriorityRank() << testCaseList.get(j).getPriorityRank();
		}
		return prioritySum;
	}

	
	private boolean ifConstraintsExistInPropertyList(ArrayList<Property> propertyList, int categoryIndex, int rvIndex) {
		boolean ifConstraintCheck = true;
		for (int j = 0; j < categories.get(categoryIndex).getRepresentativeValue(rvIndex)
				.getNumberOfIfPropertyConstraints(); rvIndex++) {
			if (!propertyList
					.contains(categories.get(categoryIndex).getRepresentativeValue(rvIndex).getIfProperty(j))) {
				ifConstraintCheck = false;
				break;
			}
		}
		return ifConstraintCheck;
	}
	
	private void f(int categoryIndex, ArrayList<Property> propertyList, ArrayList<RepresentativeValue> testCaseList,
			HashMap<ArrayList<String>, Integer> testCase, int prioritySum) {
		// IF last category
		if (categoryIndex == categories.size() - 1) {
			// FOR each RepresentativeValues in Category
			for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
				// IF representativeValue has if-constraints
				if (categories.get(categoryIndex).getRepresentativeValue(i).getNumberOfIfPropertyConstraints() != 0) {
					// IF if-constraints exist in propertyList
					if (ifConstraintsExistInPropertyList(propertyList, categoryIndex, i)) {
						// update property list
						updatePropertyList(propertyList, categoryIndex, i);
						// refresh testCaseList
						ArrayList<String> tempTestCase = refreshTestCaseList(testCaseList, categoryIndex, i);
						// prioritySum calculation
						// IF representative value is single/error
						if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() != 0) {
							prioritySum = categories.get(categoryIndex).getRepresentativeValue(i).getPriorityRank()
									+ (5 << 5) * categories.size();
						}
						// IF representative value is not single/error
						else {
							prioritySum = prioritySum(testCaseList);
						}
						// testCase put
						testCase.put(tempTestCase, prioritySum);
					}
				}
				// IF representativeValue has no if-constraints
				else {
					ArrayList<String> tempTestCase;
					// update property list
					updatePropertyList(propertyList, categoryIndex, i);
					// IF representative value is single/error
					if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() != 0) {
						// refresh testCaseList
						tempTestCase = new ArrayList<String>();
						tempTestCase.add(
								categories.get(categoryIndex).getRepresentativeValue(i).getRepresentativeValueName());
						// prioritySum calculation
						prioritySum = categories.get(categoryIndex).getRepresentativeValue(i).getPriorityRank()
								+ (5 << 5) * categories.size();
					}
					// IF representative value is not single/error
					else {
						// refresh testCaseList
						tempTestCase = refreshTestCaseList(testCaseList, categoryIndex, i);
						// prioritySum calculation
						prioritySum = prioritySum(testCaseList);
					}
					// testCase put
					testCase.put(tempTestCase, prioritySum);
				}
			}
		}
		// IF not last category
		else {
			// FOR each RepresentativeValues in Category
			for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
				// IF representativeValue has if-constraints
				if (categories.get(categoryIndex).getRepresentativeValue(i).getNumberOfIfPropertyConstraints() != 0) {
					// IF if-constraints exist in propertyList
					if (ifConstraintsExistInPropertyList(propertyList, categoryIndex, i)) {
						// update property list
						updatePropertyList(propertyList, categoryIndex, i);
						// refresh testCaseList
						ArrayList<String> tempTestCase = refreshTestCaseList(testCaseList, categoryIndex, i);
						// IF single / error
						if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() != 0) {
							// prioritySum calculation
							prioritySum = categories.get(categoryIndex).getRepresentativeValue(i).getPriorityRank()
									+ (5 << 5) * categories.size();
							// testCase put
							testCase.put(tempTestCase, prioritySum);
						}
						// If not single / error
						else {
							// recursive f()
							f(categoryIndex + 1, propertyList, testCaseList, testCase, prioritySum);
						}
					}
				}
				// IF representativeValue has no if-constraints
				else {
					// update property list
					updatePropertyList(propertyList, categoryIndex, i);

					// IF single / error
					if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() != 0) {
						// refresh testCaseList
						ArrayList<String> tempTestCase = new ArrayList<String>();
						tempTestCase.add(
								categories.get(categoryIndex).getRepresentativeValue(i).getRepresentativeValueName());
						// prioritySum calculation
						prioritySum = categories.get(categoryIndex).getRepresentativeValue(i).getPriorityRank()
								+ (5 << 5) * categories.size();
						// testCase put
						testCase.put(tempTestCase, prioritySum);
					}
					// If not single / error
					else {
						// refresh testCaseList
						refreshTestCaseList(testCaseList, categoryIndex, i);
						// recursive f()
						f(categoryIndex + 1, propertyList, testCaseList, testCase, prioritySum);
					}
				}

			}
		}
	}

	private List<ArrayList<String>> sortByValue(HashMap<ArrayList<String>, Integer> testCase) {
		List<ArrayList<String>> testCaseList = new ArrayList<ArrayList<String>>();
		testCaseList.addAll(testCase.keySet());
		Collections.sort(testCaseList, new Comparator<ArrayList<String>>() {
			public int compare(ArrayList<String> o1, ArrayList<String> o2) {
				int v1 = testCase.get(o1);
				int v2 = testCase.get(o2);
				return ((Comparable<Integer>) v2).compareTo(v1);
			}
		});
		return testCaseList;
	}
}

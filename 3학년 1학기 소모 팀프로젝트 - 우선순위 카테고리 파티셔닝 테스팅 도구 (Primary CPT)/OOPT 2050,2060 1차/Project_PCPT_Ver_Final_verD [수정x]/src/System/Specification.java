package System;

import java.util.*;

import javax.swing.JOptionPane;

import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Specification {
	private ArrayList<Category> categories;
	private HashMap<ArrayList<String>, Integer> testCase;
	private Hashtable<String, Property> propertyTable = new Hashtable<String, Property>();

	// Constructor
	public Specification() {
		categories = new ArrayList<Category>();
	}

	// number of categories
	public int getNumberOfCategories() {
		return categories.size();
	}

	public int generateTestCase() {
		boolean isExist = false;
		for (int i = 0; i < getNumberOfCategories(); i++) {
			if (getCategory(i).getNumberOfRepresentativeValues() != 0) {
				isExist = true;
				break;
			}
		}
		if (this.getNumberOfCategories() != 0 && isExist) {
			testCase = new HashMap<ArrayList<String>, Integer>();

			// single error extract
			for (int i = 0; i < categories.size(); i++) {
				for (int j = 0; j < categories.get(i).getNumberOfRepresentativeValues(); j++) {
					if (categories.get(i).getRepresentativeValue(j).getSingleError() != 0) {
						String s = categories.get(i).getRepresentativeValue(j).getRepresentativeValueName();
						for (int k = 0; k < categories.get(i).getRepresentativeValue(j)
								.getNumberOfIfPropertyConstraints(); k++) {
							s += " (if " + categories.get(i).getRepresentativeValue(j).getIfProperty(k).getName() + ")";
						}

						int prioritySum = categories.get(i).getRepresentativeValue(j).getPriorityRank()
								+ (5 << 5) * categories.size();
						ArrayList<String> testCaseString = new ArrayList<String>();
						testCaseString.add(i + "|" + s);
						testCase.put(testCaseString, prioritySum);
					}
				}
			}

			ArrayList<Property> propertyList = new ArrayList<Property>();
			ArrayList<RepresentativeValue> testCaseList = new ArrayList<RepresentativeValue>();
			ArrayList<Integer> categoryList = new ArrayList<Integer>();
			f(0, propertyList, testCaseList, categoryList, testCase, 0);

			/*
			 * //for debugging---------------------------------------- //
			 * System.out.println("testcase num=" + testCase.size()); //
			 * System.out.println(testCase); //
			 * //----------------------------------------------------- //
			 */
			return testCase.size();
		} else {
			return -1;
		}
	}

	public int export2excel(String fileName) {
		// Workbook
		Workbook xlsWb = new HSSFWorkbook(); //

		// Sheet
		Sheet sheet1 = xlsWb.createSheet("Test Case");
		Row row = null;
		Cell cell = null;
		// ----------------------------------------------------------

		// row 1
		row = sheet1.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Test Case");
		cell = row.createCell(1);
		cell.setCellValue("Total " + testCase.size() + " test cases generated.");

		// row 2
		row = sheet1.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("No");
		int i;
		for (i = 0; i < categories.size(); i++) {
			cell = row.createCell(i + 1);
			cell.setCellValue(categories.get(i).getCategoryName());
		}

		cell = row.createCell(i + 1);
		cell.setCellValue("Priority Score");

		// row 3 ~ n
		int rowNum = 2;
		int testCaseNum = 1;
		int j;
		// testCase sort and excel export
		Iterator<ArrayList<String>> it = sortByValue(testCase).iterator();
		StringTokenizer st;
		while (it.hasNext()) {
			ArrayList<String> key = (ArrayList<String>) it.next();
			int score = testCase.get(key);
			row = sheet1.createRow(rowNum++);

			cell = row.createCell(0);
			cell.setCellValue(testCaseNum++);

			for (j = 0; j < key.size(); j++) {
				st = new StringTokenizer(key.get(j), "|");
				cell = row.createCell(Integer.parseInt(st.nextToken()) + 1);
				cell.setCellValue(st.nextToken());
			}
			cell = row.createCell(categories.size() + 1);
			cell.setCellValue(score);
		}

		// save excel file
		try {
			File xlsFile = new File(fileName);
			FileOutputStream fileOut = new FileOutputStream(xlsFile);
			xlsWb.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Workbook close
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
		for (int i = 0; i < getNumberOfCategories(); i++) {
			if (categories.get(i).getCategoryName().compareTo(categoryName) == 0) {
				JOptionPane.showMessageDialog(null, "이미 같은 이름의 카테고리가 존재합니다!!");
				return 1;
			}
		}
		categories.get(categoryIndex).setCategoryName(categoryName);
		return 0;
	}

	public int addCategory(String categoryName) {
		for (int i = 0; i < getNumberOfCategories(); i++) {
			if (categories.get(i).getCategoryName().compareTo(categoryName) == 0) {
				JOptionPane.showMessageDialog(null, "이미 같은 이름의 카테고리가 존재합니다!!");
				return 1;
			}
		}
		categories.add(new Category(categoryName, propertyTable));
		return 0;
	}

	// 수정시작
	public int delCategory(int categoryIndex) {

		for (int i = 0; i < this.categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
			this.categories.get(categoryIndex).delRepresentativeValue(i); // 여기서
																			// 알아서
																			// property
																			// 일관성
																			// 유지
																			// 해주기
																			// 때문에
																			// property쪽에
																			// 문제
																			// 없음.
		}
		categories.remove(categoryIndex);
		return 0;
	}

	public void updateData() {
		for (int i = 0; i < categories.size(); i++) {
			for (int j = 0; j < categories.get(i).getNumberOfRepresentativeValues(); j++) {
				for (int k = 0; k < categories.get(i).getRepresentativeValue(j)
						.getNumberOfIfPropertyConstraints(); k++) {
					if (propertyTable
							.get(categories.get(i).getRepresentativeValue(j).getIfProperty(k).getName()) == null) {
						categories.get(i).getRepresentativeValue(j).delIfProperty(k);
					}
				}
			}
		}
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
				.getNumberOfIfPropertyConstraints(); j++) {
			if (!propertyList
					.contains(categories.get(categoryIndex).getRepresentativeValue(rvIndex).getIfProperty(j))) {
				ifConstraintCheck = false;
				break;
			}
		}
		return ifConstraintCheck;
	}

	private boolean skipCategory(int categoryIndex, ArrayList<Property> propertyList) {
		boolean result = true;
		int singleErrorNum = 0;

		// 조건0 || (조건1&&조건2)
		// skip의 조건 0. 모든 Representative value가 single 또는 error다.
		for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
			if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() != 0) {
				singleErrorNum++;
			}
		}
		if (singleErrorNum > 0 && singleErrorNum == categories.get(categoryIndex).getNumberOfRepresentativeValues()) {
			return true;
		}

		// skip의 조건 1. 모든 Representative value가 if constraint를 가지고 있다.
		for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
			if (categories.get(categoryIndex).getRepresentativeValue(i).getNumberOfIfPropertyConstraints() == 0) {
				return false;
			}
		}
		// skip의 조건 2. 모든 Representative value의 if조건이 만족되지 않는다.
		for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
			if (ifConstraintsExistInPropertyList(propertyList, categoryIndex, i)) {
				return false;
			}
		}

		return result;
	}

	private ArrayList<RepresentativeValue> copyTestCaseList(ArrayList<RepresentativeValue> testCaseList) {
		ArrayList<RepresentativeValue> newTestCaseList = new ArrayList<RepresentativeValue>();
		for (int i = 0; i < testCaseList.size(); i++) {
			newTestCaseList.add(testCaseList.get(i));
		}
		return newTestCaseList;
	}

	private ArrayList<Property> copyPropertyList(ArrayList<Property> propertyList) {
		ArrayList<Property> newPropertyList = new ArrayList<Property>();
		for (int i = 0; i < propertyList.size(); i++) {
			newPropertyList.add(propertyList.get(i));
		}
		return newPropertyList;
	}

	private ArrayList<String> testCaseListToString(ArrayList<RepresentativeValue> testCaseList,
			ArrayList<Integer> categoryList) {
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < testCaseList.size(); i++) {
			s.add(categoryList.get(i) + "|" + testCaseList.get(i).getRepresentativeValueName());
		}
		return s;
	}

	private void f(int categoryIndex, ArrayList<Property> propertyList, ArrayList<RepresentativeValue> testCaseList,
			ArrayList<Integer> categoryList, HashMap<ArrayList<String>, Integer> testCase, int prioritySum) {
		// IF skip category
		if (skipCategory(categoryIndex, propertyList)) {
			// IF last category
			if (categoryIndex == categories.size() - 1) {

				if (testCaseList.size() != 0) {
					// put test case
					testCase.put(testCaseListToString(testCaseList, categoryList), prioritySum);
				}
			}
			// IF not last category
			else {
				f(categoryIndex + 1, propertyList, testCaseList, categoryList, testCase, prioritySum);
			}
		}
		// If not skip category
		else {

			for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
				// single error 가 아니면! (만약 single error이면 그냥 die)
				if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() == 0) {
					// if constraint 확인
					if (ifConstraintsExistInPropertyList(propertyList, categoryIndex, i)) {
						// propertyList 갱신
						ArrayList<Property> newPropertyList = copyPropertyList(propertyList);
						for (int j = 0; j < categories.get(categoryIndex).getRepresentativeValue(i)
								.getNumberOfPropertyConstraints(); j++) {
							newPropertyList.add(categories.get(categoryIndex).getRepresentativeValue(i).getProperty(j));
						}
						// testCaseList 갱신
						ArrayList<RepresentativeValue> newTestCaseList = copyTestCaseList(testCaseList);
						newTestCaseList.add(categories.get(categoryIndex).getRepresentativeValue(i));
						// prioritySum 갱신
						prioritySum = prioritySum(newTestCaseList);
						// categoryList 갱신
						ArrayList<Integer> newCategoryList = new ArrayList<Integer>();
						for (int j = 0; j < categoryList.size(); j++) {
							newCategoryList.add(categoryList.get(j));
						}
						newCategoryList.add(categoryIndex);

						// IF last category
						if (categoryIndex == categories.size() - 1) {
							// put test case
							testCase.put(testCaseListToString(newTestCaseList, newCategoryList), prioritySum);
						}
						// IF not last category
						else {
							f(categoryIndex + 1, newPropertyList, newTestCaseList, newCategoryList, testCase,
									prioritySum);
						}
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
import java.util.ArrayList;

public class Specification {
	private ArrayList<Category> categories;
	private String[][] testCase;

	// Constructor
	public Specification() {
		categories = new ArrayList<Category>();
	}

	// number of categories
	public int getNumberOfCategories() {
		return categories.size();
	}

	// fill TestCase
	private void f(int categoryIndex, ArrayList<Property> propertyList, ArrayList<String> testCaseList,
			String[][] testCase, int prioritySum) {
		// 마지막 카테고리일 경우
		if (categoryIndex == categories.size() - 1) {
			for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
				// single error 확인
				if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() != null) {

				}
				// if property 확인
				else if (categories.get(categoryIndex).getRepresentativeValue(i).getNumberOfIfPropertyConstraints() !=0) {

				}
				// single error if 없을 경우
				else {
					// prioritySum += categories.get(categoryIndex).getRepresentativeValue(i).getPriorityRank();
					
					if (categories.get(categoryIndex).getRepresentativeValue(i).getNumberOfPropertyConstraints() != 0) {
						propertyList.add(categories.get(categoryIndex).getRepresentativeValue(i).getProperty(0));
					}
					
					if(testCaseList.size() > categoryIndex){
						testCaseList.set(categoryIndex, categories.get(categoryIndex).getRepresentativeValue(i).getRepresentativeValueName());
					}
					else{
						testCaseList.add(categories.get(categoryIndex).getRepresentativeValue(i).getRepresentativeValueName());
					}
					
					

					System.out.println(testCaseList);

					// 추가로 필요한것은 testcase에 넣기
				}
			}
		}
		// 마지막 카테고리가 아닐경우
		else {
			for (int i = 0; i < categories.get(categoryIndex).getNumberOfRepresentativeValues(); i++) {
				// single error 확인
				if (categories.get(categoryIndex).getRepresentativeValue(i).getSingleError() != null) {
					
				}
				// if property 확인
				else if (categories.get(categoryIndex).getRepresentativeValue(i).getNumberOfIfPropertyConstraints() != 0) {
				
				}
				// single error if 없을 경우
				else {
					// prioritySum += categories.get(categoryIndex).getRepresentativeValue(i).getPriorityRank();
					if (categories.get(categoryIndex).getRepresentativeValue(i).getNumberOfPropertyConstraints() != 0) {
						propertyList.add(categories.get(categoryIndex).getRepresentativeValue(i).getProperty(0));
					}
					
					if(testCaseList.size() == categoryIndex+1){
						testCaseList.remove(testCaseList.size()-1);
					}
					
					//반복되는 대표값 제어
					if(testCaseList.size() > categoryIndex){
						testCaseList.set(categoryIndex, categories.get(categoryIndex).getRepresentativeValue(i).getRepresentativeValueName());
					}
					else{
						testCaseList.add(categories.get(categoryIndex).getRepresentativeValue(i).getRepresentativeValueName());
					}
					
					
					f(categoryIndex + 1, propertyList, testCaseList, testCase, prioritySum);
				}
			}
		}
	}

	public int generateTestCase() {
		int testCaseNumber = 1;
		int singleError = 0;

		for (int i = 0; i < categories.size(); i++) {
			int valueNum = categories.get(i).getNumberOfRepresentativeValues();
			int withoutSingleError = valueNum;
			for (int j = 0; j < valueNum; j++) {
				if (categories.get(i).getRepresentativeValue(j).getSingleError() != null) {
					withoutSingleError--;
					singleError++;
				}
			}
			testCaseNumber *= withoutSingleError;
		}
		testCaseNumber += singleError;
		testCase = new String[testCaseNumber][categories.size()];

		System.out.println("testcase num=" + testCaseNumber); // debug

		// fill testCase
		ArrayList<Property> propertyList = new ArrayList<Property>();
		ArrayList<String> testCaseList = new ArrayList<String>();

		f(0, propertyList, testCaseList, testCase, 0);

		// showTestCase
		// for(int i=0; i<testCase.length; i++){
		// for(int j=0; j<testCase[0].length; j++){
		// System.out.print(testCase[i][j]+" ");
		// }
		// System.out.println();
		// }

		return 0;
	}

	public int export2excel() {
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
		categories.add(new Category(categoryName));
		return 0;
	}

	public int delCategory(int categoryIndex) {
		categories.remove(categoryIndex);
		return 0;
	}
}

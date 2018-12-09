package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import System.Specification;

public class SpecificationTest {
	
	@Test //정상적으로 갯수 반환하는지
	public void testGetNumberOfCategories() {
		Specification s = new Specification();
		s.addCategory("a");
		s.addCategory("b");
		s.addCategory("c");
		
		assertEquals(3,s.getNumberOfCategories());
	}

	@Test //테스트 케이스 생성이 정확하게 되는지.
	public void testGenerateTestCase() {
		Specification s = new Specification();
		s.addCategory("a");
		s.addCategory("b");
		s.getCategory(0).addRepresentativeValue("a1");
		s.getCategory(0).addRepresentativeValue("a2");
		s.getCategory(0).addRepresentativeValue("a3");
		s.getCategory(0).getRepresentativeValue(0).addProperty("a1");
		s.getCategory(1).addRepresentativeValue("b1");
		s.getCategory(1).addRepresentativeValue("b2");
		s.getCategory(1).addRepresentativeValue("b3");
		s.getCategory(1).addRepresentativeValue("b4");
		s.getCategory(1).getRepresentativeValue(1).addIfProperty("a1");
		s.getCategory(1).getRepresentativeValue(2).setSingleError(1);
		
		assertEquals(8,s.generateTestCase());
	}

	@Test //엑셀로 정확히 쏴지는지
	public void testExport2excel() {
		Specification s = new Specification();
		s.addCategory("a");
		s.addCategory("b");
		s.getCategory(0).addRepresentativeValue("a1");
		s.getCategory(0).addRepresentativeValue("a2");
		s.getCategory(0).addRepresentativeValue("a3");
		s.getCategory(0).getRepresentativeValue(0).addProperty("a1");
		s.getCategory(1).addRepresentativeValue("b1");
		s.getCategory(1).addRepresentativeValue("b2");
		s.getCategory(1).addRepresentativeValue("b3");
		s.getCategory(1).addRepresentativeValue("b4");
		s.getCategory(1).getRepresentativeValue(1).addIfProperty("a1");
		s.getCategory(1).getRepresentativeValue(2).setSingleError(1);
		s.generateTestCase();
		assertEquals(0,s.export2excel("dd.xls"));
		File f = new File("dd.xls");
		assertTrue(f.exists());
	}

	@Test
	public void testSetCategory() {
		Specification s = new Specification();
		s.addCategory("a");
		s.addCategory("b");
		s.addCategory("c");

		assertEquals(0,s.setCategory(2,"d"));
		assertEquals(1,s.setCategory(2,"b"));
	}

	@Test
	public void testAddCategory() {
		Specification s = new Specification();
		s.addCategory("a");
		s.addCategory("b");
		s.addCategory("c");

		assertEquals(0,s.addCategory("d"));
		assertEquals(1,s.addCategory("a"));
		
	}

}

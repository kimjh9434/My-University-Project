package test;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.BeforeClass;
import org.junit.Test;

import System.Category;
import System.Property;
import System.RepresentativeValue;

public class CategoryTest 
{
	static Hashtable<String, Property> pt;
	static Category c1, c2;
	static RepresentativeValue r1, r2;
	@BeforeClass
	public static void beforeClass()
	{
		pt = new Hashtable<String, Property>();
		c1 = new Category("c1", pt);
		c2 = new Category("c2", pt);
		r1 = new RepresentativeValue("r1", pt);
		r2 = new RepresentativeValue("r2", pt);
	}
	
	@Test
	public void testDelRepresentativeValue() 
	{
		c1.addRepresentativeValue("r1");
		c1.addRepresentativeValue("r2");
		
		assertEquals(0, c1.delRepresentativeValue(0));
		assertEquals(0, c1.delRepresentativeValue(0));
	}
	
	@Test
	public void testSetRepresentativeValue() 
	{
		c1.addRepresentativeValue("r1");
		c1.addRepresentativeValue("r2");
		
		assertEquals(0, c1.setRepresentativeValue(1, "1"));
		assertEquals(1, c1.setRepresentativeValue(0, "1"));
	}

	@Test
	public void testAddRepresentativeValue() 
	{
		assertEquals(0, c1.addRepresentativeValue("r2"));
		assertEquals(1, c1.addRepresentativeValue("1"));
	}

}

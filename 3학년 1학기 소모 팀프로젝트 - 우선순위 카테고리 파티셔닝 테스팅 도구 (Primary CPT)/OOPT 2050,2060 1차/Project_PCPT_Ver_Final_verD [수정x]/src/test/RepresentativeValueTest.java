package test;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;

import System.Property;
import System.RepresentativeValue;

import org.junit.BeforeClass;
import org.junit.Before;

public class RepresentativeValueTest 
{
	static RepresentativeValue rv, rv2, rv3, rv4;
	static Hashtable<String, Property> ht, ht2;
	@BeforeClass
	public static void BeforeClass()
	{
		ht = new Hashtable<String, Property>();
		ht2 = new Hashtable<String, Property>();
		rv = new RepresentativeValue("test", ht);
		rv2 = new RepresentativeValue("test1", ht);
		rv3 = new RepresentativeValue("test2", ht2);
		rv4 = new RepresentativeValue("test2", ht2);
	}

	@Before
	public void Before()
	{
		
	}

	@Test
	public void testSetProperty() 
	{
		//1 : 이름중복 2 : 하나만 변경 3 : 전부 변경 4 : 자기만 가진 경우
		rv.addProperty("test");
		rv.addProperty("test1");
		
		assertEquals(1, rv.setProperty(0, "test1"));
		
		assertEquals(0, rv.setProperty(0, "test2"));
		
		rv2.addProperty("test2");
		
		assertEquals(0, rv.setProperty(0, "test"));
		assertEquals(0, rv2.setProperty(0, "test1"));
	}

	@Test
	public void testAddProperty() 
	{
		assertEquals(0, rv.addProperty("1"));
		assertEquals(1, rv.addProperty("1"));
	}

	@Test
	public void testDelProperty()
	{
		rv.addProperty("1");
		rv.addProperty("2");
		rv2.addProperty("1");
		assertEquals(0, rv.delProperty(0));
		assertEquals(1, rv.delProperty(0));
	}

	@Test
	public void testSetIfProperty()
	{
		assertEquals(0, rv3.setIfProperty(0, "if"));
		
		assertEquals(1, rv3.setIfProperty(0, "err"));
	}

	@Test
	public void testAddIfProperty()
	{
		rv4.addProperty("if1");
		rv4.addProperty("if");
		assertEquals(0, rv3.addIfProperty("if1"));
		
		assertEquals(1, rv3.addIfProperty("if1"));
		
		assertEquals(2, rv3.addIfProperty("is"));
	}
}

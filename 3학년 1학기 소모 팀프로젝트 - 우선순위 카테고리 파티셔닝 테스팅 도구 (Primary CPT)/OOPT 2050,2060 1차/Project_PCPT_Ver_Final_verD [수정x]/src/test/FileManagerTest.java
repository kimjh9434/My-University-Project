package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import System.FileManager;
import System.Specification;

public class FileManagerTest {
	private static Specification s;
	private static FileManager f;
	private static File file;
	
	@BeforeClass
	public static void beforeSetup(){
		s = new Specification();
		f = new FileManager(s);
	}

	@Test //���� �� �Ǵ��� �׽�Ʈ
	public void testNewSpecification1() {
		file = new File("newTest.yzb");
		if(file.exists()){
			file.delete();
		}
		assertEquals(0,f.newSpecification("newTest"));
		assertTrue(file.exists());
	}
	
	@Test //test1���� ������ ���ϸ�� �����ϱ� ����� â�� �� ���̴�. ���� ������ 0, �ƴϿ��� ������ 1�� ��ȯ�Ǿ�� �Ѵ�.
	public void testNewSpecification2(){
		assertEquals(0,f.newSpecification(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4)));
		assertEquals(1,f.newSpecification(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4)));
	}
	
	@Test //�����ϴ� ������ �� �ҷ��;ߵȴ�. �׷��� 0�� ����.
	public void testLoadSpecification1() {
		file = new File("test.yzb");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0,f.loadSpecification(file.getAbsolutePath()));
	}
	
	@Test //���Ŀ� ���� �ʴ� ������ �ҷ��� ��, Ȯ���� ���� �ʴٰ� ���â �߸� 1�� ����
	public void testLoadSpecification2(){
		file = new File("err.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1,f.loadSpecification(file.getAbsolutePath()));
	}

	@Test //���� �� �Ǵ���
	public void testSaveSpecification() {
		assertEquals(0,f.saveSpecification());
	}

	@Test //�ٸ� �̸����� ������ ������ �� �������� ��� 0�� �����ϴ���
	public void testSaveSpecificationString1(){
		assertEquals(0,f.saveSpecification("yes.yzb"));
	}
	
	@Test //�ٸ� �̸����� ������ ������ ��  Ȯ���� �̻��Ѱſ� �����ϸ� ��� �߸� 1 �����ϴ���
	public void testSaveSpecificationString2() {
		assertEquals(1,f.saveSpecification("yes.txt"));
	}

	@Test //�ֱ� ���� ��� �� ������ �������� �ʴ� ���� ���� ���������� ���ְ� ���� 0 �ϴ���. �̻��� �̸��� ���ʿ� recent�� �� �� ������ �ʿ� ����.
	public void testUpdateRecentList() {
		assertEquals(0,f.saveSpecification("yes.yzb"));
	}

	@Test //���� ��� �� �޾ƿ�����.
	public void testGetPath() {
		assertNotNull(f.getPath());
	}

}

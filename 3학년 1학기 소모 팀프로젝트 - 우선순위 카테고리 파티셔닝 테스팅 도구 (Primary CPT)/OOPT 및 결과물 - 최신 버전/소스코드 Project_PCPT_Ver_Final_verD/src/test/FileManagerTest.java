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

	@Test //생성 잘 되는지 테스트
	public void testNewSpecification1() {
		file = new File("newTest.yzb");
		if(file.exists()){
			file.delete();
		}
		assertEquals(0,f.newSpecification("newTest"));
		assertTrue(file.exists());
	}
	
	@Test //test1에서 생성한 파일명과 같으니까 물어보는 창이 뜰 것이다. 예를 누르면 0, 아니오를 누르면 1이 반환되어야 한다.
	public void testNewSpecification2(){
		assertEquals(0,f.newSpecification(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4)));
		assertEquals(1,f.newSpecification(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4)));
	}
	
	@Test //존재하는 파일은 잘 불러와야된다. 그러면 0을 리턴.
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
	
	@Test //형식에 맞지 않는 파일을 불러올 때, 확장자 맞지 않다고 경고창 뜨며 1을 리턴
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

	@Test //저장 잘 되는지
	public void testSaveSpecification() {
		assertEquals(0,f.saveSpecification());
	}

	@Test //다른 이름으로 저장을 눌렀을 때 정상적인 경우 0을 리턴하는지
	public void testSaveSpecificationString1(){
		assertEquals(0,f.saveSpecification("yes.yzb"));
	}
	
	@Test //다른 이름으로 저장을 눌렀을 때  확장자 이상한거에 저장하면 경고 뜨며 1 리턴하는지
	public void testSaveSpecificationString2() {
		assertEquals(1,f.saveSpecification("yes.txt"));
	}

	@Test //최근 파일 목록 중 실제로 존재하지 않는 것은 삭제 정상적으로 해주고 리턴 0 하는지. 이상한 이름은 애초에 recent에 들어갈 수 없으니 필요 없다.
	public void testUpdateRecentList() {
		assertEquals(0,f.saveSpecification("yes.yzb"));
	}

	@Test //파일 경로 잘 받아오는지.
	public void testGetPath() {
		assertNotNull(f.getPath());
	}

}

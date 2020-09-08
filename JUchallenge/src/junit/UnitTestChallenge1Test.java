package junit;
import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTestChallenge1Test {

	@Test
	public void test() {

		UnitTestChallenge1 UTC = new UnitTestChallenge1();

		String expected = "test";

		assertEquals(expected + "@groovegear.com" , UTC.createMailAdress(expected));



	}

}

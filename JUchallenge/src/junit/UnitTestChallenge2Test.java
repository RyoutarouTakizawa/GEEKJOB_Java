package junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTestChallenge2Test {

	@Test
	public void culcPrice1() {

		UnitTestChallenge2 UTC = new UnitTestChallenge2();

		int age = 12;

		UTC.culcPrice(age);

		assertEquals(1000 , UTC.culcPrice(age));

	}

	@Test
	public void culcPrice2() {

		UnitTestChallenge2 UTC = new UnitTestChallenge2();

		int age = 17;

		UTC.culcPrice(age);

		assertEquals(1500 , UTC.culcPrice(age));

	}

	@Test
	public void culcPrice3() {

		UnitTestChallenge2 UTC = new UnitTestChallenge2();

		int age = 18;

		UTC.culcPrice(age);

		assertEquals(1800 , UTC.culcPrice(age));

	}

	@Test
	public void culcPrice4() {

		UnitTestChallenge2 UTC = new UnitTestChallenge2();

		int age = 65;

		UTC.culcPrice(age);

		assertEquals(1000 , UTC.culcPrice(age));

	}

	@Test
	public void culcPrice5() {

		UnitTestChallenge2 UTC = new UnitTestChallenge2();

		int age = -1;

		UTC.culcPrice(age);

		assertEquals(0 , UTC.culcPrice(age));

	}

}

package junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTestChallenge3Test {

	@Test
	public void culcChange1() {

		UnitTestChallenge3 UTC = new UnitTestChallenge3();

		String type = "tea";
		int money = 500;

		UTC.culcChange(type, money);

		assertEquals(500, UTC.culcChange(type, money));

	}

	@Test
	public void culcChange2() {

		UnitTestChallenge3 UTC = new UnitTestChallenge3();

		String type = "coffee";
		int money = 50;

		UTC.culcChange(type, money);

		assertEquals(50, UTC.culcChange(type, money));

	}

	@Test
	public void culcChange3() {

		UnitTestChallenge3 UTC = new UnitTestChallenge3();

		String type = "coffee";
		int money = 500;

		UTC.culcChange(type, money);

		assertEquals(400 , UTC.culcChange(type, money));

	}

	@Test
	public void culcChange4() {

		UnitTestChallenge3 UTC = new UnitTestChallenge3();

		String type = "juice";
		int money = 500;

		UTC.culcChange(type, money);

		assertEquals(350 , UTC.culcChange(type, money));

	}

}

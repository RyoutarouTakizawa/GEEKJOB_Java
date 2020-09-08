package junit;

public class UnitTestChallenge2 {

	int culcPrice(int age) {

		int price = 0;

		if(age >= 0  && age <= 12) {

			price = 1000;

		}else if(age >= 0 && age < 18) {

			price = 1500;

		}else if(age >= 65) {

			price = 1000;

		}else if(age >= 18) {

			price = 1800;

		}else if(age < 0) {

			price = 0;

		}

		return price;

	}

}

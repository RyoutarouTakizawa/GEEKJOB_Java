package junit;

public class UnitTestChallenge3 {

	int culcChange(String type, int money) {

		int price = 0;
		int change = 0;

		if(type.equals("coffee")) {

			price = 100;

		}else if(type.equals("juice")) {

			price = 150;

		}

		if(!(type.equals("coffee")) && !(type.equals("juice"))){

			change = money;

		}else if(money < price) {

			change = money;

		}else {

			change = money - price;

		}

		return change;

	}

}

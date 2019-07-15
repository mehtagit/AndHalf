package com.learning.demo;

public class Luhn {
	public static void main(String[] args) {
		System.out.println(check(357906049159340L));
	}

	public static boolean check(Long number) {
		boolean result = false;
		int modSum = 0;
		String input = String.valueOf(number);
		int lengthOfNumber = input.length();
		System.out.println("length = " + lengthOfNumber);

		for (int i = lengthOfNumber - 1; i >= 0; i--) {
			if (i % 2 != 0) {
				int doubleIt = (Integer.parseInt(String.valueOf(input.charAt(i)))) * 2;
				doubleIt = getSum(doubleIt);
				modSum = modSum + doubleIt;
			} else {
				modSum = modSum + Integer.parseInt(String.valueOf(input.charAt(i)));
			}
			System.out.println("I [" + i + "] [" + input.charAt(i) + "] ");
		}
		System.out.println("end result is  : " + modSum);
		if (modSum % 10 == 0)
			result = true;
		return result;
	}

	public static int getSum(int input) {
		int ones = (input / 1) % 10;
		int tens = (input / 10) % 10;

		int sum = ones + tens;
		if (sum > 9) {
			return getSum(sum);
		} else {
			System.out.println("return : " + sum);
			return sum;
		}
	}
}

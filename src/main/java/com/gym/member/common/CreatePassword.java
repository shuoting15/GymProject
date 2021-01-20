package com.gym.member.common;

public class CreatePassword {

	public String getRandomPassword() {
		int z;
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < 6; i++) {
			z = (int) ((Math.random() * 7) % 3);
			//System.out.println("z:"+z);
			if (z == 1) { // 放數字
				sb.append((int) ((Math.random() * 10) + 48));
			} else if (z == 2) { // 放大寫英文
				sb.append((char) (((Math.random() * 26) + 65)));
			} else {// 放小寫英文
				sb.append(((char) ((Math.random() * 26) + 97)));
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		CreatePassword createPd = new CreatePassword();
		String tempPd = createPd.getRandomPassword();
		System.out.println("臨時密碼:"+tempPd);

	}

}

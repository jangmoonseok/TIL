package exp.example;

public class ExceptionProcessExample1 {

	public static void main(String[] args) {
		ExceptionProcessClass1 class1 = new ExceptionProcessClass1();
		try {
			String userId = class1.selectUserInfo("abc", "abc");
			System.out.println(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ExceptionProcessClass1{
	/*
	 * �������� �ʴ� ID�� �Է����� �� ���ܸ� �߻���Ű�� �޼ҵ�
	 * ����ڿ��� ���� �޼����� ���� � ������ �߸��� ������ ������ �ľ��� �� �ֵ��� �Ѵ�.
	 */
	String selectUserInfo(String id, String pwd) throws Exception {
		if(id.equals("abc")) {
			throw new Exception("����� ������ ã�� �� �����ϴ�. ���̵� �ٽ� �Է����ּ���.");
//			throw new Exception();
		}
		
		return id;
	}
}
package ch01.ex08;

import java.util.List;
import java.util.ArrayList;

public final class LamdaExpCaptureTest {

	public static void main(String[] args) {
		String[] names = {"Peter", "Paul", "Marry"};
		List<Runnable> runners = new ArrayList<>();
		
		// �g��for
		//�@capture����name�͎����Ifinal�Ȃ̂Ő����ȃR�[�h�B���ꂼ��"Peter", "Paul", "Marry"��capture�����
		for (String name: names) 
			runners.add(()->System.out.println(name));
		
		// �g��for�̓W�J
		//List<String> nameList = Arrays.asList(names); 
		//for (Iterator<String> it = nameList.iterator(); it.hasNext();) {
		//	String name = it.next();
		//	runners.add(()->System.out.println(name));	
		//}
		
		//�@�ʏ��for->�L���v�`������i�������Ifinal�łȂ��̂ŃR���p�C���G���[�ƂȂ�
		//for (int i=0; i<names.length; i++) 
		//	runners.add(()->System.out.println(names[i]));
		
		names[0] = "0";
		names[1] = "1";
		names[2] = "2";
		
		for (Runnable runner: runners) {
			runner.run();
		}
	}

}

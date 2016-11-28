package player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import info.PlayerInfo;

public class PlayerRank {
	
	private static PlayerRank instance = new PlayerRank();
	
	private ArrayList<PlayerInfo> rank;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	//순위 정보가 저장되있는 파일 경로
	private File file;


	
	/**
	 * 생성되는 순간 파일을 읽은 후 리스트로 읽어옴
	 */
	private PlayerRank() {

		//이전기록 검색
		String path = "rank.dat";
		
		file = new File(path);
		
		loadRanking();
	}
	
	public void loadRanking() {

		// 랭킹정보 읽어오기
		if (file.exists()) {

			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);

				try {
					rank = (ArrayList<PlayerInfo>) ois.readObject();
					if (rank == null)
						rank = new ArrayList<PlayerInfo>();

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();

				//System.out.println("랭킹정보 파일 로드 성공");
			

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//System.out.println("랭킹정보 파일 없음");
		}
	}

	
	/**
	 *  Top5 랭킹 보여주기
	 */  
	public void showRanking() {
		
		System.out.println("순위        아이디          시간");
		System.out.println("━━━━━━━━━━━━━━━━━");
		for (int i = 0; i < rank.size(); i++) {
			System.out.println(" "+ (i + 1) + "." + rank.get(i));
			if (rank.size() ==5) break; 
		}
		System.out.println("━━━━━━━━━━━━━━━━━");
	}
	

	public void saveRanking() {

		//파일쓰기
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		try {
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			
			//RspInfo객체 저장
			oos.writeObject(rank);
			System.out.println("기록 저장.");
			
		} catch (Exception e) {
			System.out.println("기록 저장 실패.");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (oos != null ) oos.close();
				if (fos != null ) fos.close();
			} catch (IOException e) {
				
			}
		}

	}
	
	//순위정보를 insert
	public void insert(PlayerInfo pi) {
		
		rank.add(pi);
		
		if (rank.size() > 1) {
			for (int i = rank.size() - 1; i > 0; i--) {
				if (rank.get(i - 1).getRecordTime() < rank.get(i).getRecordTime()) {
					PlayerInfo temp = rank.get(i);
					rank.set(i, rank.get(i - 1));
					rank.set(i - 1, temp);
				} else {
					break;
				}
			}
		}
	}

	
	//Top5 랭킹체크 
	
	public boolean checkRanking(int recordTime) {
		
		int userCount =0;
		
		if (rank.size() < 5) {
			userCount = rank.size();
		} else {
			userCount = 5;
		}
		
		if (recordTime < rank.get(userCount).getRecordTime()) {
			return true;
		} else {
			return true;
		}
	}
	

	

	/**
	 * 파일 읽기 부분
	 */
	public void loadRank() {

		FileInputStream fis = null;
		try {
			
			
			if (file.exists()) {
				
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				
			}
			
			rank = (ArrayList<PlayerInfo>) ois.readObject();
			
			if (rank == null) rank = new ArrayList<PlayerInfo>();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	
	/**
	 * 순위정보 저장 리스트 자체를 저장함
	 */
	public void save() {
		
		FileOutputStream fos = null;
		try {
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(rank);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static PlayerRank getInstance() {

		return instance;
	}
	
	public void writeFile() {
		
		String path = "rank.dat";
		
		File f = new File("path");
		if (!f.exists()) f.mkdirs();
		
		//파일쓰기
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
	
		try {
			
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			
			//RspInfo객체 저장
			oos.writeObject(rank);
			System.out.println("기록 저장.");
			
		} catch (Exception e) {
			System.out.println("기록 저장 실패.");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (oos != null ) oos.close();
				if (fos != null ) fos.close();
			} catch (IOException e) {
				
			}
		}

	}
	
}

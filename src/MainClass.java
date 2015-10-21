import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
	private static ArrayList<String> links,sayfaadi;
	public static ExecutorService resimService,linkService;
	public static void main(String[] args) throws IOException {
			resimService = Executors.newCachedThreadPool();
			linkService = Executors.newCachedThreadPool();
			links = new ArrayList<>();
			sayfaadi = new ArrayList<>();
			menu();
			scanner.close();
	}
	static Scanner scanner = new Scanner(System.in);
	public static void menu() throws IOException{
		while(true){
			System.out.println("Yeni link eklemek icin 1");
			System.out.println("Linkleri listelemek için 2");
			System.out.println("Resimleri cekmek için 3");
			
			int menu_counter = 0;
			try {
				menu_counter= scanner.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				scanner.nextLine();
				e.printStackTrace();
			}
			switch (menu_counter) {
			case 1:
				System.out.print("Link : ");
				String link = scanner.next();
				System.out.println("sayfa adi :");
				String sayfaadi = scanner.next();
				System.out.println("link : "+link);
				links.add(link);
				MainClass.sayfaadi.add(sayfaadi);
				
				linkService.execute(new LinkOkuma(link,sayfaadi));
				break;
			case 2:
				for(int i =0;i<links.size();i++){
					System.out.println(MainClass.sayfaadi.get(i)+":"+links.get(i));
				}
				break;
			case 3:
				System.out.println("Dosya adi");
				String dosyaadi = scanner.next();
				
				resimService.execute(new ResimOkuma(dosyaadi));
				break;
			default:
				break;
			}
		}
		
	}

}

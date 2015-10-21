import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ResimOkuma implements Runnable{
	File file;
	private String fileName;
	private ArrayList<String> links;
	public ResimOkuma(String fileName) throws IOException{
		links = new ArrayList<>();
		String path = new File(".").getCanonicalPath()+"/"+fileName;
		System.out.println(path);
		this.file =new File(path );
		this.fileName = fileName;
	}
	@Override
	public void run() {
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String link  = null;
		try {
			while((link=reader.readLine())!=null){
				links.add(link);
			}
			reader.close();
			for(int i =0;i<links.size();i++){
				BufferedImage img = ImageIO.read(new URL(links.get(i)));
				ImageIO.write(img, "jpg",new File(  new File(".")+"/"+fileName+String.valueOf(i)+".jpg"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

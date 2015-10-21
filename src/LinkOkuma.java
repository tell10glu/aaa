import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LinkOkuma implements Runnable {
	private String baslangic_html, sayfaAdi;

	public LinkOkuma(String baslangicHtml, String sayfaAdi) {
		baslangic_html = baslangicHtml;
		this.sayfaAdi = sayfaAdi;
	}

	public String getLink() {
		return baslangic_html;
	}

	@Override
	public void run() {

		Document doc = null;

		String suanki_html = baslangic_html;

		BufferedWriter yazici = null;
		String path = "";

		try {
			path = new File(".").getCanonicalPath() + "/" + sayfaAdi;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		do {
			try {
				doc = Jsoup.connect(suanki_html).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Elements ilk_tik = doc.select("img#fbPhotoImage");
			String str_ilk_tik = ilk_tik.attr("src").toString();

			str_ilk_tik = str_ilk_tik.replaceAll("&amp;", "&");
			
			try {
				yazici = new BufferedWriter(new FileWriter(path, true));
				yazici.append(str_ilk_tik + "\n");
				yazici.close();
				yazici = new BufferedWriter(new FileWriter(path+"links",true));
				yazici.write(suanki_html+"\n");
				yazici.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Elements sonraki_sayfa = doc.select("a.photoPageNextNav");
			String str_sonraki_sayfa = sonraki_sayfa.attr("href").toString();
			str_sonraki_sayfa = "https://www.facebook.com" + str_sonraki_sayfa;
			// System.out.println(str_sonraki_sayfa);

			// System.out.println("-------------------------------------------------");

			suanki_html = str_sonraki_sayfa;

		} while (!suanki_html.equals(baslangic_html));

	}

}

package ma.inpt.rentingCarApp.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ma.inpt.rentingCarApp.entities.Car;

@Service
public class ExportListCars {
	public static ByteArrayInputStream productsPDFReport(List<Car> carEntity) {
	Document document = new Document();
	ByteArrayOutputStream out=new ByteArrayOutputStream();
	try {
		PdfWriter.getInstance(document,out);
		document.open();
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
		Paragraph para =new Paragraph("Liste des voitures", font);
		para.setAlignment(Element.ALIGN_CENTER);
		document.add(para);
		document.add(Chunk.NEWLINE);
		PdfPTable table= new PdfPTable(4);
		Stream.of("Car Name","Owner","release year","edition ").forEach(headerTitle->{
			PdfPCell header= new PdfPCell();
			com.itextpdf.text.Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(1);
			header.setPhrase(new Phrase(headerTitle,font2));
			table.addCell(header);	});
		for(Car location:carEntity) {
			
		
			PdfPCell titlecell= new PdfPCell(new Phrase(location.getCarName()));
			titlecell.setPaddingLeft(1);
			titlecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			titlecell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(titlecell);
			PdfPCell titlecell1= new PdfPCell(new Phrase(location.getOwner()));
			titlecell.setPaddingLeft(1);
			titlecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			titlecell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(titlecell1);
			
			int a=location.getReleaseYear();
			String edt=Integer.toString(a);
			PdfPCell titlecell2= new PdfPCell(new Phrase(edt));
			titlecell.setPaddingLeft(1);
			titlecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			titlecell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(titlecell2);
			
			int t=location.getEdition();
			String edt2=Integer.toString(t);
			PdfPCell titlecell3= new PdfPCell(new Phrase(edt2));
			titlecell.setPaddingLeft(1);
			titlecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			titlecell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(titlecell3);
			

		}
			
			
			
			document.add(table);
			document.close();}
			catch (DocumentException e) {
				e.printStackTrace();
			}
			return new ByteArrayInputStream(out.toByteArray());
		}
			
	
}
package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Standing;
import com.example.coursachTrzbd.services.ChampionshipService;
import com.example.coursachTrzbd.services.StandingService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private StandingService standingService;
    @Autowired
    private ChampionshipService championshipService;

    @GetMapping("/excel/{championshipId}")
    public void downloadExcel(@PathVariable Integer championshipId,
                              @RequestParam String season,
                              HttpServletResponse response) throws IOException {

        List<Standing> standings = standingService.findByChampionshipAndSeason(championshipId, season);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Standings");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Команда");
        header.createCell(1).setCellValue("И");
        header.createCell(2).setCellValue("В");
        header.createCell(3).setCellValue("Н");
        header.createCell(4).setCellValue("П");
        header.createCell(5).setCellValue("О");

        int rowNum = 1;
        for (Standing s : standings) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(s.getTeam().getName());
            row.createCell(1).setCellValue(s.getMatchesPlayed());
            row.createCell(2).setCellValue(s.getWins());
            row.createCell(3).setCellValue(s.getDraws());
            row.createCell(4).setCellValue(s.getLosses());
            row.createCell(5).setCellValue(s.getPoints());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String fileName = "standings_" + season + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @GetMapping("/pdf/{championshipId}")
    public void downloadPdf(@PathVariable Integer championshipId,
                            @RequestParam String season,
                            HttpServletResponse response) throws IOException {
        List<Standing> standings = standingService.findByChampionshipAndSeason(championshipId, season);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=standings_" + season + ".pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = new Font(Font.HELVETICA, 12, Font.BOLD);
        document.add(new Paragraph(championshipService.getById(championshipId).getName() + " – Сезон " + season, font));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.addCell("Команда");
        table.addCell("И");
        table.addCell("В");
        table.addCell("Н");
        table.addCell("П");
        table.addCell("О");

        for (Standing s : standings) {
            table.addCell(s.getTeam().getName());
            table.addCell(String.valueOf(s.getMatchesPlayed()));
            table.addCell(String.valueOf(s.getWins()));
            table.addCell(String.valueOf(s.getDraws()));
            table.addCell(String.valueOf(s.getLosses()));
            table.addCell(String.valueOf(s.getPoints()));
        }

        document.add(table);
        document.close();
    }
}

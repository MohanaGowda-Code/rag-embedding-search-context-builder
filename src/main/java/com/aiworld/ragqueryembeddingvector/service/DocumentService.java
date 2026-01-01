package com.aiworld.ragqueryembeddingvector.service;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class DocumentService {


    private final Tika tika = new Tika();

    public String extractText(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {

            BodyContentHandler handler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();

            TesseractOCRConfig ocrConfig = new TesseractOCRConfig();
            ocrConfig.setLanguage("eng");
            ocrConfig.setTimeoutSeconds(120);

            ParseContext context = new ParseContext();
            context.set(TesseractOCRConfig.class, ocrConfig);

            AutoDetectParser parser = new AutoDetectParser();
            parser.parse(is, handler, metadata, context);

            String text = handler.toString().trim();
            return text.isEmpty() ? "NO_TEXT_FOUND" : text;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR_EXTRACTING_TEXT";
        }
    }

}

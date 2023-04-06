package com.takehometest.TakeHomeTest.libraries.utils;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.takehometest.TakeHomeTest.models.JobDetails;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvUtil.class);
    public static ByteArrayResource convertToCSV(List<JobDetails> data) {
        LOGGER.info("convertToCsv");
        try {
            CsvSchema schema = CsvSchema.builder()
                    .addColumn("id")
                    .addColumn("type")
                    .addColumn("url")
                    .addColumn("created_at")
                    .addColumn("company")
                    .addColumn("company_url")
                    .addColumn("location")
                    .addColumn("title")
                    .addColumn("description")
                    .addColumn("how_to_apply")
                    .addColumn("company_logo")
                    .setUseHeader(true)
                    .build();
            CsvMapper mapper = new CsvMapper();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            SequenceWriter csvWriter = mapper.writerFor(List.class)
                    .with(schema)
                    .writeValues(out);

            for (Object detail : data) {
                List<String> cleanedDataList = new ArrayList<>();
                JobDetails jobDetails = (JobDetails) detail;


                for (Field field : jobDetails.getClass().getDeclaredFields()) {
                    field.setAccessible(true);

                    Object fieldValue = field.get(jobDetails);
                    if (fieldValue == null) {
                        continue;
                    }

                    String cleanedData = Jsoup.parse(field.get(jobDetails).toString()).text();
                    cleanedData = StringUtils.replaceEach(cleanedData, new String[]{"\n", "\r", "\t"}, new String[]{"", "", ""});

                    cleanedDataList.add(cleanedData);
                }

                csvWriter.write(cleanedDataList);
            }
            csvWriter.flush();
            writer.flush();
            return new ByteArrayResource(out.toByteArray());
        } catch (Exception e) {
            LOGGER.error("Failed to convert to CSV. Error {}", e.getMessage());
            return null;
        }
    }
}

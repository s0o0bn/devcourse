package com.example.jpa.global.util;

import com.example.jpa.domain.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {
    public static final String BASE_PATH = "/Users/yoonsoobin/DevCourse/temp_files/";

    public static List<FileDTO> saveFiles(MultipartFile[] files) {
        List<FileDTO> wrappedFiles = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;
                String fileType = getFileType(file.getOriginalFilename());
                String filePath = BASE_PATH + UUID.randomUUID() + fileType;
                FileDTO dto = FileDTO.builder()
                        .originalName(file.getOriginalFilename())
                        .savedPath(filePath)
                        .build();
                wrappedFiles.add(dto);
                file.transferTo(new File(filePath));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wrappedFiles;
    }

    private static String getFileType(String fileName) {
        if (fileName == null || !fileName.contains(".")) return "";
        return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

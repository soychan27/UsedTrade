package com.study.usedtrade.service;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.ItemImage;
import com.study.usedtrade.repository.ItemImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemImageService {
    @Autowired
    private ItemImageRepository itemImageRepository;

    public String generateUniqueFileName(String originalFileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        int lastDotIndex = originalFileName.lastIndexOf('.');
        String fileNameWithoutExtension = lastDotIndex != -1 ? originalFileName.substring(0, lastDotIndex) : originalFileName;
        String fileExtension = lastDotIndex != -1 ? originalFileName.substring(lastDotIndex) : "";

        return fileNameWithoutExtension + "_" + timestamp + fileExtension;
    }

    public ResponseEntity<String> uploadFile(MultipartFile[] files, String uploadDir, Item item) {
        if (files == null || files.length == 0) {
            System.out.println("Please select at least one file to upload.");
            return new ResponseEntity<>("Please select at least one file to upload.", HttpStatus.BAD_REQUEST);
        }

        List<String> uploadedFileNames = new ArrayList<>();

        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            if (!uploadDirectory.mkdirs()) {
                System.out.println("Failed to create upload directory.");
                return new ResponseEntity<>("Failed to create upload directory.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
                    File destFile = new File(uploadDir + File.separator + uniqueFileName);
                    file.transferTo(destFile);
                    uploadedFileNames.add(uniqueFileName);

                    ItemImage itemImage = new ItemImage();
                    itemImage.setItem(item);
                    itemImage.setImageName(uniqueFileName);
                    itemImage.setImagePath("/images/" + uniqueFileName); // Web 경로 설정

                    itemImageRepository.save(itemImage);
                } catch (IOException e) {
                    System.out.println("Failed to upload one or more files.");
                    return new ResponseEntity<>("Failed to upload one or more files.", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }

        System.out.println("Files uploaded successfully: " + uploadedFileNames);
        return new ResponseEntity<>("Files uploaded successfully: " + uploadedFileNames, HttpStatus.OK);
    }

    public List<ItemImage> findByItem(Item item) {
        return itemImageRepository.findByItem(item);
    }
}

package com.study.usedtrade.controller;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.service.ItemImageService;
import com.study.usedtrade.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Controller
@RequestMapping("/itemImage")
@Tag(name = "ItemImageController", description = "ItemImageController")
public class ItemImageController {
    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/upload")
    @Operation(summary = "다중 파일 업로드", description = "다중 파일 업로드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> handleFileUpload(
            @ModelAttribute Item item, @RequestParam("files") MultipartFile[] files) {

        try {
            itemService.write(item, files);
            return ResponseEntity.ok("Files uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files");
        }
    }
    /*
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("name") String name,
            @RequestParam("content") String content,
            @RequestParam("price") Integer price,
            @RequestParam("way") String way,
            @RequestParam("complete") boolean complete,
            @RequestParam("files") MultipartFile[] files) {

        Item item = new Item();
        item.setName(name);
        item.setContent(content);
        item.setPrice(price);
        item.setWay(way);
        item.setComplete(complete);

        try {
            itemService.write(item, files);
            return ResponseEntity.ok("Files uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files");
        }
    }
    */
}

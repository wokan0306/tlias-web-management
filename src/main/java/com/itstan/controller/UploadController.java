package com.itstan.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.itstan.pojo.Result;
import com.itstan.utils.AwsS3Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private AwsS3Utils awsS3Utils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {

        log.info("Document upload:  {}", image);

        // get original file name
        String originalFileName = image.getOriginalFilename();

        int index = originalFileName.lastIndexOf(".");
        String extname = originalFileName.substring(index);
        String newFileName = UUID.randomUUID() + extname;

        log.info("New Filename:  {}", newFileName);

        File fileObj = awsS3Utils.convertMultipartFileToFile(image);
        s3Client.putObject(new PutObjectRequest(bucketName, newFileName, fileObj));
        fileObj.delete();

        // set expired period to one month
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);

        return Result.success(s3Client.generatePresignedUrl(bucketName, newFileName, calendar.getTime()));
    }
}

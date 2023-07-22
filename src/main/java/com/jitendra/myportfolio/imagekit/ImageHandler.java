package com.jitendra.myportfolio.imagekit;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;

public class ImageHandler {
    private static ImageKit imageKit;
    public static void initializeImageKit() {
        System.out.println("*****************======*****"+System.getenv("PUBLIC_IMAGEKIT_KEY"));
        imageKit = ImageKit.getInstance();
        Configuration configuration = new Configuration(System.getenv("PUBLIC_IMAGEKIT_KEY"),
                                                        System.getenv("PRIVATE_IMAGEKIT_KEY"),
                                                        System.getenv("IMAGEKIT_URL_ENDPOINT"));
        imageKit.setConfig(configuration);
    }

    public static Result uploadImage(String filePath, String fileName) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        FileCreateRequest fileCreateRequest = new FileCreateRequest(bytes, fileName);
        fileCreateRequest.setUseUniqueFileName(false);
        Result result = imageKit.upload(fileCreateRequest);
        return result;
    }
    public static Result uploadBase64Image(String base64Image, String fileName) throws Exception {
        FileCreateRequest fileCreateRequest = new FileCreateRequest(base64Image, fileName);
        fileCreateRequest.setUseUniqueFileName(false);
        Result result = imageKit.upload(fileCreateRequest);
        return result;
    }
}

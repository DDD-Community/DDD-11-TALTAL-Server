package org.tatltal.proejct.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/image")
@Hidden
public class ImageController {


    @GetMapping("/{image}")
    public ResponseEntity<byte[]> getImage(@PathVariable("image") String imageFileName) {

        try {
            // Load the SVG file from resources
            Resource resource = new ClassPathResource("static/" + imageFileName);
            byte[] svgBytes = StreamUtils.copyToByteArray(resource.getInputStream());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.svg");
            headers.add(HttpHeaders.CONTENT_TYPE, "image/svg+xml");

            return new ResponseEntity<>(svgBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animation/{animationFileName}")
    public ResponseEntity<Object> getAnimationJson(@PathVariable String animationFileName) throws IOException {
        Resource resource = new ClassPathResource("static/" + animationFileName);
        byte[] svgBytes = StreamUtils.copyToByteArray(resource.getInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.svg");
        headers.add(HttpHeaders.CONTENT_TYPE, "image/svg+xml");

        return new ResponseEntity<>(svgBytes, headers, HttpStatus.OK);

    }
}

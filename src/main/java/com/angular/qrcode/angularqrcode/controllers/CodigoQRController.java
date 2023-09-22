package com.angular.qrcode.angularqrcode.controllers;

import java.io.IOException;
import java.util.Base64;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.angular.qrcode.angularqrcode.GeneradorCodigoQR;
import com.google.zxing.WriterException;

@RestController
public class CodigoQRController {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCodeImage.png";

    @GetMapping("/")
    @ResponseBody
    public ModelAndView getQRCode(Model model) {
        String medium = "https://pmarchionno.com";
        String github = "https://github.com/pmarchionno";

        byte[] image = new byte[0];
        try {

        // Generate and Return Qr Code in Byte Array
        image = GeneradorCodigoQR.getQRCodeImage(medium,250,250);

        // Generate and Save Qr Code Image in static/image folder
        GeneradorCodigoQR.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
        e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium",medium);
        model.addAttribute("github",github);
        model.addAttribute("qrcode",qrcode);

        ModelAndView layout = new ModelAndView();
        layout.setViewName("qrcode.html");

        return layout;
    }
}

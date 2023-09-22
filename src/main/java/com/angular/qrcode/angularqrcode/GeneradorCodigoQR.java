package com.angular.qrcode.angularqrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GeneradorCodigoQR {

    public static Path generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        // Para generar código QR usando ZXing necesitamos instanciar un objeto QRCodeWriter.
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        //La clase QRCodeWriter proporciona el método encode() para generar código y devolver un objeto BitMatrix.
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        // Desde el objeto BitMatrix podemos escribir código QR en el sistema de archivos usando el método estático MatrixToImageWriter.writeToPath().
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        
        return path.getFileName();
    }

    public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }
}

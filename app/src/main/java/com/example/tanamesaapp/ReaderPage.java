package com.example.tanamesaapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import android.content.Intent;


import java.io.IOException;

public class ReaderPage extends AppCompatActivity {

    private static String oldqrValue;
    private static final String TAG = "ReaderPage";
    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_page);

        surfaceView = findViewById(R.id.camerapreview);
        textView = findViewById(R.id.infotext);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    textView.setText("ERRO: Você não concedeu permissão de usar a câmera!");
                    return;
                }

                try {
                    cameraSource.start(holder);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                final String qrValue = qrCodes.valueAt(0).displayValue;
                if (qrCodes.size() != 0) {
                    if (!qrValue.equals(ReaderPage.oldqrValue)) {
                        ReaderPage.oldqrValue = qrValue;

                        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        vibrator.vibrate(200);

                        String[] splittedQRText = qrValue.split("/");
                        String restaurantName = splittedQRText[1].replace("-"," ");
                        String tableNumber = splittedQRText[2];

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("tableNumber", tableNumber);
                        intent.putExtra("restaurantName", restaurantName);

                        finish();
                        startActivity(intent);
                    }
                }
            }
        });
    }
}

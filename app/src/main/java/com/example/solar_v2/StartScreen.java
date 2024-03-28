package com.example.solar_v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class StartScreen extends View {
    private Bitmap startPageLogo;
    private Bitmap btnStartUp;
    private Bitmap btnStartDown;
    private Bitmap imagenFondo;
    private boolean playBtnState = false;
    private Context mContext;

    public StartScreen(Context context) {
        super(context);
        mContext = context;
        startPageLogo = BitmapFactory.decodeResource(getResources(), R.drawable.start_page_logo);
        btnStartUp = BitmapFactory.decodeResource(getResources(), R.drawable.btn_start_up);
        btnStartDown = BitmapFactory.decodeResource(getResources(), R.drawable.btn_start_down);
        imagenFondo = BitmapFactory.decodeResource(getResources(), R.drawable.fondo);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (imagenFondo != null) {
            canvas.drawBitmap(imagenFondo, null, new Rect(0, 0, getWidth(), getHeight()), null);
        }

        canvas.drawBitmap(startPageLogo, (getWidth() - startPageLogo.getWidth()) / 2, getHeight() / 4, null);
        Bitmap btnImage = playBtnState ? btnStartDown : btnStartUp;
        canvas.drawBitmap(btnImage, (getWidth() - btnImage.getWidth()) / 2, (getHeight() * 3) / 4, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        int btnStartX = (getWidth() - btnStartUp.getWidth()) / 2;
        int btnStartY = (getHeight() * 3) / 4;
        int btnEndX = btnStartX + btnStartUp.getWidth();
        int btnEndY = btnStartY + btnStartUp.getHeight();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (touchX >= btnStartX && touchX <= btnEndX && touchY >= btnStartY && touchY <= btnEndY) {
                    playBtnState = true;
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (playBtnState) {
                    playBtnState = false;
                    invalidate();
                    Intent gameIntent = new Intent(mContext, GameActivity.class);
                    mContext.startActivity(gameIntent);
                }
                break;
        }
        return true;
    }
}

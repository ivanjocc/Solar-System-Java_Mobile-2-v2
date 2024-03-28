package com.example.solar_v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienSolarSystem extends View {
    private List<AstreCeleste> planetas;
    private List<Bitmap> imagenesPlanetas;
    private Random random = new Random();
    private Bitmap naveImagen;
    private Bitmap imagenFondo;
    private float naveX = 100;
    private float naveY = 200;

    public AlienSolarSystem(Context context) {
        super(context);
        init();
    }

    public AlienSolarSystem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AlienSolarSystem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w != oldw || h != oldh) {
            colocarObjetosAleatoriamente();
            invalidate();
        }
    }

    private void init() {
        planetas = new ArrayList<>();
        imagenesPlanetas = new ArrayList<>();
        cargarPlanetas();
        naveImagen = BitmapFactory.decodeResource(getResources(), R.drawable.nave);
        imagenFondo = BitmapFactory.decodeResource(getResources(), R.drawable.fondo);
    }


    private void colocarObjetosAleatoriamente() {
        final int MAX_INTENTOS = 100;
        ArrayList<Rect> ocupado = new ArrayList<>();

        for (int i = 0; i < planetas.size(); i++) {
            AstreCeleste planeta = planetas.get(i);
            Bitmap imagenPlaneta = imagenesPlanetas.get(i);
            Rect area;
            boolean colision;
            int intentos = 0;

            do {
                colision = false;
                int x = random.nextInt(getWidth() - imagenPlaneta.getWidth());
                int y = random.nextInt(getHeight() - imagenPlaneta.getHeight());
                area = new Rect(x, y, x + imagenPlaneta.getWidth(), y + imagenPlaneta.getHeight());

                for (Rect r : ocupado) {
                    if (Rect.intersects(r, area)) {
                        colision = true;
                        break;
                    }
                }
                intentos++;
            } while (colision && intentos < MAX_INTENTOS);

            if (!colision) {
                planeta.setX(area.left + imagenPlaneta.getWidth() / 2);
                planeta.setY(area.top + imagenPlaneta.getHeight() / 2);
                ocupado.add(area);
            }
        }

        Rect areaNave;
        boolean colisionNave;
        int intentosNave = 0;
        do {
            colisionNave = false;
            int xNave = random.nextInt(getWidth() - naveImagen.getWidth());
            int yNave = random.nextInt(getHeight() - naveImagen.getHeight());
            areaNave = new Rect(xNave, yNave, xNave + naveImagen.getWidth(), yNave + naveImagen.getHeight());

            for (Rect r : ocupado) {
                if (Rect.intersects(r, areaNave)) {
                    colisionNave = true;
                    break;
                }
            }
            intentosNave++;
        } while (colisionNave && intentosNave < MAX_INTENTOS);

        if (!colisionNave) {
            naveX = areaNave.left;
            naveY = areaNave.top;
        }

    }

    private void cargarPlanetas() {
        planetas.clear();
        imagenesPlanetas.clear();

        planetas.add(new AstreCeleste(1, "Mercurio", 40, "#9e9e9e", 0, "mercurio", 100, 100));
        planetas.add(new AstreCeleste(2, "Venus", 60, "#e1c699", 0, "venus", 200, 200));
        planetas.add(new AstreCeleste(3, "Tierra", 70, "#6da7d9", 1, "tierra", 300, 300));
        planetas.add(new AstreCeleste(4, "Marte", 50, "#ff5733", 0, "marte", 400, 400));
        planetas.add(new AstreCeleste(5, "Júpiter", 140, "#c79b63", 0, "jupiter", 500, 500));
        planetas.add(new AstreCeleste(6, "Saturno", 120, "#ead9a4", 0, "saturno", 600, 600));
        planetas.add(new AstreCeleste(7, "Urano", 100, "#a4e4eb", 0, "urano", 700, 700));
        planetas.add(new AstreCeleste(8, "Neptuno", 100, "#4973a6", 1, "neptuno", 800, 800));
        planetas.add(new AstreCeleste(9, "Plutón", 30, "#d3c1d0", 0, "pluton", 900, 900));

        for (AstreCeleste planeta : planetas) {
            int resourceId = getResources().getIdentifier(planeta.getImageName(), "drawable", getContext().getPackageName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            imagenesPlanetas.add(bitmap);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (imagenFondo != null) {
            canvas.drawBitmap(imagenFondo, null, new Rect(0, 0, getWidth(), getHeight()), null);
        }

        Rect naveRect = new Rect((int)naveX, (int)naveY, (int)(naveX + naveImagen.getWidth()), (int)(naveY + naveImagen.getHeight()));
        Paint paint = new Paint();
        Paint paintTexto = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTexto.setColor(Color.WHITE);
        paintTexto.setTextSize(40);
        paintTexto.setTextAlign(Paint.Align.CENTER);

        String nombrePlanetaTocado = null;

        for (AstreCeleste planeta : planetas) {
            Bitmap imagenPlaneta = imagenesPlanetas.get(planetas.indexOf(planeta));
            int x = (int)planeta.getX() - (imagenPlaneta.getWidth() / 2);
            int y = (int)planeta.getY() - (imagenPlaneta.getHeight() / 2);
            Rect planetaRect = new Rect(x, y, x + imagenPlaneta.getWidth(), y + imagenPlaneta.getHeight());

            if (Rect.intersects(naveRect, planetaRect)) {
                planeta.setTocado(true);
                nombrePlanetaTocado = planeta.getName();
            }

            if (planeta.isTocado()) {
                paint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFCCCCCC));
            } else {
                paint.setColorFilter(null);
            }

            canvas.drawBitmap(imagenPlaneta, x, y, paint);
        }

        if (nombrePlanetaTocado != null) {
            canvas.drawText(nombrePlanetaTocado, getWidth() / 2, 60, paintTexto);
        }

        canvas.drawBitmap(naveImagen, naveX, naveY, null);
    }



    public void setNaveX(float x) {
        float limiteIzquierdo = 0;
        float limiteDerecho = getWidth() - (naveImagen != null ? naveImagen.getWidth() : 0);

        x = Math.max(limiteIzquierdo, Math.min(limiteDerecho, x));

        this.naveX = x;
    }

    public void setNaveY(float y) {
        float limiteSuperior = 0;
        float limiteInferior = getHeight() - (naveImagen != null ? naveImagen.getHeight() : 0);

        y = Math.max(limiteSuperior, Math.min(limiteInferior, y));

        this.naveY = y;
    }

    public float getNaveX() {
        return naveX;
    }

    public float getNaveY() {
        return naveY;
    }

}
package com.example.solar_v2;

import android.provider.BaseColumns;

public final class PlanetaContract {
    private PlanetaContract() {}

    public static class PlanetaEntry implements BaseColumns {
        public static final String TABLE_NAME = "planetas";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_TAMANO = "tamano";
        public static final String COLUMN_NAME_COLOR = "color";
        public static final String COLUMN_NAME_ESTADO = "estado";
        public static final String COLUMN_NAME_IMAGEN = "imagen";
    }
}


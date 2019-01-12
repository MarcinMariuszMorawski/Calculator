package pl.lodz.uni.math.morawski.marcin.calculator.database;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {
    }

    public static class DatabaseHistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "history";
        public static final String COLUMN_NAME_EXPRESSION = "expression";
    }
}

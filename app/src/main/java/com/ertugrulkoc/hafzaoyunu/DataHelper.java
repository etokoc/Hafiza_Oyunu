package com.ertugrulkoc.hafzaoyunu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DataHelper extends SQLiteOpenHelper {
    private static final String TABLO_AD = "Skorlar";
    private static final String ROW_ID_ = "id";
    private static final String hamleSayisi = "hamleSayisi";
    private static final String zaman = "zaman";
    private static final String oyun_Modu = "oyunModu";
    static String databaseName = "skorlar";
    static int version = 1;


    public DataHelper(Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_AD + "("
                + ROW_ID_ + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + oyun_Modu + " TEXT NOT NULL, "
                + hamleSayisi + " TEXT NOT NULL, "
                + zaman + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_AD);
        onCreate(db);
    }

    public void veriEkle(String hamleSayisi, String zaman,String oyun_Modu) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataHelper.oyun_Modu, oyun_Modu);
        values.put(DataHelper.hamleSayisi, hamleSayisi);
        values.put(DataHelper.zaman, zaman);
        db.insert(TABLO_AD, null, values);
        db.close();
    }

    public Cursor veriListele() {
        SQLiteDatabase db = this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        String[] sutunlar = {oyun_Modu,hamleSayisi, zaman};
        Cursor cr = db.query(TABLO_AD, sutunlar, null, null, null, null, null);//query fonksiyonu ile aldığımız parametreler yoluyla komutu kendi içerisinde yapılandırıyoruz.
        return cr;
    }


}

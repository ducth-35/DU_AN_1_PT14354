package company.huynhduc.camnangamthuc;


import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;

import company.huynhduc.adapter.adapterMonAn;
import company.luongchung.camnangamthuc.R;
import company.huynhduc.model.MonAn;

import static android.content.Context.MODE_PRIVATE;


public class TatCaFragment extends Fragment {

    AlertDialog dialog;
    ListView lvTatCa;
    adapterMonAn adapterTatCa;
    ArrayList<MonAn> listMonAnTatCa;
    String link = "http://www.doivi.net/nau-gi-hom-nay";
    String DATABASE_NAME="dbCamNangAmThuc.sqlite";
    String DB_PATH="/databases/";
    SQLiteDatabase sqLiteDatabase=null;
    String TrangThaiKhoiDong ="ChuaCo";
    PullRefreshLayout layoutRefresh;
    public TatCaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tat_ca, container, false);
        layoutRefresh = (PullRefreshLayout)view.findViewById(R.id.swipeRefreshLayoutTatCa);
        layoutRefresh.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layoutRefresh.setRefreshing(false);
            }
        });
        lvTatCa = (ListView)view.findViewById(R.id.lvTatCa);
        listMonAnTatCa = new ArrayList<>();
        new GetData().execute(); //lấy dữ liệu
        return view ;
    }

    public class GetData extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
//            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            layDuLieuTuSQLite();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapterTatCa=new adapterMonAn(getActivity(),R.layout.item_monan,listMonAnTatCa);
            lvTatCa.setAdapter(adapterTatCa);
//            dialog.dismiss();

        }
    }
    private void layDuLieuTuSQLite() {
        sqLiteDatabase=getActivity().openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from tbMonAn",null);
        listMonAnTatCa.clear();
        while (cursor.moveToNext())
        {
            int _ID=cursor.getInt(0);
            String _TenMonAn=cursor.getString(1);
            String _Title=cursor.getString(2);
            String _LinkImage=cursor.getString(3);
            String _LinkURL=cursor.getString(4);
            MonAn monAn=new MonAn(_ID,_TenMonAn,_Title,_LinkImage,_LinkURL);
            listMonAnTatCa.add(monAn);
        }
        cursor.close();

    }

}

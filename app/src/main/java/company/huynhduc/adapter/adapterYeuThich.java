package company.huynhduc.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

import company.huynhduc.camnangamthuc.TatCaFragment;
import company.huynhduc.camnangamthuc.chitiet;
import company.huynhduc.model.MonAn;
import company.luongchung.camnangamthuc.R;

import static android.content.Context.MODE_PRIVATE;

public class adapterYeuThich extends ArrayAdapter<MonAn> {
    Activity context;
    int resource;
    List<MonAn> objects;
    Button btnXoaYeuThich;
    TatCaFragment tatCaFragment;
    SQLiteDatabase sqLiteDatabase=null;
    String DATABASE_NAME="dbCamNangAmThuc.sqlite";
    String DB_PATH="/databases/";
    public adapterYeuThich(Activity context, int resource, List<MonAn> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        final View row = layoutInflater.inflate(this.resource, null);
        btnXoaYeuThich= (Button) row.findViewById(R.id.btnXoaYeuThich);
        TextView txtTenMon = (TextView) row.findViewById(R.id.txtTenMon);
        TextView txtTitle = (TextView) row.findViewById(R.id.txtTitle);
        ImageView ivIcon = (ImageView) row.findViewById(R.id.ivIcon);
        final MonAn monAn = objects.get(position);
        txtTenMon.setText(monAn.getTxtTenMon());
        txtTitle.setText(monAn.getTitle());
        String URL = monAn.getLinkImg();
        Picasso.with(context).load(URL).into(ivIcon);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyClickItem(monAn);
            }
        });
        btnXoaYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoaYeuThich(monAn);
                objects.remove(position);
                notifyDataSetChanged();
            }
        });
        return row;
    }

    private void xuLyXoaYeuThich(MonAn monAn) {
        sqLiteDatabase=context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("DELETE FROM TBYEUTHICH" + " WHERE TENMONAN"+"='"+monAn.getTxtTenMon()+"'");
        sqLiteDatabase.close();
    }
    private void xuLyClickItem(MonAn monAn) {
        Intent intent=new Intent(context, chitiet.class);
        intent.putExtra("TenMonAn",monAn.getTxtTenMon());
        intent.putExtra("Title",monAn.getTitle());
        intent.putExtra("LinkURL",monAn.getLinkURL());
        intent.putExtra("LinkImage",monAn.getLinkImg());
        context.startActivityForResult(intent,13);
    }
}



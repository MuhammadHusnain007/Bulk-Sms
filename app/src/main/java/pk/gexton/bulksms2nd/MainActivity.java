package pk.gexton.bulksms2nd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void order(View view) {

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("bulk.xls");
            Workbook wb = Workbook.getWorkbook(is);

            Sheet s = wb.getSheet(0);

            int row = s.getRows();
            int col = s.getColumns();

            String xx = "";

            for (int i = 0; i < row; i++) {
                for (int c = 0; c < col; c++) {

                    Cell z = s.getCell(c, i);
                    xx = xx + z.getContents();
                }
                xx = xx + "\n";
            }
            display(xx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void display(String value) {
        TextView x = findViewById(R.id.txt);
        EditText msg = findViewById(R.id.msg);
       // x.setText(value);
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + value));
            smsIntent.putExtra("sms_body", msg.getText().toString());
            startActivity(smsIntent);
        }

    }


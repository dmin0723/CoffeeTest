package dengmin.sj35.s1.studyjams.cn.coffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView numberTextView;
    private CheckBox creamCheckBox;
    private CheckBox chocolateCheckBox;
    private EditText nameEditText;
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberTextView = (TextView) findViewById(R.id.number_text_view);
        creamCheckBox = (CheckBox) findViewById(R.id.cream_check_box);
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        nameEditText = (EditText) findViewById(R.id.name_text_view);
    }

    public void decrease(View view) {
        if (number > 0) {
            number = number - 1;
            numberTextView.setText( number +"");
        } else {
            Toast.makeText(MainActivity.this, "数量不能为负数！", Toast.LENGTH_SHORT).show();
        }
    }

    public void increase(View view) {
        if (number == 100) {
            Toast.makeText(MainActivity.this, "100能喝完吗？", Toast.LENGTH_SHORT).show();
            return;
        }
        number = number + 1;
        numberTextView.setText( number +"");
    }
    public void order(View view) {
        int money = 5;
        String name = nameEditText.getText().toString();
        boolean hasCream = creamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String cream = "";
        String chocolate = "";

        if (hasCream) {
            cream = " Whipped cream";
            money += 1;
        }

        if (hasChocolate) {
            chocolate = "  Chocolate";
            money += 2;
        }

        int allMoney = money * number;
        String toppings = cream + chocolate;

        String priceMessage = "Name：" + name + "\nCoffee quantity：" + number + " cup" +
                "\nTopping：" + toppings + "\nPrice：" + allMoney + " $" + "\nThank you！";

        Uri uri = Uri.parse("smsto:138000138000");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", priceMessage);
        startActivity(intent);

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }
    }
}
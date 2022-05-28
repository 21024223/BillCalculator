package sg.edu.rp.c346.id21024223.billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText Amount;
    EditText NumOfPeople;
    ToggleButton ServiceCharge;
    ToggleButton GeneralServiceTax;
    EditText DiscountAmount;
    ToggleButton Split;
    RadioGroup Payment;
    RadioButton Cash;
    RadioButton PayNow;
    TextView EachPaid;
    TextView Results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Amount = findViewById(R.id.numAmount);
        NumOfPeople = findViewById(R.id.Pax);
        ServiceCharge = findViewById(R.id.SVC);
        GeneralServiceTax = findViewById(R.id.GST);
        Results = findViewById(R.id.TotalBillResults);
        EachPaid = findViewById(R.id.Eachperson);
        Split = findViewById(R.id.SplitBill);
        DiscountAmount = findViewById(R.id.Discount);
        Payment = findViewById(R.id.PaymentMethod);
        PayNow = findViewById(R.id.CashlessPayment);
        Cash = findViewById(R.id.CashPayment);

        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = Amount.getText().toString();
                String data2 = NumOfPeople.getText().toString();

                double amount = Double.parseDouble(data1);
                double newAmount = 0;
                int pax = Integer.parseInt(data2);

                if(GeneralServiceTax.isChecked() == true && ServiceCharge.isChecked() == true) {
                    newAmount = amount * 1.10 * 1.07;
                } else if(GeneralServiceTax.isChecked() == false && ServiceCharge.isChecked() == true) {
                    newAmount = amount * 1.10;
                } else if(GeneralServiceTax.isChecked() == true && ServiceCharge.isChecked() == false) {
                    newAmount = amount * 1.07;
                } else {
                    newAmount = amount;
                }

                if(DiscountAmount.getText().toString().length() != 0) {
                    newAmount *= 1 - Double.parseDouble(DiscountAmount.getText().toString()) / 100;
                }

                double eachPays = newAmount / pax;
                double newTotalAmt = newAmount;

                String msg1 = String.format("%.2f", newTotalAmt);
                String msg = String.format("%.2f", eachPays);

                String payMethod = new String(" is to be transferred by PayNow");
                String payMethod2 = new String(" is to be transferred by Cash");

                int selectedID = Payment.getCheckedRadioButtonId();


                if(Payment.getCheckedRadioButtonId() == -1) {
                    EachPaid.setText("Each pays: $" + msg + payMethod);
                    Results.setText("Total amount: $" + msg1 + payMethod);
                } else {
                    EachPaid.setText("Each pays: $" + msg + payMethod2);
                    Results.setText("Total amount: $" + msg1 + payMethod2);

                }
                

            }
        }); {

        }


    }
}

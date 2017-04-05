package flyberson.tipcalculatormin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.*;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    // currency and percent formatter objects
 private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    // intial values
private double billAmount = 0.0; // end result
private double percent = 0.15;  // starting tip
private TextView amountTextView;  //input
private TextView percentTextView;  // display tip percent
private TextView tipTextView;  // display tip
private TextView totalTextView; // display bill

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);

        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekbar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calculate(){
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount*percent;
        double total= billAmount+tip;

        // format to currency
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));

    }

    private final OnSeekBarChangeListener seekBarListener   = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent=progress/100.0; // convert to percent
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billAmount= Double.parseDouble(s.toString())/100;
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException    e){
                amountTextView.setText("");
                billAmount=0.0;

            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


    int quantity = 2;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setData(Uri.parse("geo:22.5,88.38"));
        //if (intent.resolveActivity(getPackageManager()) != null) {
        //    startActivity(intent);
        //}
        CheckBox whipped_cream=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        /*String checked="false";
        if(whipped_cream.isChecked()){
            checked="true";
        }*/
        CheckBox chocolate=(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean checkedchoc=chocolate.isChecked();

        boolean checkedsu= whipped_cream.isChecked();
        EditText nameofperson=(EditText) findViewById(R.id.namebox);
        String name=nameofperson.getText().toString();



        int price=calculatePrice(checkedchoc,checkedsu);
        String priceMessage =createOrderSummary(price,checkedsu,checkedchoc,name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //displayMessage(priceMessage);





    }

    public void increment(View view) {

        if(quantity<=99)
        {
            quantity = quantity + 1;
        }

        displayQuantity(quantity);

    }



    public void decrement(View view) {


        if(quantity>1)
        {
            quantity = quantity - 1;
        }


        displayQuantity(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private void displayMessage(String message) {
        TextView orderSummarytextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummarytextView.setText(message);
    }



    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(boolean choc, boolean whip) {

        int pricepercup=5;
        if(choc)
        {
            pricepercup=pricepercup+2;
        }
        if(whip)
        {
            pricepercup=pricepercup+1;
        }
        return quantity*pricepercup;

        /* yello */
    }

    public String createOrderSummary(int price, boolean checkedwhipppedcream,boolean chocolate,String name)
    {
        String str1="Name : "+name+"\nAdd Whipped Cream? "+checkedwhipppedcream+"\nAdd Chocolate? "+chocolate+"\nQuantity : "+quantity+"\nTotal : "+price+"$\n"+getString(R.string.thank_you);
        return str1;

    }


}
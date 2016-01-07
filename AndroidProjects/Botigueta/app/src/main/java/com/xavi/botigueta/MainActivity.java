package com.xavi.botigueta;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Products> products = new ArrayList();
    List<Products> productsShoppingCart = new ArrayList();
    Spinner spinner;
    SpinnerAdapter spinnerAdapter;
    Button addButton;

    ListView listShoppingCart;
    ShoppingCartAdapter shoppingCartAdapter;

    TextView totalAmount;
    Double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates the products array
        products.add(new Products(0, "Poma", 1.56, R.drawable.poma, 0));
        products.add(new Products(1, "Plàtan", 0.15, R.drawable.platan, 0));
        products.add(new Products(2, "Pastís", 12.99, R.drawable.pastis, 0));

        //Getting the spinner and setting it's adapter
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new SpinnerAdapter(this, products);
        spinner.setAdapter(spinnerAdapter);

        //Getting the listView and creating it's adapter
        listShoppingCart = (ListView) findViewById(R.id.listView);
        shoppingCartAdapter = new ShoppingCartAdapter(this, productsShoppingCart);

        totalAmount = (TextView) findViewById(R.id.textView4);

        addButton = (Button) findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = spinner.getSelectedItemPosition();
                boolean repeated = false;
                int i = 1;

                //This is the first time that the user adds a product
                if (productsShoppingCart.isEmpty()){
                    productsShoppingCart.add(new Products(products.get(id).getId(), products.get(id).getName(), products.get(id).getPrice(), products.get(id).getImage(), 1));
                } else {
                    //If there are already products, check if we add a new one or update the quantity
                    for(Products product: productsShoppingCart){
                        if (product.getId() == id){
                            repeated = true;
                            productsShoppingCart.get(i-1).setQuantity(productsShoppingCart.get(i-1).getQuantity()+1);
                            break;
                        }
                        if (i == productsShoppingCart.size() && repeated == false){
                            productsShoppingCart.add(new Products(products.get(id).getId(), products.get(id).getName(), products.get(id).getPrice(), products.get(id).getImage(), 1));
                        }
                        i++;
                    }
                }
                //Setting the adapter to the new list
                listShoppingCart.setAdapter(shoppingCartAdapter);

                //Setting the total amount for the shopping cart
                total = 0.0;
                DecimalFormat df = new DecimalFormat("#.00");
                for(Products product: productsShoppingCart){
                    total = total + (product.getPrice() * product.getQuantity());
                }
                totalAmount.setText(df.format(total) + "€");
            }
        });
    }

    //This are both adapters.
    private class SpinnerAdapter extends BaseAdapter{

        private Context context;
        List<Products> productsSpinner;

        public SpinnerAdapter(Context context, List<Products> productsSpinner) {
            this.context = context;
            this.productsSpinner = productsSpinner;
        }

        @Override
        public int getCount() {
            return productsSpinner.size();
        }

        @Override
        public Object getItem(int position) {
            return productsSpinner.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.spinner_item, null);
            }
            ((TextView) convertView.findViewById(R.id.productNameSpinner)).setText(productsSpinner.get(position).getName());
            ((TextView) convertView.findViewById(R.id.productPriceSpinner)).setText(String.valueOf(productsSpinner.get(position).getPrice()));
            return convertView;
        }
    }

    private class ShoppingCartAdapter extends BaseAdapter{
        private Context context;
        List<Products> productsShoppingCart;

        public ShoppingCartAdapter(Context context, List<Products> productsShoppingCart) {
            this.context = context;
            this.productsShoppingCart = productsShoppingCart;
        }


        @Override
        public int getCount() {
            return productsShoppingCart.size();
        }

        @Override
        public Object getItem(int position) {
            return productsShoppingCart.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.shopping_cart_item, null);
            }
            ((TextView) convertView.findViewById(R.id.shoppingCartName)).setText(productsShoppingCart.get(position).getName());
            ((TextView) convertView.findViewById(R.id.quantity)).setText(String.valueOf(productsShoppingCart.get(position).getQuantity() + " x "));
            ((TextView) convertView.findViewById(R.id.price)).setText(String.valueOf(productsShoppingCart.get(position).getPrice()));
            ((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(productsShoppingCart.get(position).getImage());
            return convertView;
        }
    }
}

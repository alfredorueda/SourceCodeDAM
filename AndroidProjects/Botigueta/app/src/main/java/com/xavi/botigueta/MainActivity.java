package com.xavi.botigueta;

/**
 * Created by Xavi on 22/12/15.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Products> products = new ArrayList();
    List<Products> productsShoppingCart = new ArrayList();
    TextView totalAmount;
    Double total;

    Spinner spinner;
    SpinnerAdapter spinnerAdapter;
    ListView listShoppingCart;
    ShoppingCartAdapter shoppingCartAdapter;

    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates the products array
        products.add(new Products(0, "Cireres", 1.56, R.drawable.cireres, 0));
        products.add(new Products(1, "Plàtan", 0.15, R.drawable.platan, 0));
        products.add(new Products(2, "Pastís", 12.99, R.drawable.pastis, 0));
        products.add(new Products(3, "Barra de pa", 0.42, R.drawable.barra_pa, 0));
        products.add(new Products(4, "Pa dolç", 0.65, R.drawable.pa_dols, 0));
        products.add(new Products(5, "Croissant", 0.85, R.drawable.croissant, 0));
        products.add(new Products(6, "Bistec de vedella 1/2Kg", 14.50, R.drawable.bistec_vedella, 0));
        products.add(new Products(7, "Panet de llet", 0.15, R.drawable.panet_llet, 0));
        products.add(new Products(8, "Dotzena d'ous", 1.89, R.drawable.dotzena_ous, 0));
        products.add(new Products(9, "Farina", 0.99, R.drawable.farina, 0));
        products.add(new Products(10, "Raïm", 1.24, R.drawable.raim, 0));
        products.add(new Products(11, "Pernil cuit", 9.99, R.drawable.pernil_cuit, 0));
        products.add(new Products(12, "Piruleta", 0.10, R.drawable.piruleta, 0));
        products.add(new Products(13, "Poma", 0.23, R.drawable.poma, 0));
        products.add(new Products(14, "Pera", 0.24, R.drawable.pera, 0));
        products.add(new Products(15, "Pinya", 1.50, R.drawable.pinya, 0));
        products.add(new Products(16, "Maduixes", 1.25, R.drawable.maduixes, 0));
        products.add(new Products(17, "Sucre", 0.99, R.drawable.sucre, 0));
        products.add(new Products(18, "Síndria", 3.65, R.drawable.sindria, 0));
        products.add(new Products(19, "Llet", 0.75, R.drawable.llet, 0));
        products.add(new Products(20, "Cafè", 0.76, R.drawable.cafe, 0));
        products.add(new Products(21, "Àpat preparat", 5.85, R.drawable.apat_preparat, 0));
        products.add(new Products(22, "Pizza", 3.55, R.drawable.pizza, 0));
        products.add(new Products(23, "Salmó", 4.56, R.drawable.salmo, 0));
        products.add(new Products(24, "Pésols", 0.55, R.drawable.pesols, 0));
        products.add(new Products(25, "Bolets", 0.42, R.drawable.bolets, 0));
        products.add(new Products(26, "Ceba", 0.35, R.drawable.ceba, 0));
        products.add(new Products(27, "Formatge", 2.30, R.drawable.formatge, 0));
        products.add(new Products(28, "Pastanaga", 0.21, R.drawable.pastanaga, 0));

        //Getting the spinner and setting it's adapter
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new SpinnerAdapter(this, products);
        spinner.setAdapter(spinnerAdapter);

        //Getting the listView and setting it's adapter
        listShoppingCart = (ListView) findViewById(R.id.listView);
        shoppingCartAdapter = new ShoppingCartAdapter(this, productsShoppingCart);
        listShoppingCart.setAdapter(shoppingCartAdapter);

        //This is the label to display the total amount for the shopping list
        totalAmount = (TextView) findViewById(R.id.textView4);

        addButton = (Button) findViewById(R.id.button);

        //Remove items from the shopping list when the user "long clicks" the item
        listShoppingCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Checks if it has to decrease the quantity or remove the product
                if (productsShoppingCart.get(position).getQuantity() > 1) {
                    productsShoppingCart.get(position).setQuantity(productsShoppingCart.get(position).getQuantity()-1);
                } else {
                    productsShoppingCart.remove(position);
                }
                //Refresh the adapter to be in sync with the data
                shoppingCartAdapter.notifyDataSetChanged();

                //Setting the total amount for the shopping cart
                calculateTotalAmount();
                return false;
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spinnerId = spinner.getSelectedItemPosition();
                int i = 0;

                //This is the first time that the user adds a product
                if (productsShoppingCart.isEmpty()){
                    productsShoppingCart.add(new Products(products.get(spinnerId).getId(), products.get(spinnerId).getName(), products.get(spinnerId).getPrice(), products.get(spinnerId).getImage(), 1));
                } else {
                    //If there are already products, check if we add a new one or update the quantity
                    for(Products product: productsShoppingCart){
                        if (product.getId() == spinnerId){
                            productsShoppingCart.get(i).setQuantity(productsShoppingCart.get(i).getQuantity()+1);
                            break;
                        }
                        if (i == productsShoppingCart.size()-1){
                            productsShoppingCart.add(new Products(products.get(spinnerId).getId(), products.get(spinnerId).getName(), products.get(spinnerId).getPrice(), products.get(spinnerId).getImage(), 1));
                        }
                        i++;
                    }
                }
                //Update the adapter to be in sync with the data
                shoppingCartAdapter.notifyDataSetChanged();

                //Setting the total amount for the shopping cart
                calculateTotalAmount();
            }
        });
    }

    public void calculateTotalAmount(){
        total = 0.0;
        DecimalFormat df = new DecimalFormat("#.00");
        for(Products product: productsShoppingCart){
            total = total + (product.getPrice() * product.getQuantity());
        }
        if (total == 0.00){
            totalAmount.setText("0.00" + "€");
        } else {
            totalAmount.setText(df.format(total) + "€");
        }

    }

    //This is the adapter for the spinner
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

    //This is the adapter for the listView
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

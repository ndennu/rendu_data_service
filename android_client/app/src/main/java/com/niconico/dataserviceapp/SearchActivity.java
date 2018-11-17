package com.niconico.dataserviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.Brand;
import com.niconico.dataserviceapp.Models.Category;
import com.niconico.dataserviceapp.Models.Items;
import com.niconico.dataserviceapp.WebServices.IAPIListener;
import com.niconico.dataserviceapp.WebServices.SearchProvider;
import com.niconico.dataserviceapp.adapter.ItemsAdapter;
import com.niconico.dataserviceapp.service.GeolocService;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.shopSpinner)
    MaterialSpinner shopSpinner;
    @BindView(R.id.categorySpinner)
    MaterialSpinner categorySpinner;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.items_rv)
    RecyclerView recyclerView;

    private ItemsAdapter itemsAdapter;
    private ArrayList<Items> itemsList;
    private long bandType = 1;
    private ArrayList<Category> categories;
    private ArrayList<Brand> brands;
    private Long brandId;
    private Long categoryId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        initRecyclerView();

        setUpTabs();
        setUpSpinner();
        getAllBrand();
        startService(new Intent(this, GeolocService.class));
    }

    @OnClick(R.id.fab_account)
    public void onFabAccountClick() {
        startActivity(new Intent(this, AccountActivity.class));
    }

    private void initRecyclerView() {
        itemsList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        itemsAdapter = new ItemsAdapter(this, itemsList);
        itemsAdapter.setListener(new ItemsAdapter.Listener() {
            @Override
            public void onItemClick(Items item) {
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.ITEM_ID, item.getId());
                intent.putExtra(DetailsActivity.ITEM_NAME, item.getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(itemsAdapter);
    }

    private void setUpTabs() {
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bandType = tab.getPosition() + 1;
                getAllBrand();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setUpSpinner() {
        shopSpinner.setHint("Magasin");
        categorySpinner.setHint("Categorie");
        setTouchSpinner();
    }

    private void setTouchSpinner() {
        shopSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Brand>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Brand item) {
                Log.d("TEST", "CLICK " + item + " : " + id);
                brandId = item.getId();
                if (brandId != 0) {
                    categorySpinner.setEnabled(true);
                    getAllCategory();
                    return;
                }

                categorySpinner.setEnabled(false);
            }
        });

        categorySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Category>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Category item) {
                Log.d("TEST", "CLICK " + item + " : " + id);
                categoryId = item.getId();
                if (categoryId != 0)
                    getItemsWithBrandIdAndCategoryId();
            }
        });
    }

    private void getAllBrand() {
        SearchProvider.getInstance().getAllBrandWithTypeId(bandType, new IAPIListener<APIResult<ArrayList<Brand>>>() {
            @Override
            public void onSuccess(APIResult<ArrayList<Brand>> response) {
                categorySpinner.setEnabled(true);
                brands = response.data;
                shopSpinner.setItems(brands);

                brandId = brands.get(0).getId();
                categorySpinner.setEnabled(true);
                getAllCategory();
            }

            @Override
            public void onError(String s) {
                Log.d("TEST", "GET BRAND ERROR: " + s);
            }
        });
    }

    private void getAllCategory() {
        SearchProvider.getInstance().getAllCategoryWithBrandId(brandId, new IAPIListener<APIResult<ArrayList<Category>>>() {
            @Override
            public void onSuccess(APIResult<ArrayList<Category>> response) {
                categories = response.data;
                categorySpinner.setItems(categories);

                if(categories.size() > 0) {
                    categoryId = categories.get(0).getId();
                    getItemsWithBrandIdAndCategoryId();
                } else {
                    itemsList.clear();
                    itemsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String s) {
                Log.d("TEST", "GET CATEGORY ERROR: " + s);
                categorySpinner.setItems(new ArrayList<>());
                categorySpinner.setEnabled(false);

                itemsList.clear();
                itemsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getItemsWithBrandIdAndCategoryId() {
        SearchProvider.getInstance().getItemsWithBrandIdAndCategoryId(brandId, categoryId, new IAPIListener<APIResult<ArrayList<Items>>>() {
            @Override
            public void onSuccess(APIResult<ArrayList<Items>> response) {
                Log.d("TEST", "ITEMS OK");
                itemsAdapter.setItemsList(response.data);
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String s) {
                Log.d("TEST", "GET ITEMS ERROR: " + s);

                itemsList.clear();
                itemsAdapter.notifyDataSetChanged();
            }
        });
    }
}

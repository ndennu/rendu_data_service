package com.niconico.dataserviceapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.User;
import com.niconico.dataserviceapp.WebServices.IAPIListener;
import com.niconico.dataserviceapp.WebServices.UserProvider;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.emailTxt)
    TextView emailTxt;
    @BindView(R.id.passwordTxt)
    TextView passwordTxt;
    @BindView(R.id.dateTxt)
    TextView dateTxt;
    @BindView(R.id.genderSpinner)
    MaterialSpinner genderSpinner;
    @BindView(R.id.sizeSpinner)
    MaterialSpinner sizeSpinner;
    @BindView(R.id.shoeSizeSpinner)
    MaterialSpinner shoeSizeSpinner;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });

        initUI();
        loadUser();
    }

    private void initUI() {
        genderSpinner.setItems("M", "F");
        genderSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                user.setSexe(item.compareTo("M") == 0);
            }
        });

        sizeSpinner.setItems("XS", "S", "M", "L", "XL", "XXL", "XXXL");
        sizeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                user.setSize(item);
            }
        });

        shoeSizeSpinner.setItems("35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49");
        shoeSizeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                user.setShoe_size(item);
            }
        });
    }

    private void loadUser() {
        long userId = getSharedPreferences(Consts.SHARED_PREFERENCES, MODE_PRIVATE).getLong(Consts.PREF_USER_ID, 0);
        UserProvider.getInstance().getUserById(userId, new IAPIListener<APIResult<User>>() {
            @Override
            public void onSuccess(APIResult<User> response) {
                user = response.data;
                emailTxt.setText(user.getEmail());
                passwordTxt.setText(user.getPassword());
                dateTxt.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(user.getBirthdate()));
                genderSpinner.setText(user.getSexe() ? "F" : "M");
                sizeSpinner.setText(user.getSize());
                shoeSizeSpinner.setText(user.getShoe_size());
            }

            @Override
            public void onError(String s) {
                Toast.makeText(AccountActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.updateBtn)
    public void onUpdateClick() {
        UserProvider.getInstance().updateUser(user.getId(), user, new IAPIListener<APIResult<Long>>() {
            @Override
            public void onSuccess(APIResult<Long> response) {
                Snackbar.make(toolbar, getString(R.string.account_updated), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(AccountActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

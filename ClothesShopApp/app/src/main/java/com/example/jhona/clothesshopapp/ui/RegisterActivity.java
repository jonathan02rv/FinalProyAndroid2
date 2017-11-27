package com.example.jhona.clothesshopapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jhona.clothesshopapp.R;
import com.example.jhona.clothesshopapp.presenter.RegisterContract;
import com.example.jhona.clothesshopapp.presenter.RegisterPresenter;
import com.example.jhona.clothesshopapp.storage.PreferencesHelper;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private Button btnRegister, btnAddPhoto;
    private EditText eteEmail;
    private EditText etePass;
    private EditText eteConfPass;
    private EditText eteDni;
    private EditText eteName;
    private EditText eteLastName;
    private String username;
    private String password;
    private String confPassword;
    private String dni;
    private String name;
    private String lastName;

    private View flayLoading;
    private RegisterPresenter registerPresenter = new RegisterPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setUpPresenter();
        init();
    }

    private void setUpPresenter() {
        registerPresenter = new RegisterPresenter();
        registerPresenter.attachView(this);
    }

    private void init() {

        eteEmail=(EditText)findViewById(R.id.eteEmail);
        etePass=(EditText)findViewById(R.id.etePass);
        eteConfPass=(EditText)findViewById(R.id.eteConfPass);
        eteDni=(EditText)findViewById(R.id.eteDni);
        eteName=(EditText)findViewById(R.id.eteName);
        eteLastName=(EditText)findViewById(R.id.eteLastName);
        btnRegister=(Button)findViewById(R.id.btnRegistrar);
        btnAddPhoto=(Button)findViewById(R.id.btnAddPhoto);
        flayLoading=findViewById(R.id.flayLoading);

    }

    public void registrar(View view) {

        registerPresenter.Register();

    }

    @Override
    public void showLoading() {
        flayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        flayLoading.setVisibility(View.GONE);
    }

    @Override
    public void gotoMain() {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(RegisterActivity.this,
                message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(RegisterActivity.this,
                message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean validateForm() {
        username = eteEmail.getText().toString();
        password = etePass.getText().toString();
        confPassword = eteConfPass.getText().toString();
        dni = eteDni.getText().toString();
        name = eteName.getText().toString();
        lastName = eteLastName.getText().toString();

        if(username.isEmpty())
        {
            eteEmail.setError("Error campo email");
            return false;
        }
        if(password.isEmpty())
        {
            etePass.setError("Error campo contraseña");
            return false;
        }
        if(confPassword.isEmpty())
        {
            eteConfPass.setError("Error campo confirmar contraseña");
        }
        if(dni.isEmpty())
        {
            eteDni.setError("Error campo dni");
            return false;
        }
        if(name.isEmpty())
        {
            eteName.setError("Error campo nombre");
            return false;
        }
        if(lastName.isEmpty())
        {
            eteLastName.setError("Error campo apellido");
            return false;
        }
        if(!password.equals(confPassword))
        {
            showErrorMessage("Las contraseñas deben ser iguales");
            return false;
        }
        return true;
    }

    @Override
    public void saveBLSession(String username, String token) {
        PreferencesHelper.saveBLSession(this,username,token);
    }

    @Override
    public String getEmail() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getDni() {
        return this.dni;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getConfPassword() {
        return this.lastName;
    }
}

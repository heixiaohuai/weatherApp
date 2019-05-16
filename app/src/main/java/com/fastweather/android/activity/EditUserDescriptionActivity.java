package com.fastweather.android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fastweather.android.R;

public class EditUserDescriptionActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userDescriptionEditText;
    private TextView tvCooperationHinTextView;
    private Button saveUserDescriptionButton;
    private Button saveUserDescriptionTitleButton;
    private Button backButton;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_description);
        titleTextView = (TextView) findViewById(R.id.user_info_title);
        titleTextView.setText("个性签名");
        userDescriptionEditText = (EditText) findViewById(R.id.user_info_update_description);
        tvCooperationHinTextView = (TextView) findViewById(R.id.tv_cooperation_hin);
        showCharNumber(35);
        if (TextUtils.isEmpty(userDescriptionEditText.getText().toString())){
            tvCooperationHinTextView.setText("0/35");
        }else {
            int ed_length = userDescriptionEditText.getText().length();
            int length = 100 - ed_length;
            tvCooperationHinTextView.setText(String.valueOf(length)+"/35");
        }
        saveUserDescriptionButton = (Button) findViewById(R.id.user_info_save_user_description_button);
        saveUserDescriptionTitleButton = (Button) findViewById(R.id.user_info_save);
        backButton = (Button) findViewById(R.id.user_info_back_button);
        saveUserDescriptionButton.setOnClickListener(this);
        saveUserDescriptionTitleButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    private void showCharNumber(final int maxNumber){
        userDescriptionEditText.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int number = editable.length();
                int length = 35-number;
                tvCooperationHinTextView.setText(String.valueOf(length)+"/35");
                selectionStart = userDescriptionEditText.getSelectionStart();
                selectionEnd = userDescriptionEditText.getSelectionEnd();
                if (temp.length()>maxNumber){
                    editable.delete(selectionStart-1, selectionEnd);
                    int tempSelection = selectionStart;
                    userDescriptionEditText.setText(editable);
                    userDescriptionEditText.setSelection(tempSelection);
                    Toast.makeText(EditUserDescriptionActivity.this,"最多输入35个字",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_info_save_user_description_button:
            case R.id.user_info_save:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("保存你的个性签名？");
                builder.setMessage("确认保存?");
                builder.setCancelable(true);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(EditUserDescriptionActivity.this, UserInformationActivity.class);
                        intent.putExtra("user_description",userDescriptionEditText.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
                break;

            case R.id.user_info_back_button:
                finish();
                break;

            default:break;
        }
    }
}

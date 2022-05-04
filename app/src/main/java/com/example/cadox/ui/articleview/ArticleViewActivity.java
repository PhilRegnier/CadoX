package com.example.cadox.ui.articleview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadox.bo.Article;
import com.example.cadox.R;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ArticleViewActivity extends AppCompatActivity {

    private ImageButton imageButtonInternet;
    private ImageButton imageButtonEdit;
    private ImageButton imageButtonSend;
    private CheckBox checkBox;
    private TextView textViewIntitule;
    private TextView textViewDescription;

    Article article = new Article(1, R.string.intitule_rayban, R.string.description_rayban, R.string.prix_rayban, byte niveau, String url) );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        imageButtonInternet = findViewById(R.id.imageButtonInternet);
        imageButtonEdit = findViewById(R.id.imageButtonEdit);
        imageButtonSend = findViewById(R.id.imageButtonSend);
        checkBox = findViewById(R.id.checkBoxEtat);
        textViewIntitule = findViewById(R.id.textViewIntitule);
        textViewDescription = findViewById(R.id.textViewDescription);


        imageButtonInternet.setOnClickListener(v -> {

            TextView textViewUrl = findViewById(R.id.textViewUrl);
            CharSequence url = textViewUrl.getText();
            if (!TextUtils.isEmpty(url)) {
                Toast.makeText(ArticleViewActivity.this, url, Toast.LENGTH_LONG).show();
            }
        });

        imageButtonEdit.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(ArticleViewActivity.this);
            builder.setMessage(R.string.confirm_edit_message)
                    .setTitle(R.string.confirm_edit_dialog_title)
                    .setPositiveButton(R.string.confirm_edit_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // OUI
                        }
                    })
                    .setNegativeButton(R.string.confirm_edit_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // NON
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        TextView textViewDateAchat = findViewById(R.id.textViewDateAchat);
        checkBox.setChecked(!TextUtils.isEmpty(textViewDateAchat.getText()));

        imageButtonSend.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(v, R.string.confirm_email_send, Snackbar.LENGTH_LONG);
            snackbar.setAction("envoyer", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // action à prévoir
                }
            });
            snackbar.show();
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalDate today = LocalDate.now();
            if (isChecked) {
                textViewDateAchat.setText(getResources().getString(R.string.article_view_dateachat) + today.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
                textViewDateAchat.setVisibility(View.VISIBLE);
                ;
            }
            else {
                textViewDateAchat.setText(null);
                textViewDateAchat.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void displayData() {
        Locale local = Locale.getDefault();

        textViewIntitule.setText(currentArticle.getIntitule());
        textViewDescription.setText(currentArticle.getDescription());
    }
}

package com.coders.coronavirus.prevention;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.coders.coronavirus.R;

public class AnswerPrevention extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_prevention);
        Intent i=getIntent();
        TextView preventionAnswer=findViewById(R.id.preventionAnswer);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            preventionAnswer.setText(Html.fromHtml(i.getStringExtra("answer"),Html.FROM_HTML_MODE_LEGACY));
        else
            preventionAnswer.setText(Html.fromHtml(i.getStringExtra("answer")));


    }

}

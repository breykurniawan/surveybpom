package com.bpom.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bpom.app.BE;
import com.bpom.app.R;
import com.bpom.app.models.answers.GAnswers;
import com.bpom.app.models.questions.GQuestions;
import com.bpom.app.utils.Cons;
import com.bpom.app.utils.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<GQuestions> lists; //inisialisasi List dengan object GQuestions
    private List<GAnswers> listsAnswer; //inisialisasi List dengan object GQuestions
    Context c;
    String TAG;

    SparseIntArray checked = new SparseIntArray();

    //construktor SurveyAdapter
    public QuestionAdapter(Context c, List<GQuestions> lists) {
        this.c = c;
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_question, parent, false);
        ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
        return holder;
    } //fungsi yang dijalankan saat ViewHolder dibuat

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final GQuestions data = lists.get(position); //inisialisasi object DataMahasiwa
        holder.mNo.setText(String.valueOf(position + 1) + ". ");
        holder.mId.setText(String.valueOf(data.getBQuestionId()));
        holder.mNama.setText(data.getBTitle());
        holder.mAnswer.setText(data.getQuestionType());
        String qt = data.getQuestionType();
        if (null != qt) {
            if (qt.equals("Essay")) {
//                holder.btnJwb.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
                        holder.mEssay.setVisibility(View.VISIBLE);
                        holder.llRB.setVisibility(View.GONE);
//                    }
//                });
//                holder.mAnswer.setVisibility(View.GONE);
            } else if (qt.equals("Yes/No")) {
//                holder.btnJwb.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                https://stackoverflow.com/questions/27836405/create-radiobutton-dynamically-with-string-array

                        holder.mEssay.setVisibility(View.GONE);
                        holder.llRB.setVisibility(View.VISIBLE);

//                String src = data.getBOptionList().replace("\"","");
//                JsonHelper json = JsonHelper.fromStringToJSON(src);
//                if (json.obj != null) {
//                        JSONObject jsonObject = (JSONObject) json.obj;
//                        JsonParser jsonParser = new JsonParser();
//                        JsonObject jo = (JsonObject)jsonParser.parse(jsonObject.toString());
//                        JsonArray jArray = jo.getAsJsonArray("f1");
//                        Gson gJson = new Gson();
//                        ArrayList ab = gJson.fromJson(jArray, ArrayList.class);
//                        for(int i =0; i<jArray.size();i++)
//                        {
//                            holder.radioButtonView = new RadioButton(c);
//                            holder.radioButtonView.setText(ab.get(i).toString());
//                            holder.radioGroup.addView(holder.radioButtonView, holder.p);
//                        }
//                }

                        String ids = String.valueOf(data.getBQuestionId());
//                if (!(checked.indexOfKey(position) >-1)) {
                        AndroidNetworking.get(Cons.API_ANSWER + ids)
                                .setPriority(Priority.HIGH)
                                .build()
                                .getAsObjectList(GAnswers.class, new ParsedRequestListener<List<GAnswers>>() {
                                    @Override
                                    public void onResponse(List<GAnswers> r) {
                                        if (r.size() > 0) {
                                            listsAnswer = r;
                                            for (int i = 0; i < listsAnswer.size(); i++) {
                                                RadioButton radioButton = new RadioButton(c);
                                                radioButton.setText(listsAnswer.get(i).getBOption());
                                                radioButton.setId(i);
                                                RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                                                holder.rgA.addView(radioButton, rprms);
                                                holder.tvStatus.setText("1");

                                                checked.put(-1, -1);
                                                if (checked.indexOfKey(position) > -1) {
                                                    holder.rgA.check(checked.get(position));
                                                } else {
                                                    holder.rgA.clearCheck();
                                                }

//                                        int selection = holder.rgA.getCheckedRadioButtonId();
//                                        for (int b = 0; b < listsAnswer.size(); b++)
//                                        {
//                                            if (b == selection)
//                                                BE.TShort(b+ " = " + listsAnswer.get(b).getBOption());
//                                        }
                                                holder.rgA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                    @SuppressLint("ResourceType")
                                                    @Override
                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                        final int selectedId = holder.rgA.getCheckedRadioButtonId();

                                                        if (checkedId > -1) {
                                                            checked.put(position, checkedId);
                                                        } else {
                                                            if (checked.indexOfKey(position) > -1)
                                                                checked.removeAt(checked.indexOfKey(position));
                                                        }

                                                    }
                                                });

//                                        holder.radioButtonView = new RadioButton(c);
//                                        holder.radioButtonView.setText(listsAnswer.get(i).getBOption());
//                                        holder.radioGroup.addView(holder.radioButtonView, holder.p);
//                                        holder.llRB.removeViewInLayout(holder.llRB);
                                            }

                                        } else {
                                            BE.TShort(c.getString(R.string.err_no_data));
                                        }
                                    }

                                    @Override
                                    public void onError(ANError error) {
                                        BE.TShort(error.getErrorDetail());
                                        Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                                        Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                                        Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                                    }
                                });
//            }

//                    }
//                });

            } else {
//                holder.btnJwb.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {

                        holder.mEssay.setVisibility(View.GONE);
                        holder.mAnswer.setVisibility(View.GONE);
                        holder.mAnswer.setVisibility(View.VISIBLE);
                        holder.mAnswer.setText(data.getBOptionList());

                    }
//                });
        }
//
////                AndroidNetworking.get(Cons.API_ANSWER + data.getBQuestionId())
////                        .setPriority(Priority.HIGH)
////                        .build()
////                        .getAsObjectList(GAnswers.class, new ParsedRequestListener<List<GAnswers>>() {
////                            @Override
////                            public void onResponse(List<GAnswers> r) {
////                                if (r.size() > 0) {
////                                    listsAnswer = r;
////                                    holder.mId.setOnClickListener(new View.OnClickListener() {
////                                        @Override
////                                        public void onClick(View view) {
////                                            Toast.makeText(c, listsAnswer.get(1).getBOption(), Toast.LENGTH_SHORT).show();
////                                        }
////                                    });
////                                } else {
////                                    BE.TShort(c.getString(R.string.err_no_data));
////                                }
////                            }
////
////                            @Override
////                            public void onError(ANError error) {
////                                BE.TShort(error.getErrorDetail());
////                                Log.d(TAG, "onError errorCode : " + error.getErrorCode());
////                                Log.d(TAG, "onError errorBody : " + error.getErrorBody());
////                                Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
////                            }
////                        });
//
//        }else {
//            holder.mEssay.setVisibility(View.GONE);
//            holder.mAnswer.setVisibility(View.GONE);
//        }

//        holder.mAnswer.setText(data.getBOptionList().get);
//        Log.d("jsonnya", lists.get(0).getBQuestionOption());
//        String src = data.getBQuestionOption();
//            String[] dest = new String[src.length()];
//            System.arraycopy(src, 0, dest, 0, src.length());
//            System.out.println(Arrays.toString(dest));
//            holder.mAnswer.setText(String.valueOf(Arrays.toString(dest)));

//        String src = "{\"Perkotaan\",\"Pedesaan\"}";
//        String[] dest = new String[src.length()];
//        System.arraycopy(src, 0, dest, 0, src.length());
//        Log.d("cek",dest.toString());


//        JsonHelper json = JsonHelper.fromStringToJSON(data.getBOptionList());
//        if(null != data.getBOptionList()) {
//            String src = data.getBOptionList().replace("\"","");
//            JsonHelper json = JsonHelper.fromStringToJSON(src);
//            if (json.obj != null) {
//
//                // If the String is a JSON array
//                if (json.isJsonArray) {
//                    JSONArray jsonArray = (JSONArray) json.obj;
//                    Log.d("jea", jsonArray.toString());
//                }
//                // If it's a JSON object
//                else {
//                    JSONObject jsonObject = (JSONObject) json.obj;
//                    Log.d("jeo", jsonObject.toString());
//
//                    JsonParser jsonParser = new JsonParser();
////                    JsonObject jo = (JsonObject)jsonParser.parse(jsonObject.toString());
////                    JsonArray jArray = jo.getAsJsonArray("f1"); // get json array
////
////                    Gson gJson = new Gson();
////                    ArrayList jsonObjArrayList = gJson.fromJson(jArray, ArrayList.class);
////                                        for (int i = 0; i < jsonObjArrayList.size(); i++)
////                                        {
////                                            RadioButton radioButton = new RadioButton(c);
////                                            radioButton.setText(jsonObjArrayList.get(i).toString());
////                                            radioButton.setId(i);
////                                            RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
////                                            holder.rgA.addView(radioButton, rprms);
////                                        }
//
//                                        JsonObject jo = (JsonObject)jsonParser.parse(jsonObject.toString());
//                    JsonArray jArray = jo.getAsJsonArray("f1");
//                                        Gson gJson = new Gson();
//                    ArrayList ab = gJson.fromJson(jArray, ArrayList.class);
//                    for(int i =0; i<ab.size();i++)
//                    {
//                        holder.radioButtonView = new RadioButton(c);
//                        holder.radioButtonView.setText(ab.get(i).toString());
//                        holder.radioGroup.addView(holder.radioButtonView, holder.p);
//                    }
//
//                }
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return lists.size(); //mengambil item sesuai urutan
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mNama, mNo, mAnswer,mId,tvStatus; //inisialisasi variabel
        LinearLayout mEssay, llRB;
        RadioGroup rgA;
        Button btnJwb, btnSave;
//        LinearLayout.LayoutParams p;
//        RadioGroup radioGroup = new RadioGroup(c);
//        RadioButton radioButtonView;

        public ViewHolder(View itemView) {
            super(itemView);
            TAG = c.getClass().getSimpleName();
            mNo = itemView.findViewById(R.id.tvNo);
            mId = itemView.findViewById(R.id.tvId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            mNama = itemView.findViewById(R.id.tvName);
            mAnswer = itemView.findViewById(R.id.tvAnswer);
            mEssay = itemView.findViewById(R.id.llAnswerEssay);
            llRB = itemView.findViewById(R.id.llRB);
            rgA = itemView.findViewById(R.id.rgAnswer);
            btnJwb = itemView.findViewById(R.id.btnJawab);

            final int position = Integer.parseInt(mNo.getText().toString().replaceAll(". ","")) - 1;
//            AndroidNetworking.get(Cons.API_ANSWER + mId.getText().toString())
//                        .setPriority(Priority.HIGH)
//                        .build()
//                        .getAsObjectList(GAnswers.class, new ParsedRequestListener<List<GAnswers>>() {
//                            @Override
//                            public void onResponse(List<GAnswers> r) {
//                                if (r.size() > 0) {
//                                    listsAnswer = r;
//                                    for (int i = 0; i < listsAnswer.size(); i++) {
//                                        RadioButton radioButton = new RadioButton(c);
//                                        radioButton.setText(listsAnswer.get(i).getBOption());
//                                        radioButton.setId(i);
//                                        RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//                                        rgA.addView(radioButton, rprms);
//                                        tvStatus.setText("1");
//
//                                        checked.put(-1,-1);
//                                        if(checked.indexOfKey(position)>-1){
//                                            rgA.check(checked.get(position));
//                                        }else{
//                                            rgA.clearCheck();
//                                        }
//
////                                        int selection = rgA.getCheckedRadioButtonId();
////                                        for (int b = 0; b < listsAnswer.size(); b++)
////                                        {
////                                            if (b == selection)
////                                                BE.TShort(b+ " = " + listsAnswer.get(b).getBOption());
////                                        }
//                                        rgA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                                            @SuppressLint("ResourceType")
//                                            @Override
//                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                                                final int selectedId = rgA.getCheckedRadioButtonId();
//
//                                                if(checkedId > -1){
//                                                    checked.put(position, checkedId);
//                                                }else{
//                                                    if(checked.indexOfKey(position)>-1)
//                                                        checked.removeAt(checked.indexOfKey(position));
//                                                }
//
//                                            }
//                                        });
//
////                                        radioButtonView = new RadioButton(c);
////                                        radioButtonView.setText(listsAnswer.get(i).getBOption());
////                                        radioGroup.addView(radioButtonView, p);
////                                        llRB.removeViewInLayout(llRB);
//                                    }
//
//                                } else {
//                                    BE.TShort(c.getString(R.string.err_no_data));
//                                }
//                            }
//
//                            @Override
//                            public void onError(ANError error) {
//                                BE.TShort(error.getErrorDetail());
//                                Log.d(TAG, "onError errorCode : " + error.getErrorCode());
//                                Log.d(TAG, "onError errorBody : " + error.getErrorBody());
//                                Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
//                            }
//                        });

//            btnJwb.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    final int position = Integer.parseInt(mNo.getText().toString().replaceAll(". ","")) - 1;
//                    String qt = mAnswer.getText().toString();
//                    if (null != qt) {
//                        if (qt.equals("Essay")) {
//                            mEssay.setVisibility(View.VISIBLE);
//                            llRB.setVisibility(View.GONE);
//                        } else if (qt.equals("Yes/No")) {
//                            mEssay.setVisibility(View.GONE);
//                            llRB.setVisibility(View.VISIBLE);
//                            String ids = String.valueOf(mId.getText().toString());
////                if (!(checked.indexOfKey(position) >-1)) {
//                            AndroidNetworking.get(Cons.API_ANSWER + ids)
//                                    .setPriority(Priority.HIGH)
//                                    .build()
//                                    .getAsObjectList(GAnswers.class, new ParsedRequestListener<List<GAnswers>>() {
//                                        @Override
//                                        public void onResponse(List<GAnswers> r) {
//                                            if (r.size() > 0) {
//                                                listsAnswer = r;
//                                                for (int i = 0; i < listsAnswer.size(); i++) {
//                                                    RadioButton radioButton = new RadioButton(c);
//                                                    radioButton.setText(listsAnswer.get(i).getBOption());
//                                                    radioButton.setId(i);
//                                                    RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//                                                    rgA.addView(radioButton, rprms);
////                                                    tvStatus.setText("1");
//                                                    llRB.removeViewInLayout(rgA);
//
//                                                    checked.put(-1, -1);
//                                                    if (checked.indexOfKey(position) > -1) {
//                                                        rgA.check(checked.get(position));
//                                                    } else {
//                                                        rgA.clearCheck();
//                                                    }
//
//                                                    rgA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                                                        @SuppressLint("ResourceType")
//                                                        @Override
//                                                        public void onCheckedChanged(RadioGroup group, int checkedId) {
//                                                            final int selectedId = rgA.getCheckedRadioButtonId();
//
//                                                            if (checkedId > -1) {
//                                                                checked.put(position, checkedId);
//                                                            } else {
//                                                                if (checked.indexOfKey(position) > -1)
//                                                                    checked.removeAt(checked.indexOfKey(position));
//                                                            }
//
//                                                        }
//                                                    });
//                                                }
//
//                                            } else {
//                                                BE.TShort(c.getString(R.string.err_no_data));
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onError(ANError error) {
//                                            BE.TShort(error.getErrorDetail());
//                                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
//                                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
//                                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
//                                        }
//                                    });
//                        }
//                    } else {
//                        mEssay.setVisibility(View.GONE);
//                        mAnswer.setVisibility(View.GONE);
//                        mAnswer.setVisibility(View.VISIBLE);
//                    }
//                }
//            });

            btnSave = itemView.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BE.TShort("Saved");
                }
            });

//            p = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.FILL_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//            llRB.addView(radioGroup, p);
            // creating Radio buttons Object//
//            radioButtonView = new RadioButton(c);

//            mNama.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    JsonHelper json = JsonHelper.fromStringToJSON(mAnswer.getText().toString());
//            if (json.obj != null || json.obj.equals("")) {
//
//                // If the String is a JSON array
//                if (json.isJsonArray) {
//                    JSONArray jsonArray = (JSONArray) json.obj;
//                    Log.d("jea", jsonArray.toString());
//                }
//                // If it's a JSON object
//                else {
//                    JSONObject jsonObject = (JSONObject) json.obj;
//                    Log.d("jea", jsonObject.toString());
//
//                }
//            }
//                }
//            });

//            mNama.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String ids = mId.getText().toString();
//                    Toast.makeText(c, ids, Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(c, QuestionsActivity.class);
//                    Bundle b = new Bundle();
//                    b.putString("id",ids);
//                    i.putExtras(b);
//                    c.startActivity(i);
//                }
//            });
        }
    }
}
package com.example.testing.Calendrier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Calendrier.Adapter.CalendarAdapter;
import com.example.testing.R;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    Button Avant;

    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        dialog = new Dialog(this);
        initwidgets();
        selectedDate = LocalDate.now();
        setMonthView();
        Avant=(Button)findViewById(R.id.btnav);
    }



    private void initwidgets() {
        monthYearText= findViewById(R.id.monYearTV);
        calendarRecyclerView = findViewById(R.id.CalendarRecyclerView);
    }
    private void setMonthView() {
        monthYearText.setText(monthYearFromDatee(selectedDate));


        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);


        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    private String monthYearFromDatee(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM YYYY");
        return date.format(formatter);

    }

    private ArrayList<String> daysInMonthArray(LocalDate date){
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(7);


        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();





        for (int i=0;i <= 42;i++){
            if(i<= dayOfWeek || i>daysInMonth + dayOfWeek  ){
                System.out.println("date maintenant "+ selectedDate.getDayOfMonth());


                daysInMonthArray.add("");

            }else {
                int dateDay= i-dayOfWeek;
                String   day=String.valueOf(dateDay);
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int mouth = cal.get(Calendar.MONTH)+1;
                System.out.println("month:"+mouth);
                if (selectedDate.getMonthValue()==mouth && selectedDate.getYear()==year) {
                    if (selectedDate.getDayOfMonth()>dateDay){
                        day="";
                    }
                }
                daysInMonthArray.add(day);

            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM");
        return date.format(formatter);

    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.equals("")){
            String mesagee= "selected Date "+monthYearFromDate(selectedDate)+"-"+dayText;

            Toast.makeText(this,mesagee,Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, Commandesj.class);
        String dttt = monthYearFromDate(selectedDate)+'-'+dayText ;
        intent.putExtra("date",dttt);
        System.out.println(monthYearFromDate(selectedDate)+"-"+dayText);

        startActivity(intent);
        //createDialog();


    }

    // private void createDialog() {
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mes commandes du jour :")
                .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();*/

    //dialog.setContentView(R.layout.comj_layout_dialog);
    //AlertDialog.Builder builder = new AlertDialog.Builder(this);

    //LayoutInflater inflater = getLayoutInflater();
    //View dialogView = (View) inflater.inflate(R.layout.comj_layout_dialog, null);
    //builder.setView(dialogView);
    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


    // ImageView imageViewclose=dialog.findViewById(R.id.imgvclose);
    //ImageView imageViewclose= ( ImageView) dialogView.findViewById(R.id.imgvclose);

    //imageViewclose.setOnClickListener(new View.OnClickListener() {
    //@Override
    //public void onClick(View view) {
    // dialog.dismiss();
    // }
    //});
    //AlertDialog dialog = builder.create();
    // dialog.show();


    // }

}
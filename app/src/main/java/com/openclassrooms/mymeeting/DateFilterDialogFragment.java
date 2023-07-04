package com.openclassrooms.mymeeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.databinding.FragmentDateFilterBinding;
import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.events.FilterMeetingEvent;
import com.openclassrooms.mymeeting.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFilterDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFilterDialogFragment extends BottomSheetDialogFragment {
    private Date dateSearch;
    static Bundle mBundle = new Bundle();
    private final MyMeetingApiService service = DI.getMyMeetingApiService();
    private FragmentDateFilterBinding binding;

    private TimePickerDialog picker;

    public DateFilterDialogFragment() {
    }
    protected DateFilterDialogFragment(Parcel in) {
        dateSearch = (Date) in.readSerializable();
    }

    public static DateFilterDialogFragment newInstance() {
        DateFilterDialogFragment fragment = new DateFilterDialogFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateFilterBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    /**
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            private DatePickerDialog pickerDate;
            @Override
            public void onClick(View v) {
                TimeZone timeZone = TimeZone.getTimeZone("France/Paris");
                Calendar cldr= Calendar.getInstance();
                Log.d("timeZone", "onClick: "+cldr.getTimeZone());
                cldr.setTimeZone(timeZone);
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                final int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerDate = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String stringHour = "09:00";
                                String dateString = Utils.getStringFromDate(new Date(year,(monthOfYear),dayOfMonth,9,00));
                                dateSearch = Utils.getDateFromString(dateString);

                                binding.buttonSelectDate.setText(dateString);
                                binding.buttonSelectTime.setText(stringHour);
                            }
                        }, year, month, day);
                pickerDate.show();
            }
        });
        binding.buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                dateSearch.setHours(sHour);
                                dateSearch.setMinutes(sMinute);
                                String stMinute="";
                                String stHour="";
                                if (sMinute<=9){stMinute="0"+sMinute;}
                                if (sHour<=9){stHour="0"+sHour;}
                                binding.buttonSelectTime.setText((stHour + ":" + stMinute));
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FilterMeetingEvent(dateSearch));
                dismiss();
            }
        });
    }

    }

package com.openclassrooms.mymeeting.views.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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
import com.openclassrooms.mymeeting.controler.MeetingRepository;
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
    private Calendar calendarSearch;
    private FragmentDateFilterBinding binding;
    private final TimeZone timeZone = TimeZone.getDefault();
    private TimePickerDialog picker;
    private Date dateSearch;

    public DateFilterDialogFragment() {
        //show be Empty
    }

    /**
     * @return instance of DateFilterDialogFragment
     */
    public static DateFilterDialogFragment newInstance() {
        DateFilterDialogFragment fragment = new DateFilterDialogFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    /**
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /**
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return view binding
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateFilterBinding.inflate(inflater, container, false);
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

            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                Calendar cldr = Calendar.getInstance(timeZone);
                Log.d("timeZone", "onClick: " + cldr);
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                final int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerDate = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            /**
                             * @param view        the picker associated with the dialog
                             * @param year        the selected year
                             * @param monthOfYear the selected month (0-11 for compatibility with
                             *                    {@link Calendar#MONTH})
                             * @param dayOfMonth  the selected day of the month (1-31, depending on
                             *                    month)
                             */
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                calendarSearch = Calendar.getInstance(timeZone);
                                calendarSearch.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                calendarSearch.set(Calendar.MONTH, monthOfYear);
                                calendarSearch.set(Calendar.YEAR, year);
                                String dateString = Utils.getStringFromDate(calendarSearch.getTime());
                                binding.buttonSelectDate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                            }
                        }, year, month, day);
                pickerDate.show();
            }
        });
        binding.buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance(timeZone);
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            /**
                             * @param tp      the view associated with this listener
                             * @param sHour   the hour that was set
                             * @param sMinute the minute that was set
                             */
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                calendarSearch.set(Calendar.HOUR_OF_DAY, sHour);
                                calendarSearch.set(Calendar.MINUTE, sMinute);
                                calendarSearch.set(Calendar.SECOND, 0);
                                String dateString = Utils.getStringFromDate(calendarSearch.getTime());

                                String stMinute = "";
                                String stHour = "";
                                if (sMinute < 10) {
                                    stMinute = "0" + sMinute;
                                } else {
                                    stHour = String.valueOf(sHour);
                                }
                                if (sHour < 10) {
                                    stHour = "0" + sHour;
                                } else {
                                    stMinute = String.valueOf(sMinute);
                                }
                                binding.buttonSelectTime.setText((sHour + ":" + stMinute));
                                binding.buttonSelectDate.setText(calendarSearch.getTime().toString());
                                dateSearch = Utils.getDateFromString(dateString);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dateSearch", "onClick: " + dateSearch);
                EventBus.getDefault().post(new FilterMeetingEvent(dateSearch));
                dismiss();
            }
        });
    }

}

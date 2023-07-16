package com.openclassrooms.mymeeting.views;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.openclassrooms.mymeeting.R;
import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.databinding.ActivityMainBinding;
import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.views.fragments.DateFilterDialogFragment;
import com.openclassrooms.mymeeting.views.fragments.MeetingsFragment;
import com.openclassrooms.mymeeting.views.fragments.RoomListDialogFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Parcelable {

    public static final Creator<MainActivity> CREATOR = new Creator<MainActivity>() {
        @Override
        public MainActivity createFromParcel(Parcel in) {
            return new MainActivity(in);
        }

        @Override
        public MainActivity[] newArray(int size) {
            return new MainActivity[size];
        }
    };
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final MyMeetingApiService service = MyMeetingApiService.getInstance();
    private String room;
    private List<Meeting> mMeetingList = service.getMeetingsList();

    public MainActivity() {

    }

    protected MainActivity(Parcel in) {
        room = in.readString();
        mMeetingList = in.createTypedArrayList(Meeting.CREATOR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_date_filter:
                DateFilterDialogFragment dialog = new DateFilterDialogFragment();
                dialog.show(getSupportFragmentManager(), "dialog");
                //
                return true;
            case R.id.action_room_filter:
                RoomListDialogFragment dialogRoom = new RoomListDialogFragment();
                dialogRoom.show(getSupportFragmentManager(), "dialogRoom");
                return true;
            case R.id.action_all:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView3, MeetingsFragment.getMeetingFragmentInstance()).commitNow();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(room);
        dest.writeTypedList(mMeetingList);
    }
}
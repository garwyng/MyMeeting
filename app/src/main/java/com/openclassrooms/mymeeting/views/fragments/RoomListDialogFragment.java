package com.openclassrooms.mymeeting.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.openclassrooms.mymeeting.controler.MeetingRepository;
import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.databinding.FragmentItemListDialogListDialogBinding;
import com.openclassrooms.mymeeting.databinding.FragmentItemListDialogListDialogItemBinding;
import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.events.FilterRoomEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     RoomListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class RoomListDialogFragment extends BottomSheetDialogFragment {

    public String roomSelected;
    MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    List<String> roomsList = mMeetingRepository.getRooms();
    Bundle bundle = new Bundle();
    private FragmentItemListDialogListDialogBinding binding;
    private OnRoomSelectedListener onRoomSelectedListener;

    public static RoomListDialogFragment newInstance() {
        final RoomListDialogFragment fragment = new RoomListDialogFragment();
        final Bundle args = new Bundle();
        return fragment;
    }

    /**
     * @param listener
     */
    public void setOnRoomSelectedListener(OnRoomSelectedListener listener) {
        this.onRoomSelectedListener = listener;
    }

    /**
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /**
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RoomAdapter(roomsList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public interface OnRoomSelectedListener {
        /**
         * room selected by user
         * @param roomSelected
         */
        void sendRoomSelected(String roomSelected);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;

        ViewHolder(FragmentItemListDialogListDialogItemBinding binding) {
            super(binding.getRoot());
            text = binding.itemRoom;

        }

    }

    private class RoomAdapter extends RecyclerView.Adapter<ViewHolder> {


        RoomAdapter(List<String> room) {
            roomsList = room;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ViewHolder(FragmentItemListDialogListDialogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(String.valueOf(roomsList.get(position)));


            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String roomSelected = holder.text.getText().toString();
                    if (onRoomSelectedListener != null) {
                        onRoomSelectedListener.sendRoomSelected(roomSelected);
                    }
                    EventBus.getDefault().post(new FilterRoomEvent(roomSelected));
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return roomsList.size();
        }

    }
}
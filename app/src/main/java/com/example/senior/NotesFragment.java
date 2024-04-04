package com.example.senior;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.senior.UserNotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private static final String PREFS_NAME = "UserNotesPrefs";
    private static final String USER_NOTES_KEY = "userNotes";

    private ArrayList<UserNotes> userNotesList = new ArrayList<>();
    private ArrayAdapter<UserNotes> adapter;



    private int lastClickedPosition = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        ListView listViewNotes = view.findViewById(R.id.listViewNotes);
        adapter = new ArrayAdapter<>(requireContext(), R.layout.list_item_note, userNotesList);
        listViewNotes.setAdapter(adapter);

        //array of building names
        String[] buildingNames = {"Select A Building", "William N. Pennington", "Davidson Math and Science","Palmer Engineering", "Harry Reid Engineering Lab"};


        ArrayAdapter<String> buildingAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, buildingNames);


        buildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner spinnerBuildingName = view.findViewById(R.id.spinnerBuildingName);
        spinnerBuildingName.setAdapter(buildingAdapter);


        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNoteToList();
                InputMethodManager HideKeyboard = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                HideKeyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Button backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager HideKeyboard = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                HideKeyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
                getParentFragmentManager().beginTransaction().hide(NotesFragment.this).commit();            }
        });

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lastClickedPosition == position) {
                    //Double click to remove from list
                    userNotesList.remove(position);

                    adapter.notifyDataSetChanged();

                    saveUserNotes();

                    lastClickedPosition = -1;

                    Toast.makeText(getContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();

                } else {

                    lastClickedPosition = position;
                    Toast.makeText(getContext(), "Click again to delete", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // Retrieve user notes from SharedPreferences
        retrieveUserNotes();

        return view;
    }

    private void addNoteToList() {
        EditText editTextName = requireView().findViewById(R.id.editTextName);
        EditText editTextRoomNumber = requireView().findViewById(R.id.editTextRoomNumber);
        Spinner spinnerBuildingName = requireView().findViewById(R.id.spinnerBuildingName);

        String name = editTextName.getText().toString().trim();
        String roomNumberString = editTextRoomNumber.getText().toString().trim();
        String buildingName = spinnerBuildingName.getSelectedItem().toString();

        if (name.isEmpty() || roomNumberString.isEmpty() || buildingName.equals("Select A Building")) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int roomNumber = Integer.parseInt(roomNumberString);
        UserNotes note = new UserNotes(name, roomNumber, buildingName);
        userNotesList.add(note);
        adapter.notifyDataSetChanged();


        editTextName.getText().clear();
        editTextRoomNumber.getText().clear();
        spinnerBuildingName.setSelection(0);

        // Save user notes to SharedPreferences
        saveUserNotes();
    }

    private void saveUserNotes() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String userNotesJson = new Gson().toJson(userNotesList);
        editor.putString(USER_NOTES_KEY, userNotesJson);
        editor.apply();
    }

    private void retrieveUserNotes() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userNotesJson = prefs.getString(USER_NOTES_KEY, null);
        if (userNotesJson != null) {
            Type listType = new TypeToken<ArrayList<UserNotes>>(){}.getType();
            userNotesList.clear();
            userNotesList.addAll(new Gson().fromJson(userNotesJson, listType));
            adapter.notifyDataSetChanged();
        }
    }

}

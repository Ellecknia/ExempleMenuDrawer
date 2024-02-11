package fr.ellecknia.testappdraweredgecoco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;


public class Home extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        // --- To set the right selected item ---
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        MainActivity.uncheckAllMenuItems(navigationView.getMenu());

        // --- GO ACTIVITY 1 ---
        Button buttonChooseBetween = view.findViewById(R.id.btn_act1);
        buttonChooseBetween.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Activity1())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_act1);
        });

        // --- GO TO ACTIVITY 2 ---
        Button buttonRandCo = view.findViewById(R.id.btn_act2);
        buttonRandCo.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Activity2())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_act2);
        });

        return view;
    }
}


package com.example.appalarmux.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appalarmux.R;
import com.example.appalarmux.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
/*
        // Obtener la referencia al SwitchCompat
        Switch switchCompat = binding.switch1; // Asegúrate de tener switch1 en tu XML

        // Cambiar el color del deslizador (thumb) y de la barra (track)
        switchCompat.setThumbTintList(ContextCompat.getColorStateList(requireContext(), R.color.accent));
        switchCompat.setTrackTintList(ContextCompat.getColorStateList(requireContext(), R.color.gray40));
*/

        binding.imageView2.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_quick_alarm);
        });

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitleTextColor(R.color.white);
        toolbar.setTitle("AlarmFit");


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

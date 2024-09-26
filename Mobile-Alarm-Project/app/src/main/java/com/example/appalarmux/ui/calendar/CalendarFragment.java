package com.example.appalarmux.ui.calendar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appalarmux.R;
import com.example.appalarmux.databinding.FragmentCalendarBinding;


public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarViewModel calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.backButtom.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_home);
        });

        binding.buttonCalendarGoogle.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Sincronización")
                    .setMessage("¿Desea sincronizar Google Calendar con AlarmFit?")
                    .setPositiveButton("ACEPTAR", (dialog, which) -> {

                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.navigation_calendar_date);

                        Toast.makeText(requireContext(), "Sincronizado", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("CANCELAR", (dialog, which) -> {

                        Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .create()
                    .show();
        });

        binding.buttonCalendar.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Sincronización")
                    .setMessage("¿Desea sincronizar Microsoft Calendar con AlarmFit?")
                    .setPositiveButton("ACEPTAR", (dialog, which) -> {

                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.navigation_calendar_date);

                        Toast.makeText(requireContext(), "Sincronizado", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("CANCELAR", (dialog, which) -> {

                        Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .create()
                    .show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
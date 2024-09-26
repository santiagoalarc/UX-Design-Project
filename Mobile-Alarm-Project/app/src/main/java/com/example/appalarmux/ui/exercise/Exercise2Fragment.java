package com.example.appalarmux.ui.exercise;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appalarmux.R;
import com.example.appalarmux.databinding.FragmentExercise2Binding;

public class Exercise2Fragment extends Fragment {

    private FragmentExercise2Binding binding;
    private Handler handler = new Handler();
    private long startTime = 0L;
    private boolean isRunning = false;
    private boolean series2Flag = false;
    private boolean series3Flag = false;
    private long initialTime = 3 * 60 * 1000;
    //private long initialTime = 10 * 1000;
    private Button finishBut, buttonNew, startButton;

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {

                long currentTime = initialTime - (System.currentTimeMillis() - startTime);

                if (currentTime <= 0) {
                    currentTime = 0;
                    isRunning = false;
                    handler.removeCallbacks(this);
                    binding.startButton.setText("EMPEZAR");
                    if (!series2Flag){
                        binding.buttonSeries2.setTextColor(
                                ContextCompat.getColorStateList(requireContext(), R.color.white));
                        binding.buttonSeries2.setBackgroundTintList(
                                ContextCompat.getColorStateList(requireContext(), R.color.primary));
                        binding.buttonSeries1.setTextColor(
                                ContextCompat.getColorStateList(requireContext(), R.color.gray40));
                        binding.buttonSeries1.setBackgroundTintList(
                                ContextCompat.getColorStateList(requireContext(), R.color.gray5));
                        series2Flag = true;
                    }else {
                        binding.buttonSeries3.setTextColor(
                                ContextCompat.getColorStateList(requireContext(), R.color.white));
                        binding.buttonSeries3.setBackgroundTintList(
                                ContextCompat.getColorStateList(requireContext(), R.color.primary));
                        binding.buttonSeries2.setTextColor(
                                ContextCompat.getColorStateList(requireContext(), R.color.gray40));
                        binding.buttonSeries2.setBackgroundTintList(
                                ContextCompat.getColorStateList(requireContext(), R.color.gray5));
                    }

                }

                // Obtener horas, minutos, segundos y milésimas restantes
                int hours = (int) (currentTime / 1000) / 60 / 60;
                int minutes = (int) (currentTime / 1000) / 60;
                int seconds = (int) (currentTime / 1000) % 60;
                int milliseconds = (int) (currentTime % 1000);

                // Formatear el texto para mostrar horas, minutos, segundos y milésimas
                String timeText = String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds);
                binding.timerTextView.setText(timeText);

                // Repetir la actualización cada milisegundo
                handler.postDelayed(this, 1);
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        ExerciseViewModel exerciseViewModel =
                new ViewModelProvider(this).get(ExerciseViewModel.class);

        binding = FragmentExercise2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton backButton = binding.backButtom;

        backButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_home);
        });

        // Referencias a los botones y temporizador desde el binding
        startButton = binding.startButton;
        binding.startButton.setOnClickListener(v -> {
            if (!isRunning) {
                startTime = System.currentTimeMillis();
                isRunning = true;
                handler.post(timerRunnable);
                startButton.setText("DETENER");
            }else{
                isRunning = false;
                handler.removeCallbacks(timerRunnable);
                startButton.setText("EMPEZAR");
            }
        });


        finishBut = binding.finishBut;
        finishBut.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Ejercicio en progreso")
                    .setMessage("¿Desea finalizar tu entrenamiento por hoy?")
                    .setPositiveButton("ACEPTAR", (dialog, which) -> {

                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.navigation_home);
                    })
                    .setNegativeButton("CANCELAR", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .create()
                    .show();
        });
        buttonNew = binding.buttonNew;
        buttonNew.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Ejercicio en progreso")
                    .setMessage("¿Desea finalizar Press Banca para inicair un nuevo ejercicio?")
                    .setPositiveButton("ACEPTAR", (dialog, which) -> {

                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.navigation_exercise);
                    })
                    .setNegativeButton("CANCELAR", (dialog, which) -> {
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
        handler.removeCallbacks(timerRunnable);
        binding = null;
    }
}

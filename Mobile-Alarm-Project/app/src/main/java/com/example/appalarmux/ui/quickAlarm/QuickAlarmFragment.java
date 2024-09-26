package com.example.appalarmux.ui.quickAlarm;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appalarmux.R;
import com.example.appalarmux.databinding.FragmentQuickAlarmBinding;

public class QuickAlarmFragment extends Fragment {

    private FragmentQuickAlarmBinding binding;
    private Handler handler = new Handler();
    private boolean isRunning = false;
    private long startTime = 0L;
    private long initialTime = 3 * 60 * 1000;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        QuickAlarmViewModel quickAlarmViewModel =
                new ViewModelProvider(this).get(QuickAlarmViewModel.class);

        binding = FragmentQuickAlarmBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.backButtom.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_home);
        });

        binding.Min05button.setOnClickListener(v -> {
            initialTime = 5 * 60 * 1000;
            updateTimerTextView(0, 5, 0, 0);
            resetButtons();
            highlightButton(binding.Min05button);
            if (!isRunning) {
                enableStartButton();
            } else {
                stopTimer();
                disableStopButton();
            }
        });

        binding.Min10button.setOnClickListener(v -> {
            initialTime = 10 * 60 * 1000;
            updateTimerTextView(0, 10, 0, 0);
            resetButtons();
            highlightButton(binding.Min10button);
            if (!isRunning) {
                enableStartButton();
            } else {
                stopTimer();
                disableStopButton();
            }
        });

        binding.Min20button.setOnClickListener(v -> {
            initialTime = 20 * 60 * 1000;
            updateTimerTextView(0, 20, 0, 0);
            resetButtons();
            highlightButton(binding.Min20button);
            if (!isRunning) {
                enableStartButton();
            } else {
                stopTimer();
                disableStopButton();
            }
        });


        binding.startButton.setOnClickListener(v -> {
            if (!isRunning) {
                startTime = System.currentTimeMillis();
                isRunning = true;
                handler.post(timerRunnable);
            }
            binding.startButton.setBackgroundTintList(
                    ContextCompat.getColorStateList(requireContext(), R.color.gray5)
            );
            binding.stopButton.setBackgroundTintList(
                    ContextCompat.getColorStateList(requireContext(), R.color.warning)
            );
            binding.startButton.setClickable(Boolean.FALSE);
        });

        binding.stopButton.setOnClickListener(v -> {
            if (isRunning) {
                isRunning = false;
                handler.removeCallbacks(timerRunnable);
                binding.startButton.setBackgroundTintList(
                        ContextCompat.getColorStateList(requireContext(), R.color.accent)
                );
                binding.startButton.setText("REINCIAR");
                binding.startButton.setClickable(Boolean.TRUE);
                binding.stopButton.setBackgroundTintList(
                        ContextCompat.getColorStateList(requireContext(), R.color.primary)
                );
                binding.stopButton.setText("CONTINUAR");
            }
        });
        return root;
    }

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

    private void resetButtons(){

        binding.startButton.setBackgroundTintList(
                ContextCompat.getColorStateList(requireContext(), R.color.primary)
        );
        binding.startButton.setText("EMPEZAR");
        binding.Min05button.setBackgroundTintList(
                ContextCompat.getColorStateList(requireContext(), R.color.white)
        );
        binding.Min05button.setTextColor(
                ContextCompat.getColorStateList(requireContext(), R.color.black)
        );
        binding.Min10button.setBackgroundTintList(
                ContextCompat.getColorStateList(requireContext(), R.color.white)
        );
        binding.Min10button.setTextColor(
                ContextCompat.getColorStateList(requireContext(), R.color.black)
        );
        binding.Min20button.setBackgroundTintList(
                ContextCompat.getColorStateList(requireContext(), R.color.white)
        );
        binding.Min20button.setTextColor(
                ContextCompat.getColorStateList(requireContext(), R.color.black)
        );
    }

    private void updateTimerTextView(int hours, int minutes, int seconds, int milliseconds) {
        String timeText = String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds);
        binding.timerTextView.setText(timeText);
    }

    private void highlightButton(Button button) {
        button.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.primary));
        button.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.white));
    }

    private void enableStartButton() {
        binding.startButton.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.primary));
        binding.startButton.setClickable(true);
    }

    private void stopTimer() {
        isRunning = false;
        handler.removeCallbacks(timerRunnable);
    }

    private void disableStopButton() {
        binding.stopButton.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.gray5));
        binding.stopButton.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.white));
        binding.stopButton.setClickable(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(timerRunnable);
        binding = null;
    }
}

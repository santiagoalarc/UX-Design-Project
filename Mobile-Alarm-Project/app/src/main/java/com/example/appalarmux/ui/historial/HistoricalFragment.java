package com.example.appalarmux.ui.historial;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.appalarmux.R;
import com.example.appalarmux.databinding.FragmentHistorialBinding;

//Dashboard
public class HistoricalFragment extends Fragment {

    private FragmentHistorialBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentHistorialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView imageViewB = binding.imageViewB;
        ImageView imageView = binding.imageView;
        ImageView imageViewDeleted = binding.imageButtonDeleteB;
        ImageView imageButtonDeleteB2 = binding.imageButtonDeleteB2;
        TextView textView2B = binding.textView2B;
        TextView textView3B = binding.textView3B;
        TextView textView5B = binding.textView5B;
        TextView textViewPM1B = binding.textViewPM1B;
        ImageView imageViewPM1B = binding.imageViewPM1B;
        TextView textView2 = binding.textView2;
        TextView textView3 = binding.textView3;
        TextView textView5 = binding.textView5;
        TextView textViewPM1 = binding.textViewPM1;
        ImageView imageViewPM1 = binding.imageViewPM1;
        ImageButton imageButtonDeleteB = binding.imageButtonDeleteB;
        ImageButton deleteButton = binding.imageButtonDeleteB;
        ImageButton backButton = binding.backButtom;

        imageViewB.setOnClickListener(v -> {
            int visibilityValue= imageButtonDeleteB.getVisibility();
            if (visibilityValue == 0){
                ConstraintLayout layout = binding.constraintLayout;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.constrainWidth(imageViewB.getId(), 1200);
                constraintSet.setHorizontalBias(imageViewB.getId(), 0.5f);
                constraintSet.applyTo(layout);
                deleteButton.setVisibility(View.GONE);

            }else{
                ConstraintLayout layout = (ConstraintLayout) v.getParent();
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.constrainWidth(imageViewB.getId(), 1100);
                constraintSet.setHorizontalBias(imageViewB.getId(), 0.31f);
                constraintSet.applyTo(layout);
                imageViewDeleted.setVisibility(View.VISIBLE);
            }
        });

        imageView.setOnClickListener(v -> {
            int visibilityValue= imageButtonDeleteB2.getVisibility();
            if (visibilityValue == 0){
                ConstraintLayout layout = binding.constraintLayout;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.constrainWidth(imageView.getId(), 1200);
                constraintSet.setHorizontalBias(imageView.getId(), 0.5f);
                constraintSet.applyTo(layout);
                imageButtonDeleteB2.setVisibility(View.GONE);

            }else{
                ConstraintLayout layout = (ConstraintLayout) v.getParent();
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.constrainWidth(imageView.getId(), 1100);
                constraintSet.setHorizontalBias(imageView.getId(), 0.31f);
                constraintSet.applyTo(layout);
                imageButtonDeleteB2.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_home);
        });


        deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Eliminar alarma")
                    .setMessage("¿Estás seguro de que quieres eliminar esta alarma?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        textView2B.setVisibility(View.GONE);
                        textView3B.setVisibility(View.GONE);
                        textView5B.setVisibility(View.GONE);
                        textViewPM1B.setVisibility(View.GONE);
                        imageViewPM1B.setVisibility(View.GONE);
                        imageButtonDeleteB.setVisibility(View.GONE);
                        imageViewB.setVisibility(View.GONE);
                        Toast.makeText(requireContext(), "Alarma eliminada", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, which) -> {

                        ConstraintLayout layout = binding.constraintLayout;
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(layout);
                        constraintSet.constrainWidth(imageViewB.getId(), 1200);
                        constraintSet.setHorizontalBias(imageViewB.getId(), 0.5f);
                        constraintSet.applyTo(layout);
                        deleteButton.setVisibility(View.GONE);

                        dialog.dismiss();
                    })
                    .create()
                    .show();
        });

        imageButtonDeleteB2.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Eliminar alarma")
                    .setMessage("¿Estás seguro de que quieres eliminar esta alarma?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.GONE);
                        textView5.setVisibility(View.GONE);
                        textViewPM1.setVisibility(View.GONE);
                        imageViewPM1.setVisibility(View.GONE);
                        imageButtonDeleteB2.setVisibility(View.GONE);
                        imageView.setVisibility(View.GONE);
                        Toast.makeText(requireContext(), "Alarma eliminada", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, which) -> {

                        ConstraintLayout layout = binding.constraintLayout;
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.clone(layout);
                        constraintSet.constrainWidth(imageView.getId(), 1200);
                        constraintSet.setHorizontalBias(imageView.getId(), 0.5f);
                        constraintSet.applyTo(layout);
                        imageButtonDeleteB2.setVisibility(View.GONE);

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

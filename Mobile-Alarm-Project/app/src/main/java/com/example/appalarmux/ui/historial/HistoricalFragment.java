package com.example.appalarmux.ui.historial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

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

        // Obtener el ImageView por el ID
        ImageView imageViewB = binding.imageViewB; // Asegúrate de que el ID coincida con tu XML
        ImageView imageViewDeleted = binding.imageButtonDeleteB;
        // Establecer el listener para modificar las propiedades del layout
        imageViewB.setOnClickListener(v -> {
            // Obtener el layout que contiene el ImageView
            ConstraintLayout layout = (ConstraintLayout) v.getParent();

            // Crear un nuevo ConstraintSet para modificar las propiedades del layout
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout); // Clonar el layout actual

            // Modificar el ancho del ImageView
            constraintSet.constrainWidth(v.getId(), 1100); // Establecer el ancho en 350

            // Mantener el valor de layout_constraintHorizontal_bias
            //float bias = constraintSet.getHorizontalBias(v.getId());
            constraintSet.setHorizontalBias(v.getId(), 0.31f); // Reaplicar el bias actual
            // Aplicar los cambios
            constraintSet.applyTo(layout);
            // Hacer visible el ImageView imageViewDeleteB
            imageViewDeleted.setVisibility(View.VISIBLE);
        });

        // Configurar el botón back_button para regresar al fragmento fragment_home
        ImageButton backButton = binding.backButtom; // Asegúrate de que el ID coincida con tu XML
        backButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_home);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

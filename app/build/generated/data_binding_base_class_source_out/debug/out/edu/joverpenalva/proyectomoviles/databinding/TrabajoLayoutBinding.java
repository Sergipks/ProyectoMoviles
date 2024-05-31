// Generated by view binder compiler. Do not edit!
package edu.joverpenalva.proyectomoviles.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import edu.joverpenalva.proyectomoviles.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TrabajoLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final TextView tvCodTrabajo;

  @NonNull
  public final TextView tvDescripcion;

  @NonNull
  public final TextView tvPrioridad;

  private TrabajoLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout linearLayout, @NonNull TextView tvCodTrabajo,
      @NonNull TextView tvDescripcion, @NonNull TextView tvPrioridad) {
    this.rootView = rootView;
    this.linearLayout = linearLayout;
    this.tvCodTrabajo = tvCodTrabajo;
    this.tvDescripcion = tvDescripcion;
    this.tvPrioridad = tvPrioridad;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static TrabajoLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TrabajoLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.trabajo_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TrabajoLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.tvCodTrabajo;
      TextView tvCodTrabajo = ViewBindings.findChildViewById(rootView, id);
      if (tvCodTrabajo == null) {
        break missingId;
      }

      id = R.id.tvDescripcion;
      TextView tvDescripcion = ViewBindings.findChildViewById(rootView, id);
      if (tvDescripcion == null) {
        break missingId;
      }

      id = R.id.tvPrioridad;
      TextView tvPrioridad = ViewBindings.findChildViewById(rootView, id);
      if (tvPrioridad == null) {
        break missingId;
      }

      return new TrabajoLayoutBinding((ConstraintLayout) rootView, linearLayout, tvCodTrabajo,
          tvDescripcion, tvPrioridad);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
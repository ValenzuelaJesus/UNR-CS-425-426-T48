// Generated by view binder compiler. Do not edit!
package com.example.senior.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.senior.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySearchBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton backButton;

  @NonNull
  public final ConstraintLayout coordinatorLayout;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final AppCompatButton searchButton;

  @NonNull
  public final Spinner spinnerBuildingName;

  @NonNull
  public final TextView textView2;

  private ActivitySearchBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton backButton, @NonNull ConstraintLayout coordinatorLayout,
      @NonNull ImageView imageView, @NonNull LinearLayout linearLayout,
      @NonNull AppCompatButton searchButton, @NonNull Spinner spinnerBuildingName,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.backButton = backButton;
    this.coordinatorLayout = coordinatorLayout;
    this.imageView = imageView;
    this.linearLayout = linearLayout;
    this.searchButton = searchButton;
    this.spinnerBuildingName = spinnerBuildingName;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backButton;
      AppCompatButton backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      ConstraintLayout coordinatorLayout = (ConstraintLayout) rootView;

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.searchButton;
      AppCompatButton searchButton = ViewBindings.findChildViewById(rootView, id);
      if (searchButton == null) {
        break missingId;
      }

      id = R.id.spinnerBuildingName;
      Spinner spinnerBuildingName = ViewBindings.findChildViewById(rootView, id);
      if (spinnerBuildingName == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new ActivitySearchBinding((ConstraintLayout) rootView, backButton, coordinatorLayout,
          imageView, linearLayout, searchButton, spinnerBuildingName, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

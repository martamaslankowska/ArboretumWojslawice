package arboretum.arboretumwojslawice.View.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.AttractionViewModel;
import dagger.android.support.DaggerFragment;

public class AttractionFragment extends DaggerFragment {

    @Inject
    AttractionViewModel attractionViewModel;

    public AttractionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attraction, container, false);
    }
}

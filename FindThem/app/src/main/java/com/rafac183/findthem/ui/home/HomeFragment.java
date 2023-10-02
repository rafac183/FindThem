package com.rafac183.findthem.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.rafac183.findthem.activities.ProfileActivity;
import com.rafac183.findthem.activities.RateUsActivity;
import com.rafac183.findthem.activities.SettingsActivity;
import com.rafac183.findthem.activities.ShareActivity;
import com.rafac183.findthem.databinding.FragmentHomeBinding;

import java.util.concurrent.atomic.AtomicReference;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*---------------Methods-------------*/
        SetInfo();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void SetInfo() {
        homeViewModel.getHomeData().observe(getViewLifecycleOwner(), homeList -> {
            CardView[] cards = {
                    binding.cvWelcome,
                    binding.cvProfile,
                    binding.cvRateUs,
                    binding.cvShare,
                    binding.cvSettings
            };
            ImageView[] cardImgIds = {
                    binding.ivWelcome,
                    binding.ivProfile,
                    binding.ivRateUs,
                    binding.ivShare,
                    binding.ivSettings
            };

            TextView[] cardNameIds = {
                    binding.cardNameWelcome,
                    binding.cardNameProfile,
                    binding.cardNameRateUs,
                    binding.cardNameShare,
                    binding.cardNameSettings
            };

            TextView[] cardDescIds = {
                    binding.cardDescWelcome,
                    binding.cardDescProfile,
                    binding.cardDescRateUs,
                    binding.cardDescShare,
                    binding.cardDescSettings
            };

            for (int i = 0; i < Math.min(homeList.size(), cardNameIds.length); i++) {
                CardView cardView = cards[i];
                ImageView cardImageView = cardImgIds[i];
                TextView cardNameTextView = cardNameIds[i];
                TextView cardDescTextView = cardDescIds[i];

                HomeModel homeModel = homeList.get(i);
                if (i != 0) {
                    cardView.setOnClickListener(v -> ItemSelected(homeModel));
                }
                cardNameTextView.setText(homeModel.getName());
                Glide.with(this).load(homeModel.getImage()).into(cardImageView);
                cardDescTextView.setText(homeModel.getDescription());
            }
        });
    }
    public void ItemSelected(HomeModel homeModel) {
        Intent intent;
        switch (homeModel.getName()) {
            case "Profile":
                intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
                break;
            case "Rate Us":
                intent = new Intent(getActivity(), RateUsActivity.class);
                startActivity(intent);
                break;
            case "Share":
                intent = new Intent(getActivity(), ShareActivity.class);
                startActivity(intent);
                break;
            case "Settings":
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
        }
    }

}
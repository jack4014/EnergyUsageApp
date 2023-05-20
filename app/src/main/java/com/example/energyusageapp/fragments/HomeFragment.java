package com.example.energyusageapp.fragments;

import android.os.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.cardview.widget.*;
import androidx.constraintlayout.helper.widget.*;
import androidx.constraintlayout.widget.*;
import androidx.navigation.*;

import com.example.energyusageapp.MainActivity;
import com.example.energyusageapp.R;

import java.util.*;

public class HomeFragment extends FragmentBase {
    ConstraintLayout container;
    Flow containerFlow;
    NavController nav;
    View self;
    public HomeFragment() {
        super(R.layout.fragment_login);
        icon = ResourceManager.Instance().resources.getDrawable(R.drawable.ic_logos_wintec, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        self = view;
        nav = Navigation.findNavController(view);
        container = view.findViewById(R.id.container_karakias);
        containerFlow = view.findViewById(R.id.flow_karakias);
        List<Karakia> songs = new ArrayList<>(ResourceManager.Instance().karakias.values());
        songs.sort(Comparator.comparing(x -> x.name));
        for (Karakia item: songs)
            if (item.id != 0)
                AddItem(item);
        if (!MainActivity.Settings.agreedToToS)
            nav.navigate(HomeFragmentDirections.actionHomeFragmentToTOSFragment());
        else
            MainActivity.instance.TryOpenTorial(ResourceManager.Instance().helpVideos.get("home"));
    }

    public CardView AddItem(Karakia song) {
        CardView card = (CardView)View.inflate(self.getContext(), R.layout.item_karakia, new CardView(self.getContext()));
        ((TextView)card.findViewById(R.id.text_karakia)).setText(song.name);
        ((ImageView)card.findViewById(R.id.image_karakia)).setImageDrawable(song.image);
        card.setId(View.generateViewId());
        container.addView(card);
        containerFlow.addView(card);
        card.setOnClickListener((x) ->
                nav.navigate(HomeFragmentDirections.actionHomeToKarakiaFragment(song.id))
        );
        return card;
    }
}

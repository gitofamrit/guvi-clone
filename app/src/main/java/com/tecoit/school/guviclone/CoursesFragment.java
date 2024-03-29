package com.tecoit.school.guviclone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import android.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.tecoit.school.guviclone.CourseItem;
import com.tecoit.school.guviclone.CoursesAdapter;
import com.tecoit.school.guviclone.R;
import com.tecoit.school.guviclone.YouTubeConfig;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {

    private RecyclerView courses_rv;
    private YouTubePlayerView youTubePlayerView;
    private int prevActivePosition = 1;
    private CoursesAdapter adapter;
    private static final String TAG = "HomeFragment";
    private YouTubePlayer playerInstance;

    private final List<CourseItem> courseItems = new ArrayList<>();

    private void setIndicatorAt(int position, boolean active){
        if (playerInstance == null)
            return;

        CourseItem.CourseItemContent content =
                ((CourseItem.CourseItemContent) courseItems.get(position));

        content.setActive(active);
        courseItems.set(position, content);

        adapter.notifyItemChanged(position);
    }

    private void loadPlayer(final String videoId){
        if (playerInstance == null)
            return;
        playerInstance.loadVideo(videoId);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        
        courses_rv = view.findViewById(R.id.courses_rv);
        youTubePlayerView = view.findViewById(R.id.yt_player);

        courses_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        courseItems.add(new CourseItem.CourseItemHeader("Beginner Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "_7lDFSorXsU", true));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 02 - Java History", "_7lDFSorXsU"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 03 - Java Features", "_7lDFSorXsU"));

        courseItems.add(new CourseItem.CourseItemHeader("Intermediate Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "PFmuCDHHpwk"));

        courseItems.add(new CourseItem.CourseItemHeader("Intermediate Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "PFmuCDHHpwk"));

        courseItems.add(new CourseItem.CourseItemHeader("Intermediate Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "PFmuCDHHpwk"));

        courseItems.add(new CourseItem.CourseItemHeader("Intermediate Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "PFmuCDHHpwk"));

        courseItems.add(new CourseItem.CourseItemHeader("Intermediate Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "PFmuCDHHpwk"));

        courseItems.add(new CourseItem.CourseItemHeader("Intermediate Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "PFmuCDHHpwk"));

        courseItems.add(new CourseItem.CourseItemHeader("Intermediate Module"));
        courseItems.add(new CourseItem.CourseItemContent("Java - Session 01 - Introduction", "PFmuCDHHpwk"));

        adapter = new CoursesAdapter(getActivity().getApplicationContext(), courseItems, new CoursesAdapter.OnClickListener() {
            @Override
            public void onClick(String videoId, int position) {
                loadPlayer(videoId);
                setIndicatorAt(prevActivePosition, false);
                setIndicatorAt(position, true);

                prevActivePosition = position;
            }
        });
        courses_rv.setAdapter(adapter);

        youTubePlayerView.initialize(YouTubeConfig.getApiKey(), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                playerInstance = youTubePlayer;
                loadPlayer("_7lDFSorXsU");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onInitializationFailure: " + youTubeInitializationResult);
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        if (playerInstance != null)
            playerInstance.release();
        super.onDetach();
    }
}
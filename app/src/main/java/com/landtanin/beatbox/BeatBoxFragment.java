package com.landtanin.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.landtanin.beatbox.databinding.FragmentBeatBoxBinding;
import com.landtanin.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mBeatBox = new BeatBox(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);
        View rootView = binding.getRoot();

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        return rootView;

    }

    /**
     *
     * This is where we hook up viewModel with Binding class
     *
     */
    private class SoundHolder extends RecyclerView.ViewHolder{

        private ListItemSoundBinding mBinding;

        // constructor
        private SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());

            mBinding = binding;

            // construct and attach viewModel
            mBinding.setViewmModel(new SoundViewModel(mBeatBox));

        }

        // update the data that view model is working with
        public void bind(Sound sound){

            // view model has one setter
            mBinding.getViewmModel().setmSound(sound);

            // executePendingBindings force method to update itself immediately
            // to live up to the speed of RecyclerView updating rate
            mBinding.executePendingBindings();

        }

    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSoundsArrayList;

        public SoundAdapter(List<Sound> sounds){
            mSoundsArrayList = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {

            Sound sound = mSoundsArrayList.get(position);
            holder.bind(sound);

        }

        @Override
        public int getItemCount() {
            return mSoundsArrayList.size();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }
}

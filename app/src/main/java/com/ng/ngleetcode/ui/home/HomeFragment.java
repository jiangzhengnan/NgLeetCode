package com.ng.ngleetcode.ui.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ng.code.util.LogUtil;
import com.ng.code.util.ProblemAndroidUtil;
import com.ng.code.util.model.CodeDataModel;
import com.ng.code.util.tree.CodeNode;
import com.ng.ngleetcode.databinding.FragmentHomeBinding;
import com.ng.ngleetcode.utils.UIUtil;
import com.ng.ngleetcode.view.ToggleView;
import com.ng.ngleetcode.view.code.CodeView;
import com.ng.ngleetcode.view.code.Language;
import com.ng.ngleetcode.view.code.Theme;


public class HomeFragment extends Fragment implements CodeView.OnHighlightListener, ToggleView.OnToggleListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CodeNode mNowData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.codeContent.setRadius(UIUtil.dp2px(getContext(), 5));
        binding.toggleCode.setOnToggleListener(this);
        binding.codeView.setOnHighlightListener(this)
                .setOnHighlightListener(this)
                .setTheme(Theme.ANDROIDSTUDIO)
                .setLanguage(Language.JAVA)
                .setWrapLine(false)
                .setFontSize(8)
                .setZoomEnabled(true)
                .setShowLineNumber(false)
                .setStartLineNumber(0)
                .apply();
        return root;
    }

    private void refreshNowProgressBar() {
        ProblemAndroidUtil.getNowProgressAndroid(getContext());
        int allPro = ProblemAndroidUtil.codeNum;
        int readPro = ProblemAndroidUtil.readNum;
        binding.nowPb.setProgress(readPro);
        binding.nowPb.setMax(allPro);
    }

    private String showRandomProblem() {
        mNowData = ProblemAndroidUtil.getRandomProblemJavaContentNew(getContext());
        refreshData(mNowData);
        return mNowData.contentPath.split("/")[0];
    }

    public String refreshData() {
        showAnim();
        return showRandomProblem();
    }

    private void showAnim() {
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat(
                "alpha", 1.0f, 0f, 1.0f);
        ValueAnimator valueAnimator = ObjectAnimator
                .ofPropertyValuesHolder(binding.tvTitle, pvhAlpha);
        valueAnimator.start();
        startAnim(binding.codeView);
    }

    @SuppressLint("Recycle")
    private void startAnim(View target) {
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(target, "alpha", 1f, 0.7f, 1f);
        target.setPivotX(target.getMeasuredWidth());
        target.setPivotY(target.getMeasuredHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator2);
        animatorSet.start();
    }


    public String refreshData(CodeNode codeBean) {
        mNowData = codeBean;
        mNowData.content = ProblemAndroidUtil.readAssets(getContext(), mNowData.contentPath);
        binding.codeView.setCode(mNowData.content).apply();
        setTitle(mNowData.title);
        int state = CodeDataModel.getInstance().loopCodeState(getActivity(), mNowData.title, -1);
        LogUtil.d("当前:" + mNowData.title + " state:" + state);
        refreshNowProgressBar();
        binding.toggleCode.setPositive(state != 2);
        showAnim();
        return mNowData.contentPath.split("/")[0];
    }

    public void setTitle(String title) {
        binding.tvTitle.setText(title.replace(".java", ""));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStartCodeHighlight() {

    }

    @Override
    public void onFinishCodeHighlight() {

    }

    @Override
    public void onLanguageDetected(Language language, int relevance) {

    }

    @Override
    public void onFontSizeChanged(int sizeInPx) {

    }

    @Override
    public void onLineClicked(int lineNumber, String content) {

    }

    @Override
    public void onToggle(boolean isPositive) {
        if (getActivity() != null) {
            CodeDataModel.getInstance().loopCodeState(getActivity(), mNowData.title, isPositive ? 1 : 2);
            refreshNowProgressBar();
        }
    }
}